package org.ashe.view.controller;

import org.ashe.domain.entity.SysUser;
import org.ashe.domain.vo.resp.RespBody;
import org.ashe.service.LoginService;
import org.ashe.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LogController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private LoginService loginService;

    @GetMapping("/hello")
    public RespBody<Object> hello() {
        return RespBody.ok(sysUserService.hello());
    }

    /**
     * 登录
     */
    @PostMapping("/user/login")
    public RespBody<Void> login(@RequestBody SysUser user){
        return loginService.login(user);
    }

    /**
     * 注销
     */
    @PostMapping("/user/logout")
    public RespBody<Object> logout(){
        return loginService.logout();
    }

}
