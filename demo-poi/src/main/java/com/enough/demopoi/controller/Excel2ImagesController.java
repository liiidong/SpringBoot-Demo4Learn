package com.enough.demopoi.controller;

import com.enough.common.model.CommonBuilder;
import com.enough.common.model.ReturnResult;
import com.enough.demopoi.images.Excel2Image;
import com.enough.demopoi.images.Excel2ImagesParam;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/07
 */
@RestController
@RequestMapping("/excel2images")
public class Excel2ImagesController {

    /**
     * 逐行导出
     *
     * @param param
     * @return
     */
    @PostMapping("/exportLineByline")
    public ReturnResult <String> excel2Images(@RequestBody Excel2ImagesParam param) throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        Excel2Image excel2Image = new Excel2Image(param.getExcelFile(), Excel2Image.SheetTypeEnum.IDX, null, 0);
        excel2Image.setImageFileNameColIdx(2);
        excel2Image.setImageFormat("JPG");
        boolean exportRst = excel2Image.excelRow2Images(param.getExportDir());
        if (exportRst && param.isOpenAfterExport()) {
            String cmdDir[] = {"explorer.exe", param.getExportDir()};
            Runtime.getRuntime().exec(cmdDir);
        }
        System.out.println("结束时间：" + System.currentTimeMillis());
        System.out.println("用时：" + (System.currentTimeMillis() - startTime) / 1000);
        return ReturnResult.structure(exportRst, String.class).build();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/xxx")
    public Excel2ImagesParam Excel2ImagesParam() {
        return CommonBuilder.of(Excel2ImagesParam::new).with(Excel2ImagesParam::setExcelFile, "dsadsa").with(Excel2ImagesParam::setExportDir, "xxxxxxxxxx")
                .build();
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        Excel2Image excel2Image = new Excel2Image("C:\\Users\\LD\\Desktop\\6月考勤汇总表.xlsx", Excel2Image.SheetTypeEnum.IDX, null, 0);
        excel2Image.setImageFileNameColIdx(2);
        excel2Image.setImageFormat("JPG");
        boolean exportRst = excel2Image.excelRow2Images("C:\\Users\\LD\\Desktop\\工资截图");
        if (exportRst && true) {
            String cmdDir[] = {"explorer.exe","C:\\Users\\LD\\Desktop\\工资截图"};
            Runtime.getRuntime().exec(cmdDir);
        }
        System.out.println("结束时间：" + System.currentTimeMillis());
        System.out.println("用时：" + (System.currentTimeMillis() - startTime) / 1000);
    }
}
