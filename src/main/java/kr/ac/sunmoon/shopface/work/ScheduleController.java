package kr.ac.sunmoon.shopface.work;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;
	
}
