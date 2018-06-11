package com.sxt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by shaxueting on 2017/6/5.
 */
@SpringBootApplication

public class App extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
//    打成war包需要添加的代码
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(App.class);
    }
}
