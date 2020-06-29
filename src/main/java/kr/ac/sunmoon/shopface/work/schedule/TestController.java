package kr.ac.sunmoon.shopface.work.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	private final TestService testService;
	
	
	@GetMapping("/test")
	public ModelAndView getList() {
		ModelAndView mav = new ModelAndView("schedule/test");
		return mav;
	}
	
	@GetMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Map<String,Object>> getList(HttpServletRequest request) {
	      HashMap<String, Object> map = new HashMap<String, Object>();
	      List<Test> list = new ArrayList<>();
	      list = this.testService.getInfo();
	         map.put("title", list.get(0).getBranch().getName());
	         map.put("start", list.get(0).getTimetable().getWorkStartTime());
	         map.put("end", list.get(0).getTimetable().getWorkEndTime());
	      return new ResponseEntity<>(map,HttpStatus.OK);
	   }
	

}
 













