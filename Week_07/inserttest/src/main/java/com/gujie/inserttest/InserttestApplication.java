package com.gujie.inserttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class InserttestApplication {

    private static final String SQL = "INSERT INTO `order`(`count`, `discount`, `amount`, `user_id`, `goods_snapshot_id`, `gmt_create`, `gmt_modified`) VALUES (2, 0.1, ?, 1, 1, ?, ?)";
    private static final int TOTAL = 100_0000;
    private static final int THREADS = 16;

    /**
     * mysql 从 docker 352133 改成本地 271653 快了一点
     * 使用 batch 降到秒级 5608
     * innodb_autoinc_lock_mode=2 4917 取消自增锁又快了一点，同理数据库索引、外键、触发器之类的也会影响性能
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(InserttestApplication.class, args);
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        CountDownLatch startCountDownLatch = new CountDownLatch(THREADS);
        CountDownLatch endCountDownLatch = new CountDownLatch(THREADS);
        for (int i = 0; i < THREADS; i++) {
            Thread thread = new Thread(()->{
                insertTest(TOTAL/THREADS, dataSource, startCountDownLatch);
                endCountDownLatch.countDown();
            }, String.format("插入线程%d", i));
            thread.start();
        }
        try {
            startCountDownLatch.await();
            long startMillis = System.currentTimeMillis();
            endCountDownLatch.await();
            long endMillis = System.currentTimeMillis();
            System.out.printf("总共耗时%d\n", endMillis - startMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void insertTest(int count, DataSource dataSource, CountDownLatch startCountDownLatch) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            startCountDownLatch.countDown();
            startCountDownLatch.await();
            for (int i = 0; i < count; i++) {
                String name = String.format("%s:%d", Thread.currentThread().getName(), count);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                statement.setDouble(1, i);
                statement.setTimestamp(2, timestamp);
                statement.setTimestamp(3, timestamp);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
