package kr.ac.sunmoon.shopface.alarm;

import java.time.LocalDate;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Alias(value = "alarm")
public class Alarm {
	private int no;
	private String addresseeID;
	private String type;
	private String contents;
	private LocalDate registerDate;
	private char checkState;
}
