package com.tung7.docsys.support.utils;

import com.tung7.docsys.support.excpetion.UnavailableIdException;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/10.
 * @update
 */

public class ParamUtils {

    public static void checkId(Long id) {
        if (id == null || id < 1)
            throw new UnavailableIdException("参数ID为null, 或者小于1");
    }
}
