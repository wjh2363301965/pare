package com.jianhaoweb.config;

import java.sql.*;
import java.util.Random;

public class InsertData {
  /*  public static void main(String[] args) {
        // 数据库连接信息
        String url = "jdbc:mysql://172.20.54.135:4001/test?useUnicode=true&characterEncoding=utf8&useSSL=false&rewriteBatchedStatements=true";
        String user = "root";
        String password = "sailing";

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 建立连接
            Connection connection = DriverManager.getConnection(url, user, password);

            // SQL插入语句
            String sql = "INSERT INTO test1 (var,name) VALUES (?, ?)";

            // 创建PreparedStatement对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 插入10万条数据
            for (int i = 1; i < 10001; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                        "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
                                "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK ");

                // 添加到批处理
                preparedStatement.addBatch();

                // 每1000条执行一次批处理
                if (i % 1000 == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }

            // 执行剩余的批处理
            preparedStatement.executeBatch();

            // 关闭资源
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        String url = "jdbc:mysql://172.20.54.221:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&rewriteBatchedStatements=true";
        String user = "root";
        String password = "123456";

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 建立连接
            Connection connection = DriverManager.getConnection(url, user, password);
            DatabaseMetaData md = connection.getMetaData();
            System.out.println(md.getDatabaseProductName());
            System.out.println(md.getDatabaseProductVersion());
            System.out.println(md.getDriverVersion());
            System.out.println(md.getTypeInfo());
            System.out.println("==============================================");
            md.getTables(null,null,null,null);
            System.out.println("===");


            // SQL插入语句
 /*           String sql = "INSERT INTO test1 (var,name) VALUES (?, ?)";

            // 创建PreparedStatement对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);*/


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    }
