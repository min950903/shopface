package kr.ac.sunmoon.shopface.member;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		Map<String, Object> map = new HashMap<>();
		map.put("date", LocalDateTime.now());
		
		modelAndView.addObject("data", map);
		
		return modelAndView;
	}
	
}
