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

	/*목록 조회 폼
	@GetMapping("/schedule")
	public ModelAndView getScheduleList() {
		return new ModelAndView("schedule/list");
	}*/

	
	//목록 조회
	@GetMapping("/schedule")
	public ModelAndView getScheduleList(Schedule schedule) {
		ModelAndView mav = new ModelAndView("schedule/list");
		List<Schedule> list = scheduleService.getScheduleList(schedule);
		mav.addObject("list",list);
		return mav;
	}


	@GetMapping("/schedule/{no}")
	public ModelAndView getSchedule(@PathVariable("no") int no) {
		ModelAndView mav = new ModelAndView("schedule/view");
		Schedule schedule = new Schedule();
		schedule.setNo(no);
		mav.addObject("schedule",scheduleService.getSchedule(schedule));
	   return mav;
	}
	/*
	@GetMapping("/schedule{no}")
	public ModelAndView editSchedule(@PathVariable("no") int no, Schedule schedule) {
		
		ModelAndView mav = new ModelAndView("/schedule/edit");
		mav.addObject("schedule",this.scheduleService.getSchedule(schedule));
		return mav;
	}
	@PutMapping("/schedule{no}")
	public ModelAndView editSchedule(Schedule schedule) {
		this.scheduleService.editSchedule(schedule);
		return new ModelAndView(new RedirectView("/schedule/list"));
	}
	
	@DeleteMapping("/schedule{no}")
	public ModelAndView removeSchedule(@PathVariable("no") int no,Schedule schedule) {
		this.scheduleService.removeSchedule(schedule);
		return new ModelAndView(new RedirectView("/schedule"));
	}
	
	*/
}
