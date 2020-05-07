package com.enough.demopoi.images;

import lombok.Data;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: Excel导出images参数
 * @author: lidong
 * @create: 2020/05/07
 */
@Data
public class Excel2ImagesParam {

    private String excelFile;
    private String exportDir;
    private boolean openAfterExport;

}
