package kr.ac.sunmoon.shopface.businessman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class BranchController {
	@Autowired
	private BranchService brunchService;
	
	/**
	 * 지점 등록 폼
	 * */
	@GetMapping("/branch/form")
	public ModelAndView addBranchForm() {
		return new ModelAndView("/branch/add.jsp");
	}
	
	/**
	 * 지점 등록
	 * */
	@PostMapping("/branch")
	public ModelAndView addBranch(Branch branch) {
		return new ModelAndView(new RedirectView("/branch"));
	}
	
	/**
	 * 지점 목록 조회 폼 
	 **/
	@GetMapping(value = "/branch", consumes="application/json")
	public ModelAndView getBranchList() {
		return null;
	}
	
	/**
	 * 지점 목록 조회
	 * */
	@GetMapping("/branch/{id}")
	public ModelAndView getBranch(String businessmanId, char approvalStatus) {
		return null;
	}
	
	/**
	 * 지점 수정
	 * */
	@PutMapping("/branch/{id}")
	public ModelAndView editBranch(Branch branch) {
		return null;
		
	}
	
	/**
	 * 지점 삭제
	 * */
	@DeleteMapping("/branch/{id}")
	public ModelAndView removeBranch(Branch branch) {
		return null;
	}
}
