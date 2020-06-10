package kr.ac.sunmoon.shopface.businessman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	/**
	 * 지점 등록 폼
	 * */
	@GetMapping("/branch/form")
	public ModelAndView addBranchForm() {
		ModelAndView mav = new ModelAndView("/branch/add.html");
		return mav;
	}
	
	/**
	 * 지점 등록
	 * */
	@PostMapping("/branch")
	public ModelAndView addBranch(RedirectAttributes redirect, Branch branch) {
		ModelAndView mav = new ModelAndView(new RedirectView("/branch"));
		try {
			boolean result = this.branchService.addBranch(branch);
			if (result == true) {
				redirect.addAttribute("result", "addSuccess");
				return mav;				
			} else {
				redirect.addAttribute("result", "addFail");
				return mav;
			}
		} catch(Exception e) {
			redirect.addAttribute("result", "addFail");
			return mav;
		}
	}
	
	/**
	 * 지점 목록 조회 폼 
	 **/
	@GetMapping(value = "/branch", consumes="application/json")
	public ModelAndView getBranchList(@RequestParam(value = "result", required = false, defaultValue = "none") String result) {
		ModelAndView mav = new ModelAndView("/branch/list.html");
		mav.addObject("result", result);
		return mav;
	}
	
	/**
	 * 지점 목록 조회
	 * */
	@GetMapping("/branch/{businessmanId}")
	public ModelAndView getBranch(@PathVariable("businessmanId") String businessmanId, char approvalStatus) {
		return null;
	}
	
	/**
	 * 지점 수정
	 * */
	@PutMapping("/branch/{no}")
	public ModelAndView editBranch(Branch branch) {
		return null;
		
	}
	
	/**
	 * 지점 삭제
	 * */
	@DeleteMapping("/branch/{no}")
	public ModelAndView removeBranch(Branch branch) {
		ModelAndView mav = new ModelAndView(new RedirectView("/branch"));
		try {
			
			boolean result = this.branchService.removeBranch(branch);
			
		} catch(Exception e) {
			
		}
		return null;
	}
}
