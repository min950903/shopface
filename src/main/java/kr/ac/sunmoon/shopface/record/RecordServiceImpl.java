package kr.ac.sunmoon.shopface.record;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.sunmoon.shopface.businessman.branch.Branch;
import kr.ac.sunmoon.shopface.businessman.branch.BranchMapper;
import kr.ac.sunmoon.shopface.employ.Employ;
import kr.ac.sunmoon.shopface.employ.EmployMapper;
import kr.ac.sunmoon.shopface.member.Member;
import kr.ac.sunmoon.shopface.member.MemberMapper;
import kr.ac.sunmoon.shopface.work.schedule.Schedule;
import kr.ac.sunmoon.shopface.work.schedule.ScheduleMapper;
import kr.ac.sunmoon.shopface.work.timetable.Timetable;
import kr.ac.sunmoon.shopface.work.timetable.TimetableMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class RecordServiceImpl implements RecordService {
	private final RecordMapper recordMapper;
	private final ScheduleMapper scheduleMapper;
	private final TimetableMapper timetableMapper;
	private final BranchMapper branchMapper;
	private final EmployMapper employMapper;
	private final MemberMapper memberMapper;
	
	@Transactional
	@Override
	public boolean addRecord(Schedule schedule) {
		boolean isSuccess = false;
		
		Schedule existSchedule = scheduleMapper.select(schedule);
		if (existSchedule != null) {
			Record record = new Record();
			
			existSchedule.setState('P');
			scheduleMapper.update(existSchedule);
			
			Member member = new Member();
			member.setId(existSchedule.getMemberId());
			Member existMember = memberMapper.select(member);
			record.setMemberName(existMember.getName());
			record.setMemberId(existMember.getId());
			record.setMemberPhone(existMember.getPhone());
			
			Timetable existTimetable = timetableMapper.select(existSchedule.getTimetableNo());
			record.setOccupationName(existTimetable.getOccupName());
			record.setWorkStartTime(existTimetable.getWorkStartTime());
			record.setWorkEndTime(existTimetable.getWorkEndTime());
			
			Branch existBranch = branchMapper.select(existTimetable.getBranchNo());
			record.setBranchNo(existBranch.getNo());
			record.setBranchName(existBranch.getName());
			record.setBranchPhone(existBranch.getPhone());
			
			Member businessman = new Member();
			businessman.setId(existBranch.getMemberId());
			Member existBusinessman = memberMapper.select(businessman);
			record.setBusinessmanId(existBusinessman.getId());
			record.setBusinessmanName(existBusinessman.getName());
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long workStart = 0;
			long workEnd = 0;
			try {
				workStart = (simpleDateFormat.parse(existTimetable.getWorkStartTime()).getTime()) / 1000;
				workEnd = (simpleDateFormat.parse(existTimetable.getWorkEndTime()).getTime()) / 1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			double hours = (workEnd - workStart) / (double) 3600; 
			
			Employ employ = new Employ();
			employ.setBranchNo(existBranch.getNo());
			employ.setMemberId(existMember.getId());
			Employ existEmploy = employMapper.selectEmploy(employ);
			record.setSalaryPlan((int)(existEmploy.getSalary() * hours));
			
			recordMapper.insert(record);
			
			Date now = new Date();
			if (now.compareTo(new Date(workStart)) > -1) {
				existSchedule.setState('P');
			} else {
				existSchedule.setState('L');
			}
			
			scheduleMapper.update(existSchedule);
			
			isSuccess = true;
		}
		
		return isSuccess;
	}

	@Transactional
	@Override
	public List<Record> getRecordList(Record record, Branch branch) {
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

	@Override
	public boolean qutting(Schedule schedule) {
		boolean isSuccess = false;
		
		Schedule existSchedule = scheduleMapper.select(schedule);
		if (existSchedule != null) {
			schedule.setState('C');
			scheduleMapper.update(schedule);
			
			Timetable existTimetable = timetableMapper.select(existSchedule.getTimetableNo());
			Record record = new Record();
			record.setWorkStartTime(existTimetable.getWorkStartTime());
			record.setWorkEndTime(existTimetable.getWorkEndTime());
			record.setMemberId(existSchedule.getMemberId());
			
			Record existRecord = recordMapper.select(record);
		
			recordMapper.quittingUpadte(existRecord);
			
			isSuccess = true;
		}
		
		return isSuccess;
	}
}