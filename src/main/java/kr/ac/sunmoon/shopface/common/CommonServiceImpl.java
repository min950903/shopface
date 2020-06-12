package kr.ac.sunmoon.shopface.common;

import org.springframework.stereotype.Service;

import kr.ac.sunmoon.shopface.member.Member;
import kr.ac.sunmoon.shopface.member.MemberMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommonServiceImpl implements CommonService {
	private final MemberMapper memberMapper;
	
	@Override
	public boolean login(Member member) {
		Member existMember = memberMapper.select(member);
		if (existMember != null 
				&& existMember.getPassword().equals(member.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
