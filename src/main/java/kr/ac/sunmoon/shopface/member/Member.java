package kr.ac.sunmoon.shopface.member;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Alias(value = "member")
public class Member {
	private String id;
	private String name;
	private String password;
	private String phone;
	private String email;
	private String bankName;
	private Long accountNum;
	private LocalDateTime registerDate;
	private char state;
	private char type;
	private String address;
	private String detailAddress;
	private String zipCode;
}
