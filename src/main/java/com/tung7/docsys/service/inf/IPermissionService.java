package com.tung7.docsys.service.inf;

import com.tung7.docsys.entity.DocPermission;

import java.util.List;
import java.util.Set;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/6.
 * @update
 */

public interface IPermissionService {
    List<DocPermission> saveAll(Iterable<DocPermission> iterable);
}
