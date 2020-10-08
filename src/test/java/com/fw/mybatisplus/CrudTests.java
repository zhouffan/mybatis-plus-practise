package com.fw.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fw.mybatisplus.entity.User;
import com.fw.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 进击的烧年.
 * @Date: 2020/10/8 16:04
 * @Description:
 */
@SpringBootTest
public class CrudTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInseart() {
        User user = new User();
        user.setName("xxxxx");
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setName("xxxxx");
        user.setId(1L);
        int i = userMapper.updateById(user);
        System.out.println(i);
        System.out.println(user);
    }

    @Test
    public void testOptimisticLocker() {
        //查询
        User user = userMapper.selectById(1314125091000295425L);
        user.setName("oooo");
        //更新
        userMapper.updateById(user);
    }

    /**
     * 测试乐观锁插件 失败
     */
    @Test
    public void testOptimisticLockerFail() {
//查询
        User user = userMapper.selectById(1314125091000295425L);
//修改数据
        user.setName("Helen Yao1");
        user.setEmail("helen@qq.com1");
//模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
        user.setVersion(user.getVersion() - 1);
//执行更新
        userMapper.updateById(user);
    }

    @Test
    public void testSelectById(){
//        System.out.println(userMapper.selectById(1L));
//        System.out.println(userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L)));
        //map中的key对应的是数据库中的列名。例如数据库user_id，实体类是userId，这时map的key需
        //要填写user_id
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        userMapper.selectByMap(map).forEach(System.out::print);
    }

    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1, 5);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        userIPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void testDeleteById(){
        System.out.println(userMapper.deleteById(1314119180022185986L));
//        System.out.println(userMapper.deleteBatchIds(Arrays.asList(1314119180022185986L)));
    }
    @Test
    public void testLogicDelete(){
        System.out.println(userMapper.deleteById(1314116599074004993L));
    }

    @Test
    public void testSelectAll(){
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
//                .eq("name", "xxxxx")
//        .gt("age", 2)
        .likeLeft("name", "lie")
        .inSql("id", "select id from user where id = 1314114944857276417")
        ;
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}
