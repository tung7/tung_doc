package com.tung7.docsys.rest;

import com.tung7.docsys.entity.DocUser;
import com.tung7.docsys.service.inf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/1.
 * @update
 */
@RestController
@RequestMapping("/api/user")
public class UserRest {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public DocUser save(DocUser user) {
        return userService.save(user);
    }

}
