package kr.ac.sunmoon.shopface.work;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;
	private int no;
	private int timetableNo;
	private String memberId;
	private char state;
}
