package cn.nbmly.party.mapper;

import cn.nbmly.party.model.Volunteer;
import cn.nbmly.party.model.VolunteerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VolunteerMapper {
    long countByExample(VolunteerExample example);

    int deleteByExample(VolunteerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Volunteer record);

    int insertSelective(Volunteer record);

    List<Volunteer> selectByExample(VolunteerExample example);

    Volunteer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Volunteer record, @Param("example") VolunteerExample example);

    int updateByExample(@Param("record") Volunteer record, @Param("example") VolunteerExample example);

    int updateByPrimaryKeySelective(Volunteer record);

    int updateByPrimaryKey(Volunteer record);
}