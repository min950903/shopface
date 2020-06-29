package kr.ac.sunmoon.shopface.work.schedule;

import org.apache.ibatis.type.Alias;

import kr.ac.sunmoon.shopface.businessman.branch.Branch;
import kr.ac.sunmoon.shopface.work.schedule.Schedule;
import kr.ac.sunmoon.shopface.work.timetable.Timetable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Alias(value = "test")
public class Test {
	private Timetable timetable;
	private Schedule schedule;
	private Branch branch;
}
