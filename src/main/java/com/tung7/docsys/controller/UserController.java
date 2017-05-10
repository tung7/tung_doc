package com.tung7.docsys.controller;

import com.tung7.docsys.bean.vo.JsonResult;
import com.tung7.docsys.bean.vo.datatable.DataTable;
import com.tung7.docsys.entity.DocUser;
import com.tung7.docsys.service.inf.IUserService;
import com.tung7.docsys.support.PageBean;
import com.tung7.docsys.support.utils.DocSystemUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/1.
 * @update
 */
@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model, DocUser user) {
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getParameter(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        } else {
            msg = "unkown exception!";
        }
        model.addAttribute("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "content/login";
    }

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.POST)
    @ResponseBody
    public DataTable<DocUser> datatableForUsers(@RequestBody DataTable<DocUser> dt) {
        PageBean<DocUser> pageBean = userService.findAll(new PageBean<>(dt.getStart(), dt.getLength()));

        dt.setData(pageBean.getList());
        dt.setRecordsTotal(new Long(pageBean.getItemSum()).intValue());
        dt.setRecordsFiltered(new Long(pageBean.getItemSum()).intValue());
        return dt;
    }

    @RequestMapping(value = "/admin/user/list_test", method = RequestMethod.GET)
    public JsonResult<DocUser> test(JsonResult<DocUser> jr) {
        jr.setData(DocSystemUtils.getCurrentUser());
        return jr;
    }
}
