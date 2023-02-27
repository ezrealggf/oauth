package org.ashe.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.ashe.domain.entity.SysUser;
import org.ashe.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Resource
    private RestTemplate restTemplate;

    @Resource
    @SuppressWarnings("all")
    private SysUserMapper sysUserMapper;

    @Override
    public String hello() {
        log.info("============== welcome to hello, then you can go resource ==============");
        // 调用资源服务器的接口
        String uri = "http://127.0.0.1:80/resource";
        return restTemplate.getForObject(uri, String.class);
    }
}
