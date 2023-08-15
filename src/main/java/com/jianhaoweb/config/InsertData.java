package com.jianhaoweb.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.sql.*;
import java.util.*;


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
        HashMap<String,String>  ak = new HashMap<>();

        //mysql链接
//        String url = "jdbc:mysql://172.20.51.221:3306/mysql?useSSL=false";
        //pg数据库
//        String url = "jdbc:postgresql://172.20.54.215:5432/test";
        //神通数据库
//        String url = "jdbc:oscar://172.20.54.126:2003/OSRDB";
        //oracle数据库
        String url = "jdbc:oracle:thin:@172.20.52.135:1521/XE";
        //南大GBase8S
//        String url = "jdbc:gbasedbt-sqli://172.20.52.50:9088/testdb:GBASEDBTSERVER=gbase01";
        //华为openAauss
//        String url = "jdbc:opengauss://172.20.52.50:5432/postgres";
        //sqlServer
//        String url = "jdbc:sqlserver://172.20.54.225:1433;databaseName=master";
        //瀚高数据库
//        String url = "jdbc:postgresql://172.20.52.135:5866/test";
        //高斯100
//        String url = "jdbc:zenith:@172.20.54.20:1888";

//        String user = "dscg";
        String user = "sys as sysdba";
//        DriverManager.setLoginTimeout(60);
//        String password = "szoscar55";
        String password = "sailing";
        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password );
        props.setProperty("remarks", "true");              // 设置允许获取remarks信息：已知oracle依赖此配置
        props.setProperty("useInformationSchema", "true"); // 设置允许获取table remarks信息：部分mysql可能依赖此配置
        try {
            // 加载驱动
//            Class.forName("com.gbasedbt.jdbc.Driver");
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("oracle.jdbc.OracleDriver");
//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("org.postgresql.Driver");
//            Class.forName("com.oscar.Driver");
//            Class.forName("org.opengauss.Driver");
//            Class.forName("com.highgo.jdbc.Driver");
//            Class.forName("com.huawei.gauss.jdbc.ZenithDriver");

            // 建立连接
            Connection connection = DriverManager.getConnection(url, props);
            DatabaseMetaData md = connection.getMetaData();
            System.out.println(md.getDatabaseProductName());
            System.out.println(md.getDatabaseProductVersion());
            System.out.println(md.getDriverVersion());
            System.out.println(md.getTypeInfo());

            System.out.println("==============================================");
//            String createTableSQL = "DELETE FROM \"DSCG_TEST\" WHERE ID = 1";
            String createTableSQL = "DROP TABLE \"DSCG_TEST\"";
//            String createTableSQL = "CREATE TABLE \"DSCG_TEST\" (ID INT,FIELD CHAR(1))";
         /*   PreparedStatement  ps = connection.prepareStatement(createTableSQL);
            ps.execute();*/
            // 不指定模式名直接创建表
        /*    String insertDataSQL = "INSERT INTO \"DSCG_TEST\" VALUES (1,'a')";
            PreparedStatement  ps2 = connection.prepareStatement(insertDataSQL);
            ps2.execute();*/
         /*   String query = "SELECT * FROM DSCG_TEST LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("field"));
            }*/
            System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓getCatalogs↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
            ResultSet rs2 = md.getCatalogs();

            while (rs2 != null && rs2.next()) {
                System.out.println("getCatalogs方法的TABLE_CAT:   " + rs2.getString("TABLE_CAT"));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>getCatalogs<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            }
            System.out.println("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑getCatalogs↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");

      /*      System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓sysSchemas↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
            ResultSet rs3 = md.getSchemas();

            while (rs3 != null && rs3.next()) {
                System.out.println("sysSchemas方法的TABLE_SCHEM:   " + rs3.getString("TABLE_SCHEM"));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>sysSchemas<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            }
            System.out.println("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑sysSchemas↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
*/
            Long start=System.currentTimeMillis();
            System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓getTables↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
            ResultSet rs = md.getTables(null, null, null, null);

            while (rs != null && rs.next()) {
                System.out.println("TABLE_CAT:   "+rs.getString("TABLE_CAT"));
                System.out.println("TABLE_SCHEM:   "+rs.getString("TABLE_SCHEM"));
                System.out.println("TABLE_NAME:   "+rs.getString("TABLE_NAME"));
                System.out.println("TABLE_TYPE:   "+rs.getString("TABLE_TYPE"));
                System.out.println("REMARKS:   "+rs.getString("REMARKS"));
                System.out.println("TYPE_CAT:   "+rs.getString("TYPE_CAT"));
                System.out.println("TYPE_SCHEM:   "+rs.getString("TYPE_SCHEM"));
                System.out.println("TYPE_NAME:   "+rs.getString("TYPE_NAME"));
                System.out.println("SELF_REFERENCING_COL_NAME:   "+rs.getString("SELF_REFERENCING_COL_NAME"));
                System.out.println("REF_GENERATION:   "+rs.getString("REF_GENERATION"));

                System.out.println(">>>>>>>>>>>>>>>>>>>>getTables<<<<<<<<<<<<<<<<");

            }
            System.out.println("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑getTables↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
            Long end=System.currentTimeMillis();

/*             System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓getColumns↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
           ResultSet columns = md.getColumns(null, null, null, null);
                while (columns.next()){
                    System.out.println("TABLE_CAT:   "+columns.getString("TABLE_CAT"));
                    System.out.println("TABLE_SCHEM:   "+columns.getString("TABLE_SCHEM"));
                    System.out.println("TABLE_NAME:   "+columns.getString("TABLE_NAME"));
                    System.out.println("COLUMN_NAME:   "+columns.getString("COLUMN_NAME"));
                    System.out.println("DATA_TYPE:   "+columns.getString("DATA_TYPE"));
                    System.out.println("TYPE_NAME:   "+columns.getString("TYPE_NAME"));
                    System.out.println("COLUMN_SIZE:   "+columns.getString("COLUMN_SIZE"));
                    System.out.println("BUFFER_LENGTH:   "+columns.getString("BUFFER_LENGTH"));
                    System.out.println("DECIMAL_DIGITS:   "+columns.getString("DECIMAL_DIGITS"));
                    System.out.println("NUM_PREC_RADIX:   "+columns.getString("NUM_PREC_RADIX"));
                    System.out.println("REMARKS:   "+columns.getString("REMARKS"));
                    System.out.println("COLUMN_DEF:   "+columns.getString("COLUMN_DEF"));
                    System.out.println("SQL_DATA_TYPE:   "+columns.getString("SQL_DATA_TYPE"));
                    System.out.println("SQL_DATETIME_SUB:   "+columns.getString("SQL_DATETIME_SUB"));
                    System.out.println("CHAR_OCTET_LENGTH:   "+columns.getString("CHAR_OCTET_LENGTH"));
                    System.out.println("ORDINAL_POSITION:   "+columns.getString("ORDINAL_POSITION"));
                    System.out.println("IS_NULLABLE:   "+columns.getString("IS_NULLABLE"));
//                    System.out.println("SCOPE_CATALOG:   "+columns.getString("SCOPE_CATALOG"));
                    System.out.println("SCOPE_SCHEMA:   "+columns.getString("SCOPE_SCHEMA"));
                    System.out.println("SCOPE_TABLE:   "+columns.getString("SCOPE_TABLE"));
                    System.out.println("SOURCE_DATA_TYPE:   "+columns.getString("SOURCE_DATA_TYPE"));
                    System.out.println("IS_AUTOINCREMENT:   "+columns.getString("IS_AUTOINCREMENT"));
//                    System.out.println("IS_GENERATEDCOLUMN:   "+columns.getString("IS_GENERATEDCOLUMN"));

                      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>getColumns<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
            System.out.println("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑getColumns↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");*/
         /*   String query = "SELECT * FROM \"public\".\"dscg_test\"  LIMIT 1 ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
            }*/
            System.out.println((end-start)/1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }
