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
	private String memberId;
	private String name;
	private String phone;
	private String registerDate;
	private String address;
	private String detailAddress;
	private String zipCode;
	private String businessLPath;
	private char state;
	private char approvalStatus;
}
