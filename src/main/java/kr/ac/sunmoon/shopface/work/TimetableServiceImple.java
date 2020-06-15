package kr.ac.sunmoon.shopface.work;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.sunmoon.shopface.employ.EmployMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j 
@RequiredArgsConstructor
@Service
public class TimetableServiceImple implements TimetableService {
	private final TimetableMapper timetableMapper;
	
	/**
	 * 시간표 등록
	 * */
	@Override
	public boolean addTimetable(Timetable timetable, Schedule schedule) {
		try {
			if (timetable.getWorkStartTime() != null 
					&& timetable.getWorkEndTime() != null
					&& timetable.getOccupName() != null
					&& "".equals(timetable.getOccupName())
					&& timetable.getOccupColor() != null
					&& "".equals(timetable.getOccupColor())
					&& schedule.getMemberId() != null
					&& "".equals(schedule.getMemberId())) {
				List<Timetable> timetables = this.timetableMapper.selectAll(timetable);
				
				if (timetables == null) {
					this.timetableMapper.insert(timetable);//시간표 등록
					// 스케줄 등록
					//알람 등록
				} else {
					//중복한 시간표의 스케줄 조회
					//중복하는 스케줄이 존재하는가?
					//존재 시 스케줄 등록
					//알람 등록
				}
				
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * 시간표 목록 조회
	 * */
	@Override
	public List<TimetableSchedule> selectTimetableList(Timetable timetable, Schedule schedule) {
		List<TimetableSchedule> timetableSchedules = new ArrayList<TimetableSchedule>();
		try {
			//1. 지점 일련 번호를 받았느니 확인
			if (timetable.getBranchNo() > 0 
					&& "".equals(timetable.getBranchNo())) {
				//2. 지점 일련 번호가 있으면 시간표 조회 
				List<Timetable> timetables = this.timetableMapper.selectAll(timetable);
				//3. 한 시간표와 연관된 스케줄 존재 시 list에 저장
				if (timetables.size() > 0) {
					for (int i = 1; i <= timetables.size(); i++) {
						int no = timetables.get(i).getNo();
						
						Schedule parameterSchedule = new Schedule();
						parameterSchedule.setTimetableNo(no);
						
//						List<Schedule> schedules = this.ScheduleMapper.selectAll(parameterSchedule);
//						for (int j = 1; j <= schedules.size(); j++) {
//							timetableSchedules.add(new TimetableSchedule(timetables.get(i), schedules.get(j)));
//						}
					}
					return timetableSchedules;
				} else {
					return timetableSchedules;
				}
			} else {
				return timetableSchedules;
			}
		} catch (Exception e) {
			return timetableSchedules;
		} 
	}

	/**
	 * 시간표 수정
	 * */
	@Override
	public boolean editTimetable(Timetable timetable, Schedule scheduel) {
		//1. 현재 시간보다 근무 시작 시간이 지났는지 확인
		
		//2. 근무 시작 시간이 안지났으면 스케줄 목록 조회
		//3. 수정하려는 스케줄 이외에 연관된 스케줄이 존재하는지 확인 (스케줄 목록 조회 결과가 개수가 1보다 크면 존재하는것)
		//4. 존재하지 않으면 시간표 수정
		//5. 알람 등록
		
		//6. 연관된 스케줄 존재하면 수정하려는 시간표를 등록
		//7. 새로 등록한 시간표의 일련번호를 전달받은 스케줄에 수정반영
		//8. 알람 등록
		return false;
	}

	/**
	 * 시간표 삭제
	 * */
	@Override
	public boolean removeTimetable(Schedule schedule) {
		try {
			//1. 전달받은 일련번호로  스케줄의 상태값 조회를 위해 스케줄 조회
			if (schedule.getNo() > 0
					&& schedule.getTimetableNo() > 0) {
				List<Schedule> schedules = new ArrayList<Schedule>();
				//scheduels = this.scheduleMapper.selectAll(schedule);
				//2. 조회한 스케줄(1개)의 상태값이 등록, 결론인지 확인
				if (schedules != null) {
					//if (schedules != null && schedules.size() == 1) {
					//	if (schedules.get(1).getState() == 'R' || schedules.get(1).getState() == 'B') {
					//3. 등록이나 결론이면 해당 스케줄을 삭제한다.
					//		this.scheduleMapper.delete(schedule);
					//		4. 해당 스케줄과 같은 시간표에 연관된 스케줄이 존재하는지 확인하기 위해 전달받은 시간표 일련번호로 스케줄 목록 조회
//							Schedule parameter = new Schedule();
//							parameter.setTimetableNo(schedule.getTimetableNo());
//							
//							List<Schedule> resultSchedules = this.scheduleMapper.selectAll(parameter);
					//		5. 존재하는 경우 연관된 시간표를 삭제하지 않는다.
//							if (resultSchedules != null) {
//								if (resultSchedules.size() > 0) {
//									return true;
//								}
//							} else {
//								//		6. 존재하지 않는 경우 연관된 시간표를 삭제한다.
//								this.timetableMapper.delete(new Timetable(schedule.getTimetableNo()));
//								return true;
//							}
					//이후 알람 등록
					//	} else {
					//		
					//	}
					//} else {//전달받은 스케줄이 존재하는지 확인하는데 1개 보다 많거나 없으면 
					//	return false;
					//}					
				}
			}
				
						
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}

}