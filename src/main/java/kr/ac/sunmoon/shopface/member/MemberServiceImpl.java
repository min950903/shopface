package kr.ac.sunmoon.shopface.member;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;
	
	@Override
	public boolean addMember(Member member) {
		if (member.getId() != null 
				&& member.getPassword() != null
				&& member.getPhone() != null) {
			memberMapper.insert(member);
			
			return true;
		} else {
			return false;			
		}
	}

	@Override
	public List<Member> getMemberList(Member member) {
		return memberMapper.selectAll(member);
	}

	@Override
	public Member getMember(Member member) {
		return memberMapper.select(member);
	}

	@Override
	public boolean editMember(Member member) {
		Member existMember = memberMapper.select(member);
		if (existMember != null) {
			memberMapper.update(member);
			
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeMember(Member member) {
		if (member.getId() != null 
				&& memberMapper.select(member) != null) {
			memberMapper.delete(member);
			
			return true;
		} else {
			return false;
		}
	}
}
