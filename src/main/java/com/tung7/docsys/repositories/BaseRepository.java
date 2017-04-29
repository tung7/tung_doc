package com.tung7.docsys.repositories;

import com.tung7.docsys.entity.DocConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@NoRepositoryBean
public interface BaseRepository<T, D extends Serializable> extends JpaRepository<T, D>,  JpaSpecificationExecutor<T> {
}
