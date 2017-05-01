package com.tung7.docsys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

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
@EntityListeners({AuditingEntityListener.class})
public class DocConfig extends IdEntity{
    /**
     * 配置项的key
     */
    @Column(name = "ckey", unique = true)
    private String key;

    /**
     * 配置项的value
     */
    @Column(name = "cvalue", length = 1000)
    private String value;

//    @CreatedBy
//    private DocUser creator;
//
//    @LastModifiedBy
//    private DocUser lastModeiUser;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Column(name = "modified_date")
    private Date modifiedDate;

    /**
     * The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected.
     */
    protected DocConfig() {}

    public DocConfig(String key, String value) {
        this.key = key;
        this.value = value;
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
