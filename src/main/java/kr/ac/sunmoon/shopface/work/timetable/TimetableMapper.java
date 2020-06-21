package kr.ac.sunmoon.shopface.work.timetable;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimetableMapper {
	public void insert(Timetable timetable);
	public List<Timetable> selectAll(Timetable timetable);
	public void select(int no);
	public void update(Timetable timetable);
	public void delete(Timetable timetable);
}
