package kr.ac.sunmoon.shopface.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
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
	public List<Test> getList(Test test) {
		List<Test> data = this.testService.getInfo();
		return data;
		
	}
	

}
 