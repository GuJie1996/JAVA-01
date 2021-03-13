package com.gujie.distributedtransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DistributedTransactionApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DistributedTransactionApplication.class, args);
        DbService service = context.getBean(DbService.class);
        try {
            service.testXA();
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.query();
    }

}
