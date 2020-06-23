package kr.ac.sunmoon.shopface.work.timetable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.ac.sunmoon.shopface.work.TimetableSchedule;
import kr.ac.sunmoon.shopface.work.schedule.Schedule;
import kr.ac.sunmoon.shopface.work.schedule.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j 
@RequiredArgsConstructor
@Service
public class TimetableServiceImple implements TimetableService {
	private final TimetableMapper timetableMapper;
	private final ScheduleMapper scheduleMapper;
	
	/**
	 * 시간표 등록
	 * */
	@Override
	public boolean addTimetable(Timetable timetable, Schedule schedule) {
		try {
			if (timetable.getWorkStartTime() != null 
					&& "".equals(timetable.getWorkStartTime())
					&& timetable.getWorkEndTime() != null
					&& "".equals(timetable.getWorkEndTime())
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
					schedule.setTimetableNo(timetable.getNo());
					this.scheduleMapper.insert(schedule);
					return true;
					//알람 등록
					
				} else {
					//중복한 시간표의 스케줄 조회
					if (timetables.size() == 1) {
						Schedule parameter = new Schedule();
						parameter.setTimetableNo(timetables.get(1).getNo());
						
						List<Schedule> schedules = this.scheduleMapper.selectAll(parameter);
						//중복하는 스케줄이 존재하는가?
						if (schedules == null) {
							//존재 하지 않을 시 스케줄 등록
							this.scheduleMapper.insert(schedule);
							return true;
						}
						return false;
					}
				} return true;
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
	public List<TimetableSchedule> getTimetableList(int branchNo) {
		List<TimetableSchedule> timetableSchedules = new ArrayList<TimetableSchedule>();
		try {
			//1. 지점 일련 번호를 받았느니 확인
			if (branchNo > 0 
					&& !"".equals(branchNo)) {
				//2. 지점 일련 번호가 있으면 시간표 조회 
				Timetable timetable = new Timetable();
				timetable.setBranchNo(branchNo);
				List<Timetable> timetables = this.timetableMapper.selectAll(timetable);
				//3. 한 시간표와 연관된 스케줄 존재 시 list에 저장
				if (timetables.size() > 0) {
					for (int i = 0; i < timetables.size(); i++) {
						int no = timetables.get(i).getNo();
						
						Schedule parameterSchedule = new Schedule();
						parameterSchedule.setTimetableNo(no);
						
						List<Schedule> schedules = this.scheduleMapper.selectAll(parameterSchedule);
						for (int j = 0; j < schedules.size(); j++) {
							timetableSchedules.add(new TimetableSchedule(timetables.get(i), schedules.get(j)));
						}
					}
					return timetableSchedules;
				} else {
					return timetableSchedules;
				}
			} else {
				return timetableSchedules;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return timetableSchedules;
		} 
	}

	/**
	 * 시간표 수정
	 * */
	@Override
	public boolean editTimetable(Timetable timetable, Schedule schedule) {
		//1. 현재 시간보다 근무 시작 시간이 지났는지 확인
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("YY-MM-DD HH24:MI:SS");
			
			Date currentTime = new Date();
			String current = dateFormat.format(currentTime);
			
			Date today = dateFormat.parse(current);
			
			String startTime = timetable.getWorkStartTime();
			Date workStartTime = dateFormat.parse(startTime);
			
			int compare = today.compareTo(workStartTime);
			if (compare < 0) {
				//2. 근무 시작 시간이 안지났으면 스케줄 목록 조회
				List<Schedule> schedules = this.scheduleMapper.selectAll(schedule);
				//3. 수정하려는 스케줄 이외에 연관된 스케줄이 존재하는지 확인 (스케줄 목록 조회 결과가 개수가 1보다 크면 존재하는것)
				if (schedules != null && schedules.size() == 1) {
					//4. 존재하지 않으면 시간표 수정
					this.timetableMapper.update(timetable);
					//5. 알람 등록
					return true;
				} else if (schedules != null && schedules.size() > 1) {
					//6. 연관된 스케줄 존재하면 수정하려는 시간표를 등록
					this.timetableMapper.insert(timetable);
		
					//7. 새로 등록한 시간표의 일련번호를 조회
					List<Timetable> result = this.timetableMapper.selectAll(timetable);
					if (result != null && result.size() == 1) {
						//8. 새로 등록한 시간표의 일련번호를 전달받은 스케줄에 수정반영
						schedule.setTimetableNo(result.get(1).getNo());
						this.scheduleMapper.update(schedule);
						return true;
						//8. 알람 등록 
					}
					return false;
				}
				return false;
			}
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
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
				schedules = this.scheduleMapper.selectAll(schedule);
				//2. 조회한 스케줄(1개)의 상태값이 등록, 결론인지 확인
				if (schedules != null) {
					if (schedules != null && schedules.size() == 1) {
						if (schedules.get(1).getState() == 'R' || schedules.get(1).getState() == 'B') {
					//3. 등록이나 결론이면 해당 스케줄을 삭제한다.
							this.scheduleMapper.delete(schedule);
					//		4. 해당 스케줄과 같은 시간표에 연관된 스케줄이 존재하는지 확인하기 위해 전달받은 시간표 일련번호로 스케줄 목록 조회
							Schedule parameter = new Schedule();
							parameter.setTimetableNo(schedule.getTimetableNo());
							
							List<Schedule> resultSchedules = this.scheduleMapper.selectAll(parameter);
					//		5. 존재하는 경우 연관된 시간표를 삭제하지 않는다.
							if (resultSchedules != null) {
								if (resultSchedules.size() > 0) {
									return true;
								}
							} else {
								//6. 존재하지 않는 경우 연관된 시간표를 삭제한다.
								this.timetableMapper.delete(new Timetable(schedule.getTimetableNo()));
								return true;
							}
					//이후 알람 등록
						} else {
							//3-1. 삭제하려는 스케줄의 상태값이 등록이나 결론이 아닐 경우 삭제 불가
							return false;
						}
					} else {//전달받은 스케줄이 존재하는지 확인하는데 1개 보다 많거나 없으면 
						return false;
					}					
				}
			}
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}

}