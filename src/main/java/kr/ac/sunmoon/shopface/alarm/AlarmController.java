package kr.ac.sunmoon.shopface.alarm;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
public class AlarmController {
	private final AlarmService alarmService;
	
	@GetMapping(value = "/alarm", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Alarm> getAlarmList(@RequestParam("addresseeId") String addresseeId) {
		Alarm alarm = new Alarm();
		alarm.setAddresseeId(addresseeId);
		
		return alarmService.getAlarmList(alarm);
	}
	
	@PostMapping(value = "/alarm", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlarmMessage> addAlarm(Alarm alarm) {
		AlarmMessage message = new AlarmMessage();
		
		if (alarmService.addAlarm(alarm)) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@PutMapping(value = "/alarm", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlarmMessage> readAlarm(Alarm alarm) {
		AlarmMessage message = new AlarmMessage();
		
		if (alarmService.updateAlarm(alarm)) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/alarm", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlarmMessage> removeAlarm(Alarm alarm) {
		AlarmMessage message = new AlarmMessage();
		
		if (alarmService.removeAlarm(alarm)) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
