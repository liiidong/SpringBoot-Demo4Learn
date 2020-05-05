package com.enough.demopoi.images;

import java.awt.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: liiidong
 * @create: 2020/05/05
 */
public class Grid {
    private int x;
    private int y;
    private int height;
    private int width;
    private int col;
    private int row;
    private boolean show;
    private Color bgColor;
    private XSSFFont font;
    private Color ftColor;
    private String text;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setFont(XSSFFont font) {
        this.font = font;
    }

    public XSSFFont getFont() {
        return font;
    }

    public void setFtColor(Color ftColor) {
        this.ftColor = ftColor;
    }

    public Color getFtColor() {
        return ftColor;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
