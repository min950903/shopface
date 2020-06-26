package kr.ac.sunmoon.shopface.alarm;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlarmServiceImpl implements AlarmService {
	private final AlarmMapper alarmMapper;
	
	@Transactional
	@Override
	public boolean addAlarm(Alarm alarm) {
		boolean isSuccess = false;
		
		if (!"".equals(alarm.getAddresseeId()) 
				&& !"".equals(alarm.getType()) 
				&& !"".equals(alarm.getContents())) {
			alarmMapper.insert(alarm);
			
			isSuccess = true;
		}
		
		return isSuccess;
	}

	@Transactional
	@Override
	public List<Alarm> getAlarmList(Alarm alarm) {
		return alarmMapper.selectAll(alarm);
	}

	@Transactional
	@Override
	public boolean updateAlarm(Alarm alarm) {
		Alarm existAlarm = alarmMapper.select(alarm);
		if (existAlarm != null) {
			alarmMapper.update(alarm);
			
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean removeAlarm(Alarm alarm) {
		boolean isSuccess = false;
		
		Alarm existAlarm = alarmMapper.select(alarm);
		if (existAlarm != null) {
			alarmMapper.delete(alarm);
			
			isSuccess = true;
		} else {
			isSuccess = false;
		}
		
		return isSuccess;
	}
}
