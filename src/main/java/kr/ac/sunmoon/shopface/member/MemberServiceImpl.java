package kr.ac.sunmoon.shopface.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			member.setPassword(passwordEncoder.encode(member.getPassword()));
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

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Member member = new Member();
		member.setId(id);
		
		Member existMember = memberMapper.select(member);
		
		//TODO-관리자/유저 별 권한 부여 로직 
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		
		return new User(existMember.getId(), existMember.getPassword(), authorities);
	}
}
