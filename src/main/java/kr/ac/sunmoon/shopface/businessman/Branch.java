package kr.ac.sunmoon.shopface.businessman;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Branch {
	private int no;
	private String businessmanId;
	private String name;
	private String phone;
	private Date registerDate;
	private String address;
	private String detailAddress;
	private String zipNo;
	private String businessLicensePath;
	private char state;
	private char approvalStatus;
}
