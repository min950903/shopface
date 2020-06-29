package kr.ac.sunmoon.shopface.work.schedule;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {
	private final ScheduleMapper scheduleMapper;

	@Override
	public List<Test> getInfo() {
		List<Test> list = this.scheduleMapper.selectView();
		
		return list;
	}
}
