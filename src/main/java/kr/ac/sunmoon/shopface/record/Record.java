package kr.ac.sunmoon.shopface.record;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Alias(value = "record")
public class Record {
	private int no;
	private String businessmanId;
	private String businessmanName;
	private String branchName;
	private String branchPhone;
	private String memberId;
	private String memberName;
	private String memberPhone;
	private String occupationName;
	private String workStartTime;
	private String workEndTime;
	private String workingTime;
	private String quittingTime;
	private int salaryPlan;
	private int salaryPay;
	private int evaluation;
	private String note;
}
