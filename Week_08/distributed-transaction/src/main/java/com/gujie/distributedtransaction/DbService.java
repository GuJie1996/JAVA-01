package com.gujie.distributedtransaction;

import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

@Service
public class DbService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DbService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @ShardingTransactionType(value = TransactionType.XA)
    @Transactional
    public void testXA() {
        for (int i=0; i<100; i++) {
            jdbcTemplate.update("INSERT INTO `order`(`count`, `discount`, `amount`, `user_id`, `goods_snapshot_id`, `gmt_create`, `gmt_modified`) VALUES (2, 0.33, 1.9, 1, 1, ?, ?)", new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    preparedStatement.setTimestamp(1, timestamp);
                    preparedStatement.setTimestamp(2, timestamp);
                }
            });
        }
        int a = 1/0;
    }

    public void query() {
        jdbcTemplate.query("select * from `order` where discount = 0.33", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    System.out.println(resultSet.getBigDecimal("discount"));
                }
            }
        });
    }

}
