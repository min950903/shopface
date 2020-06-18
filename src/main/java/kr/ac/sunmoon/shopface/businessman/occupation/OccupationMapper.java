package kr.ac.sunmoon.shopface.businessman.occupation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OccupationMapper {
    public void insert(Occupation Occupation);
    public List<Occupation> selectAll(Occupation occupation);
    public void update(Occupation occupation);
    public void delete(Occupation occupation);
}
