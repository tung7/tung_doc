package com.tung7.docsys.repositories;

import com.tung7.docsys.entity.DocCategory;
import com.tung7.docsys.entity.DocResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.print.Doc;
import java.util.List;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
public interface CategoryRepository extends BaseRepository<DocCategory, Long> {
    DocCategory findByName(String name);

    List<DocCategory> findByParentIsNull();

    @Query("select max(c.taxis) from DocResource c")
    Long findMaxTaxis();

    @Query("select max(c.taxis) from DocResource c where c.parent = :parent")
    Long getMaxTaxis(@Param("parent") DocResource parent);

}
