package kr.ac.sunmoon.shopface.member;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias(value = "criteria")
public class Criteria {
	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPageStart() {
		return (this.page-1) * perPageNum;
	}
	
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	
	public void setPerPageNum(int pageCount) {
		int count = this.perPageNum;
		if (pageCount != count) {
			this.perPageNum = count;
		} else {
			this.perPageNum = pageCount;
		}
	}
}
