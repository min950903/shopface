package kr.ac.sunmoon.shopface.work;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleMapper scheduleMapper;

	@Override
	public List<Schedule> getScheduleList(Schedule schedule) {

		return scheduleMapper.selectAll(schedule);
	}

	
	/*
	@Override
	public Schedule getSchedule(Schedule schedule) {
		return scheduleMapper.select(schedule);
	}

	
	
	
	@Override
	public boolean editSchedule(Schedule schedule) {
		//수정 시 상태 정보 바뀜
		Schedule checkSchedule = scheduleMapper.select(schedule);
		if (checkSchedule != null ) {
				scheduleMapper.update(checkSchedule);
				return true;
		} else {
				return false;
			}
	}
	
	@Override
	public boolean removeSchedule(Schedule schedule) {
		scheduleMapper.delete(schedule);
		//근무중인 근무자가 있을 경우 삭제 못함
		
		return false;
	}
	
	*/

}
