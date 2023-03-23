package com.jianhaoweb;

import com.jianhaoweb.entity.Bear;
import com.jianhaoweb.mapper.BearMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class JianhaowebApplicationTests {

    @Autowired
    private BearMapper bearMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void DataSource() throws SQLException{
        System.out.println(dataSource.getConnection());
    }

    @Test
    void TestGetAll(){
        List<Bear> list = bearMapper.getAllBears();
        System.out.println(list);
    }



}
