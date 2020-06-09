package kr.ac.sunmoon.shopface.member;

import java.util.List;

public interface MemberService {
	public boolean addMember(Member member);
	public List<Member> getMemberList(Member member);
	public Member getMember(Member member);
	public boolean editMember(Member member);
	public boolean removeMember(Member member);
}
