package org.ashe.service;

import lombok.extern.slf4j.Slf4j;
import org.ashe.domain.dto.LoginUser;
import org.ashe.domain.entity.SysUser;
import org.ashe.domain.util.JwtUtil;
import org.ashe.domain.util.RedisCache;
import org.ashe.domain.vo.resp.RespBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;
    @Resource
    private RestTemplate restTemplate;

    @Override
    public RespBody<Void> login(SysUser user) {
        // 通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUserName(), user.getPassword());

        // AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 如果认证通过，使用user生成jwt jwt存入ResponseResult 返回

        // 如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 获取当前用户的userid
        String userId = loginUser.getUser().getUserId().toString();

        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        // 把完整的用户信息存入redis userid为key 用户信息为value
        redisCache.setCacheObject("login:" + userId, loginUser);

//        log.info("============== welcome to hello ==============");
//        // 调用资源服务器的接口
//        String uri = "http://127.0.0.1:80/resource";
//        restTemplate.getForObject(uri, String.class);

        return RespBody.ok();
    }

    @Override
    public RespBody<Object> logout() {
        // 获取认证对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 拿到登录用户
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getUserId();
        // 删除Redis的token
        redisCache.deleteObject("login:"+userid);
        return RespBody.ok("退出成功");
    }

}
