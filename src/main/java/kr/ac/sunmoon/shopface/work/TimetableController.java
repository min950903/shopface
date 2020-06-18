package kr.ac.sunmoon.shopface.work;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
		
		return mav;
	}
	
	/**
	 * 시간표 화면 출력
	 * */
	@GetMapping("/timetable")
	public ModelAndView getTimetable() {
		ModelAndView mav = new ModelAndView("timetable/list");
		return mav;
	}
	
	/**
	 * 시간표 목록 조회
	 * */
	@GetMapping(value = "/timetable", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<TimetableSchedule> getTimetable(Timetable timetable, Schedule schedule) {
		List<TimetableSchedule> timetableSchedule = this.timetableService.selectTimetableList(timetable, schedule);
		return timetableSchedule;
	}
	
	/**
	 * 시간표 수정
	 * */
	@PutMapping("/timetable/{no}")
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
	@DeleteMapping("/timetable/schedule{no}")
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