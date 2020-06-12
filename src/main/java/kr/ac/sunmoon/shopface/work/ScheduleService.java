package kr.ac.sunmoon.shopface.work;

import java.util.List;

public interface ScheduleService {

	public List<Schedule> getScheduleList(Schedule schedule);
	public Schedule getSchedule(Schedule schedule);
	public boolean editSchedule(Schedule schedule);
	public boolean removeSchedule(Schedule schedule);
}
