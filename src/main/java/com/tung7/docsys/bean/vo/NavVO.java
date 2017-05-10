package com.tung7.docsys.bean.vo;


import java.util.ArrayList;
import java.util.List;

/**
 * 顶级类别的导航菜单栏
 * 若是链接菜单类型，则有属性href。
 * 若是非链接菜单，href无效（为null）。
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */
public class NavVO {

    /**
     * id值
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;



    /**
     * 链接菜单对应的地址
     */
    private String href;

    /**
     * 非链接菜单对应的子菜单
     */
    private List<NavVO> subNavs = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public NavVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public NavVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getHref() {
        return href;
    }

    public NavVO setHref(String href) {
        this.href = href;
        return this;
    }

    public List<NavVO> getSubNavs() {
        return subNavs;
    }

    public NavVO setSubNavs(List<NavVO> subNavs) {
        this.subNavs = subNavs;
        return this;
    }

}
