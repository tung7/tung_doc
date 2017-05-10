package com.tung7.docsys.service.inf;

import com.tung7.docsys.entity.DocPermission;
import com.tung7.docsys.entity.DocRole;

import java.util.List;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/6.
 * @update
 */

public interface IRoleService {
     List<DocRole> saveAll(Iterable<DocRole> iterable);
}
