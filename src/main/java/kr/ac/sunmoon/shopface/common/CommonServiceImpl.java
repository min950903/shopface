package kr.ac.sunmoon.shopface.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.ac.sunmoon.shopface.employ.CryptogramImpl;
import kr.ac.sunmoon.shopface.member.Member;
import kr.ac.sunmoon.shopface.member.MemberMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommonServiceImpl implements CommonService {
	private final MemberMapper memberMapper;
	private final JavaMailSender mailSender;
	
	@Value("${secretKey}")
	private String secretKey;
	
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

	@Override
	public boolean checkEmail(Member member) {
		boolean isSuccess = false;
		if (memberMapper.select(member) != null) {
			sendInviteMessage(member);
			
			isSuccess = true;
		} 
		
		return isSuccess;
	}
	
	@Override
	public boolean sendInviteMessage(Member member) {
		this.mailSender.send(createInviteMessage(member));
		
		return false;
	}
	
	@Override
	public String createAuthCode() {
		Random random = new Random();

        String certiCode = "";
        for (int i = 0; i < 6; i++) {
            certiCode += String.valueOf((char) ((int) (random.nextInt(26)) + 65));
        }
        
        return certiCode;
	}

	@Override
	public String verificationAuthCode() {
		return null;
	}

	@Override
	public SimpleMailMessage createInviteMessage(Member member) {
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        String encryptDate = null;

        try {
            CryptogramImpl cryptogramImpl = new CryptogramImpl(secretKey);
            encryptDate = cryptogramImpl.encrypt(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String contents = "안녕하세요" + "\r\n" + "비밀번호 설정을 위한 코드와 링크 보내드립니다. \r\n" + "링크 : "
                            + "http://localhost:8080/forgotpassword/auth?date=" + encryptDate + "\r\n" + "인증 코드 : " + createAuthCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(member.getEmail());
        message.setSubject("페이스원 비밀번호 찾기 안내 메시지");
        message.setText(contents);

        return message;
	}

}
