package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.AnimalHospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NavigationMapper {

    @Select("SELECT * FROM animal_hospital WHERE fclty_flag_nm = '동물병원'")
    List<AnimalHospital> findAllHospital();

    @Select("SELECT * FROM animal_hospital WHERE fclty_flag_nm = '동물병원' AND ctprvn_klang_nm = #{city}")
    List<AnimalHospital> findHospitalInCity(String city);
}