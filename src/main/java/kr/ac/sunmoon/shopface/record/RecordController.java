package kr.ac.sunmoon.shopface.record;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class RecordController {
	private final RecordService recordService;
	
	@GetMapping(value = "/record")
	public ModelAndView getRecordList() {
		return new ModelAndView("record");
	}
	
	@PostMapping(value = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecordMessage> addRecord(Record record) {
		RecordMessage message = new RecordMessage();
		
		if (recordService.addRecord(record)) {
			message.setSuccess(true);
			message.setMessage("근무 기록이 등록 되었습니다.");
		} else {
			message.setSuccess(false);
			message.setMessage("근무 기록 등록에 실패하였습니다.");
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping(value = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Record> getRecordList(Record record) {
		return recordService.getRecordList(record);
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
