package com.enough.demopoi.images;

import lombok.Data;

import java.awt.*;
import java.io.Serializable;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 网格信息
 * @author: lidong
 * @create: 2020/05/06
 */
@Data
public class GridInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 行索引
     */
    private int rowIdx;
    /**
     * 列索引
     */
    private int colIdx;
    /**
     * X坐标
     */
    private float x;
    /**
     * Y坐标
     */
    private float y;
    /**
     * 高
     */
    private float height;
    /**
     * 宽
     */
    private float width;
    /**
     * 是否展示
     */
    private boolean show;
    /**
     * 背景色
     */
    private Color bgColor;
    /**
     * 字体
     */
    private Font font = new Font("Default", Font.PLAIN, 12);
    /**
     * 前景色
     */
    private Color ftColor;
    /**
     * 文本
     */
    private String text;

}
