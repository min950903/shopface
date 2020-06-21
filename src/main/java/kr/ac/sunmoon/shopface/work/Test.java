package kr.ac.sunmoon.shopface.work;

import kr.ac.sunmoon.shopface.businessman.branch.Branch;
import kr.ac.sunmoon.shopface.work.schedule.Schedule;
import kr.ac.sunmoon.shopface.work.timetable.Timetable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Test {
	private Timetable timetable;
	private Schedule schedule;
	private Branch branch;
}
