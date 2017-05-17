package com.tung7.docsys.repositories;

import com.tung7.docsys.entity.DocResource;
import com.tung7.docsys.entity.DocRole;
import org.springframework.data.jpa.repository.Query;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/16.
 * @update
 */

public interface ResourceRepository extends BaseRepository<DocResource,Long> {

    @Query("select max(c.taxis) from DocResource c")
    Long findMaxTaxis();
}
