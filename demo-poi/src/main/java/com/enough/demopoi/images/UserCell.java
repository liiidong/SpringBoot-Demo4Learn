package com.enough.demopoi.images;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: liiidong
 * @create: 2020/05/05
 */
public class UserCell {
    private Cell cell;
    private int row;
    private int col;
    private boolean show;

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean getShow() {
        return show;
    }
}
