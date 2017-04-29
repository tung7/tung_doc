package com.tung7.docsys.repositories;

import com.tung7.docsys.entity.DocConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
public interface ConfigRepository extends BaseRepository<DocConfig, Long> {
    DocConfig findByKey(String lastName);
}
