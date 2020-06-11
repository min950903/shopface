package kr.ac.sunmoon.shopface.businessman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RequiredArgsConstructor
@RestController
public class BranchController {
	private final BranchService branchService;
	
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
			} else {
				redirect.addAttribute("result", "addFail");
			}
		} catch(Exception e) {
			redirect.addAttribute("result", "addFail");
		} finally {
			return mav;
		}
	}
	
	/**
	 * 지점 목록 조회 폼 
	 **/
	@GetMapping(value = "/branch")
	public ModelAndView getBranchList(@RequestParam(value = "result", required = false, defaultValue = "none") String result) {
		ModelAndView mav = new ModelAndView("/branch/list.html");
		mav.addObject("result", result);
		return mav;
	}
	
	/**
	 * 지점 목록 조회
	 * */
	@GetMapping(value = "/branch", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Branch> getBranchList(Branch branch) {
		return this.branchService.getBranchList(branch);
	}
	
	/**
	 * 지점 조회
	 * */
	@GetMapping(value = "/branch/{no}")
	public ModelAndView getBranch(@RequestParam(value = "result", required = false, defaultValue = "none") String result, @PathVariable(value = "no") int no) {
		ModelAndView mav = new ModelAndView("/branch/detail.html");
		try {
			Branch branch = this.branchService.getBranch(no);
		} catch (Exception e) {
			log.info("지점 조회 controller 예외 발생");
		} finally {
			return mav;
		}
	}
	
	/**
	 * 지점 수정
	 * */
	@PutMapping("/branch/{no}")
	public ModelAndView editBranch(RedirectAttributes redirect, Branch branch) {
		try {
			boolean result = this.branchService.editBranch(branch);
			if (result == true) {
				redirect.addAttribute("result", "editSuccess");
			} else {
				redirect.addAttribute("result", "editFail");
			}
		} catch (Exception e) {
			redirect.addAttribute("result", "editFail");
		}
		
		return new ModelAndView(new RedirectView("/branch/" + branch.getNo()));
	}
	
	/**
	 * 지점 삭제
	 * */
	@DeleteMapping("/branch/{no}")
	public ModelAndView removeBranch(Branch branch) {
		ModelAndView mav = new ModelAndView(new RedirectView("/branch"));
		try {
			boolean result = this.branchService.removeBranch(branch);
			if (result == true) {
				mav.addObject("result", "deleteSuccess");
			} else {
				mav.addObject("result", "deleteFail");
			}
			
		} catch(Exception e) {
			mav.addObject("result", "deleteFail");
		}
		
		return mav;
	}
}
