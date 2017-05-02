package com.tung7.docsys.bean.vo.datatable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */
public class DataTableColumns {
    private String data;
    private String name;
    private boolean searchable;
    private boolean orderable;
    @JsonProperty(value = "search")
    private DataTableSearch search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public DataTableSearch getSearch() {
        return search;
    }

    public void setSearch(DataTableSearch search) {
        this.search = search;
    }
}
