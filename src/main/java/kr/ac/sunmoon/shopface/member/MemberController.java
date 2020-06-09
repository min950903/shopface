package kr.ac.sunmoon.shopface.member;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/member/form")
	public ModelAndView addMember() {	
		return new ModelAndView("member/add");
	}
	
	@PostMapping("/member")
	public ModelAndView addMember(Member member, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (memberService.addMember(member)) {
			modelAndView.setView(new RedirectView("/"));
			
			return modelAndView;
		} else {
			modelAndView.setView(new RedirectView("/member/form"));
			redirectAttributes.addFlashAttribute("message", "회원가입 정보가 올바르지 않습니다");
			
			return modelAndView;			
		}
	}
	
	@GetMapping("/member")
	public ModelAndView getMemberList() {
		return new ModelAndView("member/list");
	}
	
	@GetMapping(value = "/member", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Member> getMemberList(Member member) {
		return memberService.getMemberList(member);
	}
	
	@GetMapping("/member/{id}")
	public ModelAndView getMember(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView("member/detail");
		
		Member member = new Member();
		member.setId(id);

		modelAndView.addObject("member", memberService.getMember(member));
		
				
		return modelAndView;
	}
	
	
	//pathvariable 유용성 검사 필요
	@PutMapping("/member/{id}")
	public ModelAndView updateMember(@PathVariable String id, Member member) {
		memberService.editMember(member);
		
		return new ModelAndView(new RedirectView("/member"));
	}
	
	@DeleteMapping("/member/{id}")
	public ModelAndView removeMember(@PathVariable String id) {
		Member member = new Member();
		member.setId(id);
		
		memberService.removeMember(member);
		
		return new ModelAndView(new RedirectView("/member"));
	}
}
