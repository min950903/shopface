package kr.ac.sunmoon.shopface.work.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;

	@GetMapping("/schedule")
	public ModelAndView getScheduleList() {
		ModelAndView mav = new ModelAndView("work/schedule/list");
		return mav;
	}
		
	@GetMapping(value = "/schedule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Test> getList(){
		List<Test> list = this.scheduleService.getInfo();
		return list;
	}

	/*
	 * //스케줄 조회
	 * 
	 * @GetMapping("/schedule/{no}") public ModelAndView
	 * getSchedule(@PathVariable("no") int no) { ModelAndView mav = new
	 * ModelAndView("schedule/detail"); Schedule schedule = new Schedule();
	 * schedule.setNo(no);
	 * mav.addObject("schedule",scheduleService.getSchedule(schedule)); return mav;
	 * }
	 */

}
