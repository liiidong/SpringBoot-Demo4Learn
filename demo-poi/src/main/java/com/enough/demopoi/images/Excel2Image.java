package com.enough.demopoi.images;

import cn.hutool.core.img.ImgUtil;
import com.enough.demopoi.utils.ExcelUtil;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.awt.SunHints;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: excel导出为image
 * @author: lidong
 * @create: 2020/05/06
 */
@Service
public class Excel2Image {
    /**
     * 倍数
     */
    private static final float MULTIPLE = 1.15f;

    private String sheetName;
    private int sheetIdx;
    private SheetTypeEnum sheetType;
    private String excelFile;
    @Setter
    private int imageFileNameColIdx;
    @Setter
    private String imageFormat;

    public Excel2Image() {
    }

    public Excel2Image(String excelFile, SheetTypeEnum sheetType, String sheetName, int sheetIdx) {
        this.excelFile = excelFile;
        this.sheetType = sheetType;
        this.sheetName = sheetName;
        this.sheetIdx = sheetIdx;
    }

    @PostConstruct
    private void init() {
    }

    public boolean excelRow2Images(String exportDir) throws Exception {
        Assert.hasLength(excelFile, "Excel文件不能为空");
        Sheet sheet;
        if (sheetType == SheetTypeEnum.IDX) {
            Assert.isTrue(sheetIdx > -1, "sheet索引不能为空");
            sheet = WorkbookFactory.create(new File(excelFile)).getSheetAt(sheetIdx);
        } else {
            Assert.hasLength(sheetName, "sheet名不能为空");
            sheet = WorkbookFactory.create(new File(excelFile)).getSheet(sheetName);
        }
        Assert.notNull(sheet, "sheet解析失败");
        // 遍历需要扫描的区域
        int rowSize = ExcelUtil.getActiveRowCount(sheet);
        int imageWidth = float2Int((ExcelUtil.getRowWidth(sheet) * 1.5f));
        int imageHeight = float2Int(ExcelUtil.getRowHeight(0, 2, sheet) * 2.0f);
        List <GridInfo> grids;
        String fileName;
        float x = ExcelUtil.getSingleColWidth(sheet, 0) * MULTIPLE;
        float y = sheet.getRow(0).getHeightInPoints() * MULTIPLE;
        for (int i = 1; i < rowSize; i++) {
            grids = new ArrayList <>();
            parseGridInfos(grids, sheet, 0, x, y);
            parseGridInfos(grids, sheet, i, x, y + sheet.getRow(i).getHeightInPoints() * MULTIPLE);
            fileName = getFileNameFromExcel(sheet, i);
            exportImage(imageWidth, imageHeight, grids, exportDir, fileName, imageFormat);
        }
        return true;
    }

    public void excelRow2Images(Sheet sheet, String exportDir) throws IOException {
        // 遍历需要扫描的区域
        int rowSize = ExcelUtil.getActiveRowCount(sheet);
        int imageWidth = float2Int((ExcelUtil.getRowWidth(sheet) * 1.5f));
        int imageHeight = float2Int(ExcelUtil.getRowHeight(0, 2, sheet) * 2.0f);
        List <GridInfo> grids = null;
        String fileName = "";
        float x = ExcelUtil.getSingleColWidth(sheet, 0) * MULTIPLE;
        float y = sheet.getRow(0).getHeightInPoints() * MULTIPLE;
        for (int i = 1; i < rowSize; i++) {
            grids = new ArrayList <>();
            parseGridInfos(grids, sheet, 0, x, y);
            parseGridInfos(grids, sheet, i, x, y + sheet.getRow(i).getHeightInPoints() * MULTIPLE);
            fileName = getFileNameFromExcel(sheet, i);
            exportImage(imageWidth, imageHeight, grids, exportDir, fileName, imageFormat);
        }
    }

    /**
     * 解析网格信息
     *
     * @param gridInfos
     * @param sheet
     * @param rowIdx
     */
    private void parseGridInfos(List <GridInfo> gridInfos, Sheet sheet, int rowIdx, float x, float y) {
        int colSize = ExcelUtil.getActiveColCount(sheet);
        for (int j = 0; j < colSize; j++) {
            Cell cell = sheet.getRow(rowIdx).getCell(j);
            GridInfo grid = new GridInfo();
            // 设置坐标和宽高
            grid.setX(x);
            x += ExcelUtil.getSingleColWidth(sheet, j) * MULTIPLE;
            grid.setY(y);
            grid.setWidth(ExcelUtil.getSingleColWidth(sheet, j) * MULTIPLE);
            grid.setHeight(ExcelUtil.getRowHeight(rowIdx, rowIdx, sheet) * MULTIPLE);
            grid.setRowIdx(rowIdx);
            grid.setColIdx(j);
            grid.setShow(ExcelUtil.checkColHidden(rowIdx, j, sheet));
            // 单元格背景颜色
            CellStyle cs = cell.getCellStyle();
            if (cs.getFillPattern() == cs.getFillBackgroundColor()) {
                grid.setBgColor((Color) cell.getCellStyle().getFillForegroundColorColor());
            }
            // 设置字体
            org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().getFontAt(cs.getFontIndex());
            font.setColor(font.getColor());
            grid.setFont(new Font(font.getFontName(), cell.getCellStyle().getBorderBottomEnum().getCode(), (int) (font.getFontHeightInPoints() * MULTIPLE)));
            // 设置字体前景色
            if (font instanceof XSSFFont) {
                XSSFFont xf = (XSSFFont) font;
                grid.setFtColor(new Color(xf.getXSSFColor().getIndex()));
            }
            // 设置文本
            String strCell = ExcelUtil.getCellValueString(sheet, rowIdx, j);
            grid.setText(strCell.matches("\\w*\\.0") ? strCell.substring(0, strCell.length() - 2) : strCell);
            gridInfos.add(grid);
        }
    }

    /**
     * 从Excel中获取文件名称
     *
     * @param sheet
     * @param rowIdx
     * @return
     */
    private String getFileNameFromExcel(Sheet sheet, int rowIdx) {
        return rowIdx + ExcelUtil.getCellValueString(sheet, rowIdx, imageFileNameColIdx);
    }

    /**
     * 导出图片
     *
     * @param imageWidth
     * @param imageHeight
     * @param grids
     * @param filePath
     * @param fileName
     * @param formatName
     * @throws IOException
     */
    private void exportImage(int imageWidth, int imageHeight, List <GridInfo> grids, String filePath, String fileName, String formatName) throws IOException {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // 平滑字体
        g2d.setRenderingHint(SunHints.KEY_ANTIALIASING, SunHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, SunHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        g2d.setRenderingHint(SunHints.KEY_STROKE_CONTROL, SunHints.VALUE_STROKE_DEFAULT);
        g2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST, 140);
        g2d.setRenderingHint(SunHints.KEY_FRACTIONALMETRICS, SunHints.VALUE_FRACTIONALMETRICS_OFF);
        g2d.setRenderingHint(SunHints.KEY_RENDERING, SunHints.VALUE_RENDER_DEFAULT);
        g2d.setColor(Color.white);
        //减少锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        //消除文字锯齿
        //g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除画图锯齿
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //填充图形矩形
        g2d.fillRect(0, 0, imageWidth, imageHeight);
        // 绘制表格
        parseGraphics2D(grids, g2d);
        g2d.dispose();
        if (!new File(filePath).exists()) {
            new File(filePath).mkdir();
        }
        formatName = StringUtils.isNotBlank(formatName) ? formatName : "png";
        ImageIO.write(image, formatName, new File(filePath.concat("/").concat(fileName).concat(".").concat(formatName)));
    }

    /**
     * 解析2D图形对象
     *
     * @param grids
     * @param g2d
     */
    private void parseGraphics2D(List <GridInfo> grids, Graphics2D g2d) {
        // 绘制表格
        for (GridInfo g : grids) {
            if (!g.isShow()) {
                continue;
            }
            // 绘制背景色
            g2d.setColor(g.getBgColor() == null ? Color.white : g.getBgColor());
            g2d.fillRect(float2Int(g.getX()), float2Int(g.getY()), float2Int(g.getWidth()), float2Int(g.getHeight()));

            // 绘制边框
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(0.5f));
            g2d.drawRect(float2Int(g.getX()), float2Int(g.getY()), float2Int(g.getWidth()), float2Int(g.getHeight()));

            // 绘制文字,居中显示
            g2d.setColor(g.getFtColor());
            FontMetrics fm = g2d.getFontMetrics(g.getFont());
            // 获取将要绘制的文字宽度
            int strWidth = fm.stringWidth(g.getText());
            //设置字体
//            g2d.setFont(g.getFont() == null ? new Font("等线", Font.BOLD, 14) : g.getFont());
            g2d.setFont(new Font("等线", Font.BOLD, g2d.getFont().getSize()));
            g2d.setPaint(Color.BLACK);
            //计算居中位置
            g2d.drawString(g.getText(), g.getX() + (g.getWidth() - strWidth) / 2,
                    g.getY() + (g.getHeight() - g.getFont().getSize()) / 2 + g.getFont().getSize());
        }
    }

    private int float2Int(float f) {
        return (int) (f + 0.5);
    }

    public enum SheetTypeEnum {
        /**
         * 类型枚举：索引、名称
         */
        IDX, NAME
    }
}
