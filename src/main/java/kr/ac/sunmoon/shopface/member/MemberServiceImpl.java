package kr.ac.sunmoon.shopface.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;
	
	@Transactional
	@Override
	public boolean addMember(Member member) {
		if (!member.getId().equals("") 
				&& !member.getName().equals("")
				&& !member.getPassword().equals("")
				&& !member.getPhone().equals("")) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			memberMapper.insert(member);
			
			return true;
		} else {
			return false;			
		}
	}
	
	@Transactional
	@Override
	public boolean checkIdDuplicate(String id) {
		boolean isDuplicate = false;
		
		Member member = new Member();
		member.setId(id);
		
		Member existMember = memberMapper.select(member);
		if (existMember != null) {
			isDuplicate = true;
		} 
		
		return isDuplicate;
	}
	
	@Transactional
	@Override
	public List<Member> getMemberList(Member member) {
		return memberMapper.selectAll(member);
	}

	@Transactional
	@Override
	public Member getMember(Member member) {
		return memberMapper.select(member);
	}

	@Transactional
	@Override
	public boolean editMember(Member member, String oldPassword) {
		Member existMember = memberMapper.select(member);
		if (existMember != null) {
			if (member.getPassword() != null) {
				if (existMember.getPassword().equals(oldPassword)) {
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					member.setPassword(passwordEncoder.encode(member.getPassword()));
					
					memberMapper.update(member);
					
					return true;
				} else {
					return false;
				}
			} else {
				memberMapper.update(member);
				
				return true;
			}
		} else {
			return false;
		}
	}

	@Transactional
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

	@Transactional
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
