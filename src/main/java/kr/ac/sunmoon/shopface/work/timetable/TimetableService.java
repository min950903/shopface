package kr.ac.sunmoon.shopface.work.timetable;

import java.util.List;

import kr.ac.sunmoon.shopface.work.TimetableSchedule;
import kr.ac.sunmoon.shopface.work.schedule.Schedule;

public interface TimetableService {
	public boolean addTimetable(Timetable timetable, Schedule schedule);
	public List<TimetableSchedule> getTimetableList(int branchNo);
	public boolean editTimetable(Timetable timetable, Schedule schedule);
	public boolean removeTimetable(Schedule schedule);
}