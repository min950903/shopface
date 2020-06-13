package kr.ac.sunmoon.shopface.employ;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
public class EmployServiceImpl implements EmployService {
    private final EmployMapper employMapper;
    private final JavaMailSender mailSender;

    @Value("${secretKey}")
    private String secretKey;

    @Transactional
    @Override
    public boolean addEmploy(Employ employ) {
        if (employ.getEmail() != null && employ.getName() != null && employ.getBranchNo() != 0) {
            employ.setCertiCode(verificationAuthCode(employ));
            employMapper.insert(employ);

            return sendInviteMessage(employ);
        }

        return false;
    }

    @Override
    public String verificationAuthCode(Employ employ) {
        String certiCode = "";
        while (true) {
            certiCode = createAuthCode();
            employ.setCertiCode(certiCode);

            if (employMapper.select(employ) == null) {
                return certiCode;

            }
        }
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
    public boolean sendInviteMessage(Employ employ) {
        SimpleMailMessage message = createInviteMessage(employ);
        try {
            mailSender.send(message);

            return true;
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public SimpleMailMessage createInviteMessage(Employ employ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        CryptogramImpl cryptogramImpl = new CryptogramImpl(secretKey);
        String encryptDate = null;
        try {
            encryptDate = cryptogramImpl.encrypt(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String contents = "안녕하세요" + employ.getName() + "님 \r\n" + "초대경로와 인증코드 첨부드립니다. \r\n" + "초대경로 : "
                + "http://nabar/check/date?" + encryptDate + "\r\n" + "인증 코드 : " + employ.getCertiCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employ.getEmail());
        message.setSubject("페이스원 초대메시지 안내 메시지");
        message.setText(contents);

        return message;
    }

    @Override
    public List<Employ> getEmployList(Employ employ) {
        return employMapper.selectAll(employ);
    }

    @Override
    public Employ getEmploy(Employ employ) {
        return employMapper.select(employ);
    }

    @Transactional
    @Override
    public boolean editEmploy(Employ employ) {
        if (null != employ) {
            employMapper.update(employ);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean deleteEmploy(Employ employ) {
        employMapper.delete(employ);
        
        return true;
        
    }

    @Transactional
    @Override
    public boolean resendInviteMessage(Employ employ) {
        if (employ.getEmail() != null) {
            Employ savedEmploy = employMapper.select(employ);
            savedEmploy.setEmail(employ.getEmail());
            savedEmploy.setCertiCode(verificationAuthCode(savedEmploy));
            
            if ('D' == savedEmploy.getState()) {
                savedEmploy.setEmployDate(null);
                savedEmploy.setCloseDate(null);

                employMapper.update(savedEmploy);
                savedEmploy.setState('B');
                employMapper.update(savedEmploy);
            }
            
            sendInviteMessage(savedEmploy);
            
            // 알람을 등록한다.
            return true;
        }
        return false;
    }
}