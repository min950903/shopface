package kr.ac.sunmoon.shopface.record;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordMapper {
	public void insert(Record record);
	public Record select(Record record);
	public List<Record> selectAll(Record record);
	public void update(Record record);
	public void delete(Record record);
}
