package kr.ac.sunmoon.shopface.employ;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

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
    public boolean getEmploy(Employ employ) {
        return false;
    }

    @Override
    public boolean editEmploy(Employ employ) {
        return false;
    }

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
