package kr.ac.sunmoon.shopface.work.schedule;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {
	public void insert(Schedule schedule);
	public List<Schedule> selectAll(Schedule schedule);
	public Schedule select(Schedule schedule);
	public void update(Schedule schedule);
	public void delete(Schedule schedule);
}
