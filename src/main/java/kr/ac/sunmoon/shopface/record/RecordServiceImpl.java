package kr.ac.sunmoon.shopface.record;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {
	private final RecordMapper recordMapper;
	
	@Transactional
	@Override
	public boolean addRecord(Record record) {
		boolean isSuccess = false;
		
		if (!"".equals(record.getBranchName()) 
				&& !"".equals(record.getBranchPhone()) 
				&& !"".equals(record.getBusinessmanId()) 
				&& !"".equals(record.getBusinessmanName()) 
				&& !"".equals(record.getMemberId()) 
				&& !"".equals(record.getMemberName()) 
				&& record.getWorkStartTime() != null 
				&& record.getWorkEndTime() != null 
				&& record.getWorkingTime() != null 
				&& record.getSalaryPlan() > 0) {
			recordMapper.insert(record);
			
			isSuccess = true;
		}
		
		return isSuccess;
	}

	@Transactional
	@Override
	public List<Record> getRecordList(Record record) {
		if (!"".equals(record.getMemberId())) {
			return recordMapper.selectAll(record);
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public boolean editRecord(Record record) {
		boolean isSuccess = false;
		
		if (recordMapper.select(record) != null) {
			recordMapper.update(record);
			
			isSuccess = true;
		}
		
		return isSuccess;
	}

	@Transactional
	@Override
	public boolean removeRecord(Record record) {
		boolean isSuccess = false;
		
		if (recordMapper.select(record) != null) {
			recordMapper.delete(record);
			
			isSuccess = true;
		}
		
		return isSuccess;
	}
	
}
