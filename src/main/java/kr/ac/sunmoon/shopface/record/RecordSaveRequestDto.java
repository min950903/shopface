package kr.ac.sunmoon.shopface.record;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

//Setter는 필요하지 않다 https://jojoldu.tistory.com/407
@Getter
@NoArgsConstructor
public class RecordSaveRequestDto {
	private int no;

	@NotBlank(message = "BusinessmanID is mandatory")
	private String businessmanId;
	
	@NotBlank(message = "BusinessmanName is mandatory")
	private String businessmanName;
	
	@NotBlank(message = "BranchName is mandatory")
	private String branchName;
	
	@NotBlank(message = "BranchPhone is mandatory")
	private String branchPhone;
	
	@NotBlank(message = "MemberID is mandatory")
	private String memberId;
	
	@NotBlank(message = "MemberName is mandatory")
	private String memberName;
	
	@NotBlank(message = "Work Start Time is mandatory")
	private LocalDateTime workStartTime;
	
	@NotBlank(message = "Work End Time is mandatory")
	private LocalDateTime workEndTime;
	
	@NotBlank(message = "Working Time is mandatory")
	private LocalDateTime workingTime;
	
	@NotBlank(message = "Salary(Plan) is mandatory")
	private int salaryPlan;
}
