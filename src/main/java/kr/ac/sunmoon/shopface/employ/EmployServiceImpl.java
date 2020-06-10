package kr.ac.sunmoon.shopface.employ;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployServiceImpl implements EmployService {
    private final EmployMapper employMapper;
    
    @Override
    public boolean addEmploy(Employ employ) {
        return false;
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
        if(null != employ) {
            if(employ.getState() != null) {
                String state = employ.getState();
                switch (state) {
                case "합류전":
                    state = "B";
                    break;
                case "합류완료":
                    state = "C";
                    break;
                case "비활성화":
                    state = "D";
                    break;
                }
                employ.setState(state);
            }
            
            employMapper.update(employ);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean deleteEmploy(Employ employ) {
        return false;
    }

    @Override
    public String createAuthCode() {
        return null;
    }

    @Override
    public boolean verificationAuthCode(Employ employ) {
        return false;
    }

    @Override
    public SimpleMailMessage createInviteMessage(Employ employ) {
        return null;
    }

    @Override
    public boolean sendInviteMessage(Employ employ) {
        return false;
    }
}
