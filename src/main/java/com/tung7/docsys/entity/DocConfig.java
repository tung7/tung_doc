package com.tung7.docsys.entity;

import javax.persistence.*;

/**
 * 系统配置实体类
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@Entity
@Table(name = "tdoc_config")
public class DocConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ckey", unique = true)
    private String key;
    @Column(name = "cvalue", length = 1000)
    private String value;

    /**
     * The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected.
     */
    protected DocConfig() {}

    public DocConfig(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public DocConfig setId(Long id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public DocConfig setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DocConfig setValue(String value) {
        this.value = value;
        return this;
    }
}
