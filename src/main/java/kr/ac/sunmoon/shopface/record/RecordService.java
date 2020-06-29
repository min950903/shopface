package kr.ac.sunmoon.shopface.record;

import java.util.List;

import kr.ac.sunmoon.shopface.businessman.branch.Branch;
import kr.ac.sunmoon.shopface.work.schedule.Schedule;

public interface RecordService {
	public boolean addRecord(Schedule schedule);
	public List<Record> getRecordList(Record record, Branch branch);
	public boolean editRecord(Record record);
	public boolean removeRecord(Record record);
	public boolean qutting(Schedule schedule);
}
