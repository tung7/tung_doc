package com.tung7.docsys.support;

import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/6.
 * @update
 */
public class PageBean<T> implements Iterable<T>{
    /**
     * start with 1
     */
    private int curPage;
    private long itemSum;
    private int pageSum;
    private int lineSize;
    private List<T> list;

    public PageBean(){
    }

    /**
     * 通过记录起始值和页宽 产生实例
     * @param startIndex 起始记录 start with 0
     * @param lineSize 页宽
     */
    public PageBean(int startIndex, int lineSize){
        this.curPage = calculatePageNum(startIndex, lineSize);
        this.lineSize = lineSize;
    }

//    public PageBean(int curPage, int lineSize){
//        this.curPage = curPage;
//        this.lineSize = lineSize;
//    }

    public int getCurPage() {
        return curPage;
    }

    public PageBean<T> setCurPage(int curPage) {
        this.curPage = curPage;
        return this;
    }

    public long getItemSum() {
        return itemSum;
    }

    public void setItemSum(long itemSum) {
        this.itemSum = itemSum;
        //也就是都有效的时候，计算总页数
        if (lineSize > 0 && itemSum >= 0) {
            this.pageSum = calculatePageSum(lineSize, itemSum);
            //如果计算出来的总页数小于当前页，重设当前页。
            if(pageSum < curPage) curPage = pageSum;
        }
    }

    public int getPageSum() {
        return pageSum;
    }

    public  PageBean<T>  setPageSum(int pageSum) {
        this.pageSum = pageSum;
        return this;
    }

    public int getLineSize() {
        return lineSize;
    }

    public  PageBean<T>  setLineSize(int lineSize) {
        this.lineSize = lineSize;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public  PageBean<T>  setList(List<T> list) {
        this.list = list;
        return this;
    }


    @Override
    public Iterator<T> iterator(){
        return  list.iterator();
    }

    public int size() {return list.size();}

    public static int calculatePageSum(int linesize, long sum) {
        return (int) Math.ceil((double) sum / linesize);
    }

    /**
     * 通过，起始记录和页宽 计算出当前页 （页码从1 开始）
     * @param startIndex 起始记录 start with 0
     * @param linesize 页宽
     * @return
     */
    public static int calculatePageNum(int startIndex, int linesize) {
       return  (startIndex + 1) / linesize + 1;
    }
}
