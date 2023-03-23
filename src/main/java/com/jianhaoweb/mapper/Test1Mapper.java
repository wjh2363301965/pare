package com.jianhaoweb.mapper;

import com.jianhaoweb.entity.Test1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangjianhao
 * @since 2023-03-14
 */
@Mapper
public interface Test1Mapper extends BaseMapper<Test1> {


    List<Test1> getjson();

    void save(@Param("jsonStr") String jsonStr);

}
