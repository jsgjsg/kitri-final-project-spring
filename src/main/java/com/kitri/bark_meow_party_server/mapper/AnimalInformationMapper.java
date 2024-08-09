package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.AnimalInformation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnimalInformationMapper {
    @Select("SELECT * FROM animal_information")
    List<AnimalInformation> selectAllAnimal();
    @Select("SELECT * FROM animal_information WHERE user_id=#{userId}")
    List<AnimalInformation> selectByAnimalUserId(Long userId);
    @Insert("INSERT INTO animal_information (user_id, name, image, type, year, month, day, gender, neutralization, weight) " +
            "VALUES (#{userId}, #{name}, #{image}, #{type}, #{year}, #{month}, #{day}, #{gender}, #{neutralization}, #{weight})")
    void insertAnimal(AnimalInformation animalInformation);
    @Update("UPDATE animal_information " +
            "SET name = #{name}, image = #{image}, type = #{type}, " +
            "year = #{year}, month = #{month}, day = #{day}, " +
            "gender = #{gender}, neutralization = #{neutralization}, weight = #{weight} " +
            "WHERE id = #{id}")
    void updateAnimalInformation(AnimalInformation animalInformation);
    @Delete("DELETE FROM animal_information WHERE id=#{id}")
    void deleteAnimalInformation(Long id);

}
