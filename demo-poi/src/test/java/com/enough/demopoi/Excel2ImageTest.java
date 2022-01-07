package com.enough.demopoi;

import com.enough.demopoi.images.Excel2Image;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.File;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/07
 */
public class Excel2ImageTest {

    public static final String PATH = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
    @Test
    public void excel2ImagesTest() throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        Excel2Image excel2Image = new Excel2Image("C:\\Users\\lidong290\\Desktop\\4月考勤汇总表.xlsx", Excel2Image.SheetTypeEnum.IDX,null,0);
        excel2Image.setImageFileNameColIdx(2);
        excel2Image.setImageFormat("JPG");
        excel2Image.excelRow2Images("C:\\Users\\lidong290\\Desktop\\考勤");
        System.out.println("结束时间：" + System.currentTimeMillis());
        System.out.println("用时：" + (System.currentTimeMillis() - startTime) / 1000);
    }
    @Test
    public void excel2Images2Test() throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        Excel2Image excel2Image = new Excel2Image();
        Workbook wb = WorkbookFactory.create(new File(PATH + "\\11月考勤汇总表.xlsx"));
        Sheet sheet = wb.getSheetAt(0);
        excel2Image.setImageFileNameColIdx(2);
        excel2Image.excelRow2Images(sheet, PATH + "\\11月考勤汇总表");
        System.out.println("结束时间：" + System.currentTimeMillis());
        System.out.println("用时：" + (System.currentTimeMillis() - startTime) / 1000);
    }

    public static void main(String[] args) {
        javax.swing.filechooser.FileSystemView fsv = javax.swing.filechooser.FileSystemView
                .getFileSystemView();
        System.out.println("桌面路径：" + fsv.getHomeDirectory());
        System.out.println("默认路径：" + fsv.getDefaultDirectory());
        System.getProperties().list(System.out);
    }
}
