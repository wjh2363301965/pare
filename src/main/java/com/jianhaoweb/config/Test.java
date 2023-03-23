package com.jianhaoweb.config;
import com.jianhaoweb.entity.Test1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @Auther:剑豪
 * @Date:2023/3/15
 * @VERSON:1.8
 */
public class Test {

    public static void main(String[] args) {
        String url="jdbc:mysql://172.20.52.135:4000/test?useUnicode=true&characterEncoding=utf8&useSSL=false&rewriteBatchedStatements=true";//mysql8的连接字符串，多了时区比之前的5
        String name="root";
        String password="sailing";

        //1.加载驱动
        try {
            List<Test1> list = new ArrayList<>();
            for (int i = 1; i < 100001; i++) {
                Test1 test1 = new Test1();
                test1.setName("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" +
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
                        "KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
                test1.setId(i);
                list.add(test1);
            }

            Class.forName("com.mysql.jdbc.Driver");//在有错误提示的时候光标移到错误处，alt+enter，
            try {
                //2.创建连接
                Connection  connection= DriverManager.getConnection(url,name,password);
                //关闭事务自动提交
                connection.setAutoCommit(false);
                String sql="insert into test.test1 values(?,?)";
                //3.创建命令窗口
//                Statement statement = connection.createStatement();
                PreparedStatement pstm = connection.prepareStatement(sql);
                //4.执行命令窗口里的语句
//                ResultSet resultSet = statement.executeQuery(sql);
                for (Test1 test1 : list) {
                    pstm.setInt(1, test1.getId());
                    pstm.setString(2, test1.getName());
                    pstm.addBatch();
                }
                pstm.executeBatch();
                connection.commit();
                //5.处理返回的结果集
           /*     int id = 0;
                String nam = "";
                String json ="";
                while (resultSet.next()){
                    //打印行的每一列
                     id = resultSet.getInt(1);
                    name = resultSet.getString(2);
                    json = resultSet.getString(3);
                    System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));
                }
                 statement.executeUpdate("insert into test1 (`name`,`json`) VALUES('手机铃声','"+json+"')");*/


//                6.关闭资源
                pstm.close();
//                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
