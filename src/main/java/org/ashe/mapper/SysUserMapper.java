package org.ashe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ashe.domain.entity.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    
}
