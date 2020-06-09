package kr.ac.sunmoon.shopface.employ;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Alias("employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employ {
    private int no;
    private String name;
    private int branchNo;
    private String memberId;
    private int salary;
    private String cretiCode;
    private Date employeeDate;
    private Date closeDate;
    private char state;
}
