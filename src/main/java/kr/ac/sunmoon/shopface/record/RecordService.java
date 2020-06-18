package kr.ac.sunmoon.shopface.record;

import java.util.List;

public interface RecordService {
	public boolean addRecord(Record record);
	public List<Record> getRecordList(Record record);
	public boolean editRecord(Record record);
	public boolean removeRecord(Record record);
}
