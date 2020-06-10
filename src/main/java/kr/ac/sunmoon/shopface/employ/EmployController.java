package kr.ac.sunmoon.shopface.employ;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class EmployController {
    private final EmployService employService;
    
    @GetMapping("/employ/form")
    public ModelAndView addEmploy() {
        return new ModelAndView("/employ/list.html");
    }
    
    @PostMapping("/employ")
    public ModelAndView addEmploy(@RequestParam Employ employ) {
        return null;
    }
    
    @GetMapping("/employ/{branchNo}")
    public ModelAndView getEmployList(@PathVariable int branchNo) {
        Employ employ = new Employ();
        employ.setBranchNo(branchNo);
        
        List<Employ> employList = employService.getEmployList(employ);
        
        ModelAndView modelAndView = new ModelAndView("employ/list.html");
        modelAndView.addObject("employList", employList);
        
        return modelAndView;
    }
    
    @GetMapping(value = "/employ/{no}", consumes = "application/json")
    public List<Employ> getEmployList(@PathVariable int branchNo, Employ employ) {
        return null;
    }
    
    @GetMapping("/employ/employee/{no}")
    public ModelAndView getEmploy(@PathVariable int no) {
        Employ employ = new Employ();
        employ.setNo(no);
        
        ModelAndView modelAndView = new ModelAndView("/employ/detail.html");
        modelAndView.addObject("employ", employService.getEmploy(employ));
        
        return modelAndView;
    }
    
    @PutMapping("/employ/{no}")
    public ModelAndView editEmploy(@PathVariable int no, Employ employ) {
        employService.editEmploy(employ);
        ModelAndView modelAndView = new ModelAndView("redirect:/employ/employee/" + employ.getNo());
        
        return modelAndView;
    }
    
    @DeleteMapping("/employ/{no}")
    public ModelAndView removeEmploy(@PathVariable("no") int no, @RequestParam Employ employ) {
        return null;
    }
    
    @PostMapping("/employ/inviteMessage")
    public ModelAndView addInviteMessage(Employ employ) {
        return null;
    }
}