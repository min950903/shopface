package kr.ac.sunmoon.shopface.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestMapper testMapper;
	
	@Override
	public List<Test> getInfo() {
		List<Test> test = this.testMapper.select();
		return test;
	}
}
