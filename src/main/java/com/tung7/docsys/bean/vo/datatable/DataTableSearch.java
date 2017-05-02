package com.tung7.docsys.bean.vo.datatable;

/**
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */
public class DataTableSearch {
    private String value;
    private boolean regex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }
}
