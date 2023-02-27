package org.ashe.my;

import org.ashe.domain.entity.SysUser;
import org.ashe.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void testMysql(){
        List<SysUser> userList = sysUserService.list();
        userList.forEach(System.err::println);
    }
}
