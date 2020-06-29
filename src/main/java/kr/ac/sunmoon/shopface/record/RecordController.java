package kr.ac.sunmoon.shopface.record;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.sunmoon.shopface.businessman.branch.Branch;
import kr.ac.sunmoon.shopface.work.schedule.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class RecordController {
	private final RecordService recordService;
	
	@GetMapping(value = "/record")
	public ModelAndView getRecordList() {
		return new ModelAndView("work/record/list");
	}
	
	@PostMapping(value = "/working", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecordMessage> addRecord(@RequestBody Schedule schedule) {
		RecordMessage message = new RecordMessage();
		
		if (recordService.addRecord(schedule)) {
			message.setSuccess(true);
			message.setMessage("근무 기록이 등록 되었습니다.");
		} else {
			message.setSuccess(false);
			message.setMessage("근무 기록 등록에 실패하였습니다.");
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@PostMapping(value = "/quitting", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecordMessage> quitting(@RequestBody Schedule schedule) {
		RecordMessage message = new RecordMessage();
		
		if (recordService.qutting(schedule)) {
			message.setSuccess(true);
			message.setMessage("퇴근 기록이 등록 되었습니다.");
		} else {
			message.setSuccess(false);
			message.setMessage("퇴근 기록 등록에 실패하였습니다.");
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping(value = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Record> getRecordList(Record record, Branch branch) {
		return recordService.getRecordList(record, branch);
	}
	
	@PutMapping("/record")
	public ResponseEntity<RecordMessage> editRecord(Record record) {
		RecordMessage message = new RecordMessage();
		
		if (recordService.editRecord(record)) {
			message.setSuccess(true);
			message.setMessage("근무 기록 수정 성공");
		} else {
			message.setSuccess(false);
			message.setMessage("근무 기록 수정 실패");
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/record")
	public ResponseEntity<RecordMessage> removeRecord(Record record) {
		return null;
	}
}
