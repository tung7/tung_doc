package com.tung7.docsys.repositories;

import com.tung7.docsys.entity.DocGroup;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/12.
 * @update
 */

public interface GroupRepository extends BaseRepository<DocGroup, Long> {
    @Query("select g from DocGroup g order by g.taxis asc")
    List<DocGroup> findAllOrderByTaxisAsc();

    @Query("select max(g.taxis) from DocGroup g")
    Long findMaxTaxis();
}
