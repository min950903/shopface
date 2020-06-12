package kr.ac.sunmoon.shopface.employ;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("employ")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employ {
    private int no;
    private String memberId;
    private String name;
    private int branchNo;
    private int salary;
    private String cretiCode;
    private Date employeeDate;
    private Date closeDate;
    private String state;
    private String email;
    private String phone;
    private String bankName;
    private long accountNum;
    
}
