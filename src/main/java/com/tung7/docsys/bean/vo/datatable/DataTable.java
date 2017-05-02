package com.tung7.docsys.bean.vo.datatable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * datatable 1.10对应dto
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */

public class DataTable<T> implements Serializable {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;

    private int start;
    private int length;

    @JsonProperty(value = "search")
    private DataTableSearch search;
//    private Map<String,String> search = new HashMap<>();

    @JsonProperty(value = "order")
    private ArrayList<DataTableOrder> order = new ArrayList<>();

    @JsonProperty(value = "columns")
    private ArrayList<DataTableColumns> columns = new ArrayList<>();

    public ArrayList<DataTableColumns> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<DataTableColumns> columns) {
        this.columns = columns;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataTableSearch getSearch() {
        return search;
    }

    public void setSearch(DataTableSearch search) {
        this.search = search;
    }

    public ArrayList<DataTableOrder> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<DataTableOrder> order) {
        this.order = order;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

}
