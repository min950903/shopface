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
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}
}
