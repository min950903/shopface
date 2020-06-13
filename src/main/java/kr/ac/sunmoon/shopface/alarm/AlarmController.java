package kr.ac.sunmoon.shopface.alarm;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class AlarmController {
	private final AlarmService alarmService;
	
	@GetMapping(value = "/alarm", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Alarm> getAlarmList(@RequestParam("addresseeId") String addresseeId) {
		Alarm alarm = new Alarm();
		alarm.setAddresseeID(addresseeId);
		
		return alarmService.getAlarmList(alarm);
	}
	
	@PutMapping("/alarm")
	public ResponseEntity readAlarm() {
		return null;
	}
}
