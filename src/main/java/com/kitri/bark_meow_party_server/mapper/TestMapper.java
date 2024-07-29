package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {

    @Select("SELECT * FROM test")
    List<Test> findAll();
}