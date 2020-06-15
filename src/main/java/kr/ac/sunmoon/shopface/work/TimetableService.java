package kr.ac.sunmoon.shopface.work;

import java.util.List;

public interface TimetableService {
	public boolean addTimetable(Timetable timetable, Schedule schedule);
	public List<TimetableSchedule> selectTimetableList(Timetable timetable, Schedule schedule);
	public boolean editTimetable(Timetable timetable, Schedule scheduel);
	public boolean removeTimetable(Schedule schedule);
}