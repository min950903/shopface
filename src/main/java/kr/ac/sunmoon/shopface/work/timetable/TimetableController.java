package kr.ac.sunmoon.shopface.work.timetable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.ac.sunmoon.shopface.work.schedule.Schedule;
import kr.ac.sunmoon.shopface.work.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RequiredArgsConstructor
@RestController
public class TimetableController {
	private final TimetableService timetableService;
	private final ScheduleService scheduleService;
	
	/**
	 * 시간표 등록
	 * */
	@PostMapping("/timetable")
	public ModelAndView addTimetable(RedirectAttributes redirect, Timetable timetable, Schedule schedule) {
		ModelAndView mav = new ModelAndView(new RedirectView("/timetable"));
		
		boolean result = this.timetableService.addTimetable(timetable, schedule);
		if (result == true) {
			redirect.addAttribute("result", "addSuccess");
		} else {
			redirect.addAttribute("result", "addFail");
		}
		return mav;
	}
	
	/**
	 * 시간표 화면 출력
	 * */
	@GetMapping("/timetable")
	public ModelAndView getTimetable(@RequestParam(value = "result", required = false, defaultValue = "none") String result) {
		ModelAndView mav = new ModelAndView("work/timetable/list");
		return mav;
	}
	
	/**
	 * 시간표 목록 조회
	 * */
	@GetMapping(value = "/timetable", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<TimetableSchedule> getTimetable(int branchNo) {
		List<TimetableSchedule> timetableSchedule = this.timetableService.getTimetableList(branchNo);
		return timetableSchedule;
	}
	
	/**
	 * 시간표 수정
	 * */
	@PutMapping("/timetable/{timetableNo}")
	public ModelAndView editTimetable(RedirectAttributes redirect, Timetable timetable, Schedule schedule) {
		try {
			boolean result = this.timetableService.editTimetable(timetable, schedule);
			if (result == true) {
				redirect.addAttribute("result", "editSuccess");
			} else {
				redirect.addAttribute("result", "editFail");
			}
		} catch (Exception e) {
			redirect.addAttribute("result", "editFail");
		}
		
		return new ModelAndView(new RedirectView("/timetable"));
	}
	
	/**
	 * 시간표 삭제
	 * */
	@DeleteMapping("/timetable/schedule/{no}")
	public ModelAndView removeTimetable(RedirectAttributes redirect, Schedule schedule) {
		ModelAndView mav = new ModelAndView(new RedirectView("/timetable"));
		try {
			boolean result = this.timetableService.removeTimetable(schedule);
			if (result == true) {
				mav.addObject("result", "deleteSuccess");
			} else {
				mav.addObject("result", "deleteFail");
			}
			
		} catch(Exception e) {
			mav.addObject("result", "deleteFail");
		}
		
		return mav;
	}
}