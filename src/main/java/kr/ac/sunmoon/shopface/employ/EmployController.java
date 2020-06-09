package kr.ac.sunmoon.shopface.employ;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ModelAndView getEmployList(@PathVariable String branchNo) {
        Employ employ = new Employ();
        employ.setBranchNo((Integer.parseInt(branchNo)));
        
        List<Employ> employList = employService.getEmployList(employ);
        
        ModelAndView mav = new ModelAndView("/employ/list.html");
        mav.addObject("employList", employList);
        
        return mav;
    }
    
    @GetMapping(value = "/employ/{id}", consumes = "application/json")
    public List<Employ> getEmployList(@PathVariable String branchNo, Employ employ) {
        return null;
    }
    
    @PutMapping("/employ/{id}")
    public ModelAndView editEmploy(@PathVariable String no , @RequestParam Employ employ) {
        return null;
    }
    
    @DeleteMapping("/employ/{id}")
    public ModelAndView removeEmploy(@PathVariable String no, @RequestParam Employ employ) {
        return null;
    }
    
    @PostMapping("/employ/inviteMessage")
    public ModelAndView addInviteMessage(Employ employ) {
        return null;
    }
}