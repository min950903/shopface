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
		return null;
	}

	@Override
	public Schedule getSchedule(Schedule schedule) {
		return null;
	}

	@Override
	public boolean editSchedule(Schedule schedule) {
		return false;
	}

	@Override
	public boolean removeSchedule(Schedule schedule) {
		return false;
	}
	
}
