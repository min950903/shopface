package kr.ac.sunmoon.shopface.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
	private boolean isDuplicate;
	private String message;
}
