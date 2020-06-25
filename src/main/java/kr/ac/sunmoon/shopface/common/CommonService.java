package kr.ac.sunmoon.shopface.common;

import org.springframework.mail.SimpleMailMessage;

import kr.ac.sunmoon.shopface.employ.Employ;
import kr.ac.sunmoon.shopface.member.Member;

public interface CommonService {
	public boolean login(Member member);
	public boolean checkEmail(Member member);
	public boolean sendInviteMessage(Member member);
	public String createAuthCode();
    public String verificationAuthCode();
    public SimpleMailMessage createInviteMessage(Member member);
}
