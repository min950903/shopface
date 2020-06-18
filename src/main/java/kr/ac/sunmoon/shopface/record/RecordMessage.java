package kr.ac.sunmoon.shopface.record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordMessage {
	private String message;
	private boolean isSuccess;
}
