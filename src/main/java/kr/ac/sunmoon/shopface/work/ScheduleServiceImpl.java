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
		return this.scheduleMapper.selectAll(schedule);
	}

	@Override
	public Schedule getSchedule(Schedule schedule) {
		return this.scheduleMapper.select(schedule);
	}
/*
	@Override
	public void editSchedule(Schedule schedule) {
		this.scheduleMapper.update(schedule);
	}

	@Override
	public void removeSchedule(Schedule schedule) {
		this.scheduleMapper.delete(schedule);
	}
*/	
}
