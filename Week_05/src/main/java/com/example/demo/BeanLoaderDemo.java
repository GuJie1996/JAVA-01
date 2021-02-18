package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.factory.AutowireFactory;
import com.example.demo.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

public class BeanLoaderDemo {

    public static void main(String[] args) {
        // 使用xml装配
        ApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-loader-context.xml");
        // 构造方法
        User userByConstructor = xmlApplicationContext.getBean("user-by-constructor", User.class);
        System.out.println(userByConstructor);
        // 使用静态方法装配
        User userByStaticMethod = xmlApplicationContext.getBean("user-by-static-method", User.class);
        System.out.println(userByStaticMethod);
        // 使用实例方法装配
        User userByInstanceMethod = xmlApplicationContext.getBean("user-by-instance-method", User.class);
        System.out.println(userByInstanceMethod);
        // 自定义factoryBean
        User userByFactoryBean = xmlApplicationContext.getBean("user-by-factory-bean", User.class);
        System.out.println(userByFactoryBean);
        // ServiceLoader
        // 也是个FactoryBean，但是类型是ServiceLoader，从META-INF/services里的配置文件读取实现类
        ServiceLoader<?> serviceLoader = xmlApplicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        for (Object o : serviceLoader) {
            UserFactory userFactory = (UserFactory) o;
            System.out.println(userFactory.createUser());
        }
        // AutowireCapableBeanFactory
        // 自动注入的是AutowireFactory，然后调用了这个bean的方法
        AutowireCapableBeanFactory autowireCapableBeanFactory = xmlApplicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = autowireCapableBeanFactory.createBean(AutowireFactory.class);
        System.out.println(userFactory.createUser());
    }

    public static class Config {
        @Bean
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("张三");
            return user;
        }
    }

}
