package kr.ac.sunmoon.shopface.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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

@RequiredArgsConstructor
@Slf4j
@RestController
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/member/form")
	public ModelAndView addMember(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("member/add");
		
		String referrer = request.getHeader("Referer");
		
		if ("employ".equals(referrer.substring(referrer.lastIndexOf("/") + 1, referrer.lastIndexOf("/") + 7))) {
			modelAndView.addObject("type", "E");
		} else {
			modelAndView.addObject("type", "B");
		}
		
		return modelAndView;
	}
	
	@PostMapping("/member")
	public ModelAndView addMember(Member member, String certiCode, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (memberService.addMember(member, certiCode)) {
			modelAndView.setView(new RedirectView("/login"));
		} else {
			modelAndView.setView(new RedirectView("/member/form"));
			redirectAttributes.addFlashAttribute("message", "회원가입 정보가 올바르지 않습니다");
		}
		return modelAndView;			
	}
	
	@GetMapping(value = "/member/check", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MemberMessage> checkId(@RequestParam("id") String id) {
		MemberMessage message = new MemberMessage();
		
		if (memberService.checkIdDuplicate(id)) {
			message.setDuplicate(true);
			message.setMessage("입력한 아이디가 사용 중 입니다.");
		} else {
			message.setDuplicate(false);
			message.setMessage("사용가능한 아이디입니다.");
		}
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping("/member")
	public ModelAndView getMemberList(@AuthenticationPrincipal User user) {
		ModelAndView modelAndView = new ModelAndView("member/list");
		
		return modelAndView;
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
	
	@PutMapping("/member/{id}")
	public ModelAndView updateMember(@PathVariable String id, Member member, String oldPassword,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		
		boolean isSuccess = memberService.editMember(member, oldPassword);
		if(isSuccess) {
			modelAndView.setView(new RedirectView("/member"));
		} else {
			redirectAttributes.addFlashAttribute("message", "기존 비밀번호가 일치하지 않습니다.");
			
			modelAndView.setView(new RedirectView("/member/" + id));
		}
		
		return modelAndView;
	}
	
	@DeleteMapping("/member/{id}")
	public ModelAndView removeMember(@PathVariable String id) {
		Member member = new Member();
		member.setId(id);
		
		memberService.removeMember(member);
		
		return new ModelAndView(new RedirectView("/member"));
	}
}
