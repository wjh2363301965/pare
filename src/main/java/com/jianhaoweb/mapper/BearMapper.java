package com.jianhaoweb.mapper;

import com.jianhaoweb.entity.Bear;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther:剑豪
 * @Date:2023/3/13
 * @VERSON:1.8
 */
@Repository
public interface BearMapper {

    List<Bear> getAllBears();
}
