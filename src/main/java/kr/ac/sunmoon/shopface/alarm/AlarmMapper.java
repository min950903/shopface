package kr.ac.sunmoon.shopface.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper {
	public List<Alarm> selectAll(Alarm alarm);
	public Alarm select(Alarm alarm);
	public void insert(Alarm alarm);
	public void update(Alarm alarm);
	public void delete(Alarm alarm);
}
