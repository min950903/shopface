package kr.ac.sunmoon.shopface.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMaker {
	private Criteria criteria;
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 5;
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
		
		int tempEndPage = (int) (Math.ceil(totalCount/ (double) criteria.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		startPage = (endPage - displayPageNum) + 1;
		if (startPage <= 0) {
			startPage = 1;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * criteria.getPageStart() >= totalCount ? false : true;
	}
}
