package com.enough.demopoi.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.Assert;

import java.text.DecimalFormat;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/06
 */
public class ExcelUtil {
    /**
     * 获取行宽度
     *
     * @param sheet
     * @return
     */
    public static float getRowWidth(Sheet sheet) {
        Assert.notNull(sheet, "sheet不能为null");
        // 计算所求区域宽度
        int widthPix = 0;
        for (int i = 0; i < getActiveColCount(sheet); i++) {
            widthPix += sheet.getColumnWidthInPixels(i);
        }
        return widthPix;
    }

    /**
     * 获取单列宽度
     *
     * @param sheet
     * @return
     */
    public static float getSingleColWidth(Sheet sheet, int colIdx) {
        Assert.notNull(sheet, "sheet不能为null");
        Assert.isTrue(colIdx >= 0, "列索引不能小于0");
        return sheet.getColumnWidthInPixels(colIdx);
    }

    /**
     * 获取行累计高度
     *
     * @param startIdx
     * @param endIdx
     * @param sheet
     * @return
     */
    public static float getRowHeight(int startIdx, int endIdx, Sheet sheet) {
        Assert.notNull(sheet, "sheet不能为null");
        // 计算所求区域宽度
        int heightPix = 0;
        for (int i = startIdx; i <= endIdx; i++) {
            heightPix += sheet.getRow(i).getHeightInPoints();
        }
        return heightPix;
    }

    /**
     * 获取活动行数
     *
     * @param sheet
     * @return
     */
    public static int getActiveRowCount(Sheet sheet) {
        Assert.notNull(sheet, "sheet不能为null");
        return sheet.getLastRowNum() + 1;
    }

    /**
     * 获取活动列数
     *
     * @param sheet
     * @return
     */
    public static int getActiveColCount(Sheet sheet) {
        Assert.notNull(sheet, "sheet不能为null");
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    /**
     * 检查单元格是否被隐藏
     *
     * @param rowIdx
     * @param colIdx
     * @param sheet
     * @return 隐藏->返回false
     */
    public static boolean checkColHidden(int rowIdx, int colIdx, Sheet sheet) {
        boolean ifShow = true;
        ifShow = ifShow && !(sheet.isColumnHidden(colIdx) || sheet.getRow(rowIdx).getZeroHeight());
        return ifShow;
    }

    /**
     * 获取单元格内容文本
     *
     * @param sheets
     * @param rowIdx
     * @param colIdx
     * @return
     */
    public static String getCellValueString(Sheet sheets, int rowIdx, int colIdx) {
        String strCell;
        Cell cell = sheets.getRow(rowIdx).getCell(colIdx);
        if(cell == null){
            return "";
        }
        switch (cell.getCellTypeEnum()) {
        case NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case STRING:
            strCell = cell.getStringCellValue();
            break;
        case BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case FORMULA:
            try {
                strCell = String.valueOf(cell.getNumericCellValue());
            } catch (IllegalStateException e) {
                strCell = String.valueOf(cell.getRichStringCellValue());
            }
            break;
        default:
            strCell = "";
        }

        if (cell.getCellStyle().getDataFormatString().contains("0.00%")) {
            try {
                double dbCell = Double.parseDouble(strCell);
                strCell = new DecimalFormat("#.00").format(dbCell * 100) + "%";
            } catch (NumberFormatException e) {
            }
        }
        return strCell;
    }
}
