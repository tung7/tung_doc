package com.tung7.docsys.support.utils;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/12.
 * @update
 */

public class Utils {
    public static Long getNextTaxis(Long taxis) {
        if (taxis == null) {
            return 0L;
        }
        return taxis + 1;
    }
}
