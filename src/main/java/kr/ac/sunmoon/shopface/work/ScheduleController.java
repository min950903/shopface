package kr.ac.sunmoon.shopface.work;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;

	//목록 조회 폼
	@GetMapping("/schedule")
	public ModelAndView getScheduleList() {
		ModelAndView mav = new ModelAndView("schedule/list");
		//mav.addObject("",);
		return mav;
	}

	
	//목록 조회
	@GetMapping(value = "/schedule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Schedule> getScheduleList(Schedule schedule) {
		return scheduleService.getScheduleList(schedule);
	}

	
	/*
	//스케줄 조회
	@GetMapping("/schedule/{no}")
	public ModelAndView getSchedule(@PathVariable("no") int no) {
		ModelAndView mav = new ModelAndView("schedule/detail");
		Schedule schedule = new Schedule();
		schedule.setNo(no);
		mav.addObject("schedule",scheduleService.getSchedule(schedule));
	   return mav;
	}
	
	//스케줄 수정
	@PutMapping("/schedule/edit/{no}")
	public ModelAndView editSchedule(@PathVariable("no") int no, Schedule schedule) {
		scheduleService.editSchedule(schedule);
		return new ModelAndView(new RedirectView("/schedule"));
	}
	
	//스케줄 삭제
	@DeleteMapping("/schedule/delete/{no}")
	public ModelAndView removeSchedule(@PathVariable("no") int no) {
		Schedule schedule = new Schedule();
		schedule.setNo(no);
		scheduleService.removeSchedule(schedule);
		return new ModelAndView(new RedirectView("/schedule"));
	}
	*/
	
}
