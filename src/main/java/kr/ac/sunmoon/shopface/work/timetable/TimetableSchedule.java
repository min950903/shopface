package kr.ac.sunmoon.shopface.work.timetable;

import kr.ac.sunmoon.shopface.work.schedule.Schedule;
import kr.ac.sunmoon.shopface.work.timetable.Timetable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimetableSchedule {
	private Timetable timetable;
	private Schedule schedule;
}

