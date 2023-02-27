package org.ashe.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.ashe.domain.dto.LoginUser;
import org.ashe.domain.entity.SysUser;
import org.ashe.domain.vo.error.BusinessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 校验用户是否确认授权
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, username);
        SysUser user = sysUserService.getOne(wrapper);
        // 如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new BusinessException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中

        //封装成UserDetails对象返回
        return new LoginUser(user);
    }
}
