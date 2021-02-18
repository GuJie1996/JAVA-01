package com.example.demo;

import io.kimmking.aop.ISchool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
@Data
public class DemoApplication {

    @Autowired
    public void DemoApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        ISchool iSchool = applicationContext.getBean(ISchool.class);
        System.out.println(iSchool);

        DemoApplication demoApplication = applicationContext.getBean(DemoApplication.class);
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // JDBC原生
        crudByJDBC(demoApplication);
        // tx, preparedStatement
        crudByTransaction(demoApplication);
        // HikariCP
        crudByHikariCP(demoApplication);
    }

    private static void crudByHikariCP(DemoApplication demoApplication) {
        try (Connection con = demoApplication.getDataSource().getConnection();
             PreparedStatement retrieveStatement = con.prepareStatement("select * from foo where id = ?")) {
            retrieveStatement.setInt(1, 5);
            ResultSet rs = retrieveStatement.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("id:%d, bar:%s", rs.getInt("id"), rs.getString("bar")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void crudByTransaction(DemoApplication demoApplication) {
        try (Connection con = DriverManager.getConnection(demoApplication.getUrl(), demoApplication.getUsername(), demoApplication.getPassword());
             PreparedStatement createStatement = con.prepareStatement("insert into foo values (?,?)");
             PreparedStatement retrieveStatement = con.prepareStatement("select * from foo");
             PreparedStatement updateStatement = con.prepareStatement("update foo set bar = ? where id = ?");
             PreparedStatement deleteStatement = con.prepareStatement("delete from foo where id = ?")) {
            con.setAutoCommit(false);

            createStatement.setInt(1, 4);
            createStatement.setString(2, "ddd");
            createStatement.addBatch();
            createStatement.setInt(1, 5);
            createStatement.setString(2, "eee");
            createStatement.addBatch();
            createStatement.executeBatch();

            updateStatement.setString(1, "dddd");
            updateStatement.setInt(2, 3);
            updateStatement.execute();

            deleteStatement.setInt(1, 2);
            deleteStatement.execute();

            ResultSet rs = retrieveStatement.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("id:%d, bar:%s", rs.getInt("id"), rs.getString("bar")));
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void crudByJDBC(DemoApplication demoApplication) {
        try (Connection con = DriverManager.getConnection(demoApplication.getUrl(), demoApplication.getUsername(), demoApplication.getPassword());
             Statement st = con.createStatement();) {
            st.execute("insert into foo values (3, 'ccc')");
            st.execute("delete from foo where id = 1");
            st.execute("update foo set bar = 'cccc' where id = 2");
            ResultSet rs = st.executeQuery("select * from foo");
            while (rs.next()) {
                System.out.println(String.format("id:%d, bar:%s", rs.getInt("id"), rs.getString("bar")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
