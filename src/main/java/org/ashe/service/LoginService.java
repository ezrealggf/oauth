package org.ashe.service;

import org.ashe.domain.entity.SysUser;
import org.ashe.domain.vo.resp.RespBody;

public interface LoginService {

    RespBody<Void> login(SysUser user);

    RespBody<Object> logout();
}
