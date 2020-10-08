package com.fw.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fw.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;


/**
 * @Author: 进击的烧年.
 * @Date: 2020/10/8 15:54
 * @Description:   @Repository加上，引用时不会报错（可不加）
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
