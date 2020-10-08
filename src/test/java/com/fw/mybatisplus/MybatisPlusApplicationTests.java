package com.fw.mybatisplus;

import com.fw.mybatisplus.entity.User;
import com.fw.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


}
