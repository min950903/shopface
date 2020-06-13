package kr.ac.sunmoon.shopface.alarm;

import java.util.List;

public interface AlarmService {
	public boolean addAlarm(Alarm alarm);
	public List<Alarm> getAlarmList(Alarm alarm);
	public boolean updateAlarm(Alarm alarm);
	public boolean removeAlarm(Alarm alarm);
}
