package kr.ac.sunmoon.shopface.employ;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployMapper {
    public List<Employ> selectAll(Employ employ);
    public Employ select(Employ employ);
    public void insert(Employ employ);
    public void update(Employ employ);
    public void delete(Employ employ);
    public int findByCertiCode(Employ employ);
}
