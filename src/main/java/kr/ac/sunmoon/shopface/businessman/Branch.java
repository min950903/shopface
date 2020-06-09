package kr.ac.sunmoon.shopface.businessman;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Branch {
	private int no;
	private String businessmanId;
	private String name;
	private String phone;
	private Date registerDate;
	private String address;
	private String businessLicensePath;
	private char state;
	private char approvalStatus;
}
