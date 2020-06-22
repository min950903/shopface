package kr.ac.sunmoon.shopface.employ;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

public interface EmployService {
    public boolean addEmploy(Employ employ);
    public List<Employ> getEmployList(Employ employ);
    public Employ getEmploy(Employ employ);
    public boolean editEmploy(Employ employ);
    public boolean deleteEmploy(Employ employ);
    public String createAuthCode();
    public String verificationAuthCode(Employ employ);
    public SimpleMailMessage createInviteMessage(Employ employ);
    public boolean sendInviteMessage(Employ employ);
    public boolean resendInviteMessage(Employ employ);
    public boolean checkCertiCode(Employ employ, String expiredDate);
}
