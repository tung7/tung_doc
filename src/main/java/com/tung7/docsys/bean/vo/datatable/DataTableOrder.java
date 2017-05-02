package com.tung7.docsys.bean.vo.datatable;

/**
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */
public class DataTableOrder {
    private int column;
    private String dir;

    public DataTableOrder(){}

    public DataTableOrder(int column, String dir){
        this.column = column;
        this.dir = dir;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
