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
import org.springframework.web.servlet.view.RedirectView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RequiredArgsConstructor
@RestController
public class TimetableController {
	private final TimetableService timetableService;
	
	/**
	 * 시간표 등록
	 * */
	@PostMapping("/timetable")
	public ModelAndView addTimetable(Timetable timetable, Schedule schedule) {
		ModelAndView mav = new ModelAndView(new RedirectView("/timetable"));
		return mav;
	}
	
	/**
	 * 시간표 화면 출력
	 * */
	@GetMapping("/timetable")
	public ModelAndView getTimetable() {
		ModelAndView mav = new ModelAndView("/timetable/list.html");
		return mav;
	}
	
	/**
	 * 시간표 목록 조회
	 * */
	@GetMapping(value = "/timetable", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<TimetableSchedule> getTimetable(Timetable timetable, Schedule schedule) {
		List<TimetableSchedule> timetableSchedule = new ArrayList<TimetableSchedule>();
		return null;
	}
	
	/**
	 * 시간표 수정
	 * */
	@PutMapping("/timetable/{no}")
	public ModelAndView editTimetable(Timetable timetable, Schedule schedule) {
		ModelAndView mav = new ModelAndView(new RedirectView("/timetable"));
		return mav;
	}
	
	/**
	 * 시간표 삭제
	 * */
	@DeleteMapping("/timetable/schedule{no}")
	public ModelAndView removeTimetable(Schedule schedule) {
		ModelAndView mav = new ModelAndView(new RedirectView("/timetable"));
		return mav;
	}
}