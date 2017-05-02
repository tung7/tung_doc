package com.tung7.docsys.support;

import com.tung7.docsys.bean.vo.JsonResult;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * With Spring 3.1.0 the ArgumentResolver has now changed to HandlerMethodArgumentResolver - prior to that it used to be WebArgumentResolver
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */
@Component
public class JsonResultArgumentResult implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter mp) {
        return mp.getParameterType() != null && mp.getParameterType().equals(JsonResult.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return new JsonResult();
    }
}
