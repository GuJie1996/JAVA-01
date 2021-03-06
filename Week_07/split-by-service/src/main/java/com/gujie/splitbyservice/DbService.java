package com.gujie.splitbyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class DbService {

    DataSource dataSource1;
    DataSource dataSource2;

    @Autowired
    public DbService (DataSource dataSource1, DataSource dataSource2) {
        this.dataSource1 = dataSource1;
        this.dataSource2 = dataSource2;
    }

    public void create() {
        try (Connection connection = dataSource1.getConnection();
             PreparedStatement statement1 = connection.prepareStatement("INSERT INTO `order`(`count`, `discount`, `amount`, `user_id`, `goods_snapshot_id`, `gmt_create`, `gmt_modified`) VALUES (2, 0.1, 1.9, 1, 1, ?, ?)");
        ) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            statement1.setTimestamp(1, timestamp);
            statement1.setTimestamp(2, timestamp);
            statement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieve() {
        ResultSet rs = null;
        try (Connection connection = dataSource2.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from `order`");
        ) {
            rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getBigDecimal("amount"));
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
