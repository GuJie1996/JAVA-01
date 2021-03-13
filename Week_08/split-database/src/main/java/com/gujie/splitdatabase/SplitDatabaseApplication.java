package com.gujie.splitdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
public class SplitDatabaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SplitDatabaseApplication.class, args);
        DataSource dataSource = context.getBean(DataSource.class);
        ResultSet rs = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement1 = connection.prepareStatement("INSERT INTO `order`(`count`, `discount`, `amount`, `user_id`, `goods_snapshot_id`, `gmt_create`, `gmt_modified`) VALUES (2, 0.1, 1.9, 1, 1, ?, ?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statement2 = connection.prepareStatement("select * from `order`");
             PreparedStatement statement3 = connection.prepareStatement("update `order` set `discount` = 0.01 where `id` = ? ");
             PreparedStatement statement4 = connection.prepareStatement("delete from `order` where `id` = ? ");
        ) {
            for (int i=0; i<100; i++) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                statement1.setTimestamp(1, timestamp);
                statement1.setTimestamp(2, timestamp);
                statement1.execute();
            }
            try(ResultSet resultSet = statement1.getGeneratedKeys()) {
                while (resultSet.next()) {
                    long key = resultSet.getLong(1);
                    statement3.setLong(1, key);
                    statement3.execute();
                    statement4.setLong(1, key);
                    statement4.execute();
                }
            }
            rs = statement2.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getBigDecimal("discount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
