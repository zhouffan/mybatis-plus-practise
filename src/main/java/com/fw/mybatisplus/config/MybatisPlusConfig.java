package com.fw.mybatisplus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: 进击的烧年.
 * @Date: 2020/10/8 16:41
 * @Description:
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.fw.mybatisplus.mapper")
public class MybatisPlusConfig {
    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 逻辑删除 deleted
     * 测试后发现，数据并没有被删除，deleted字段的值由0变成了1
     * @return
     */
    @Bean
    public ISqlInjector sqlInjecto(){
        return new LogicSqlInjector();
    }


    /**
     * 五、性能分析
     * 性能分析拦截器，用于输出每条 SQL 语句及其执行时间
     * SQL 性能执行分析,开发环境使用，超过指定时间，停止运行。有助于发现问题
     * 1、配置插件
     * （1）参数说明
     * 参数：maxTime： SQL 执行最大时长，超过自动停止运行，有助于发现问题。
     * 参数：format： SQL是否格式化，默认false。
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
//        performanceInterceptor.setMaxTime(100);
        return performanceInterceptor;
    }
}
