package com.fw.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: 进击的烧年.
 * @Date: 2020/10/8 16:19
 * @Description:  自动填充: 项目中经常会遇到一些数据，每次都使用相同的方式填充，例如记录的创建时间，更新时间等。
 * 我们可以使用MyBatis Plus的自动填充功能，完成这些字段的赋值工作
 *
 * 不要忘记添加 @Component 注解
 *
 * 特别说明:
 * 支持的数据类型只有 int,Integer,long,Long,Date,Timestamp,LocalDateTime
 * 整数类型下 newVersion = oldVersion + 1
 * newVersion 会回写到 entity 中
 * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
 * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
 *
 * 乐观锁
 * 主要适用场景：当要更新一条记录的时候，希望这条记录没有被别人更新，也就是说实现线程安全的数据更新
 * 乐观锁实现方式：
 * 取出记录时，获取当前version
 * 更新时，带上这个version
 * 执行更新时， set version = newVersion where version = oldVersion
 * 如果version不对，就更新失败
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("inser fill");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        //乐观锁
        this.setFieldValByName("version", 1, metaObject);
        //添加deleted的insert默认值
        this.setFieldValByName("deleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("update fill");
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
