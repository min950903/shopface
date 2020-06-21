    package kr.ac.sunmoon.shopface.employ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmployController {
    private final EmployService employService;
        
    @PostMapping("/employ/{branchNo}")
    public Map<String, Object> addEmploy(@PathVariable int branchNo, Employ employ) {
        boolean isSucess = employService.addEmploy(employ);
        
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("isSucess", isSucess);
        
        return responseMap;
    }
    
    @GetMapping("/employ/{branchNo}")
    public ModelAndView getEmployList(@PathVariable int branchNo) {
        Employ employ = new Employ();
        employ.setBranchNo(branchNo);
        
        List<Employ> employList = employService.getEmployList(employ);
        
        ModelAndView modelAndView = new ModelAndView("employ/_list.html");
        modelAndView.addObject("employList", employList);
        
        return modelAndView;
    }
    
    @GetMapping(value = "/employ/{branchNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Employ> getEmployList(Employ employ) {
        return employService.getEmployList(employ);
    }
    
    @GetMapping("/employ/{branchNo}/{no}")
    public ModelAndView getEmploy(@PathVariable int branchNo, @PathVariable int no, Employ employ) {
        ModelAndView modelAndView = new ModelAndView("/employ/_detail.html");
        modelAndView.addObject("employ", employService.getEmploy(employ));
        
        return modelAndView;
    }
    
    @PutMapping(value = "/employ/{branchNo}")
    public ModelAndView editEmploy(@PathVariable int branchNo, Employ employ) {
        employService.editEmploy(employ);
        ModelAndView modelAndView = new ModelAndView("/employ/_detail.html");
        
        return modelAndView;
    }
    
    @DeleteMapping("/employ/{branchNo}")
    public ModelAndView removeEmploy(Employ employ) {
        employService.deleteEmploy(employ);
        return new ModelAndView("/employ/_list.html");
    }
    
    @PutMapping("/employ/invite")
    public Map<String, Object> resendInviteMessage(@RequestBody Employ employ) {
        boolean isSuccess = employService.resendInviteMessage(employ);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("isSuccess", isSuccess);
        
        return responseMap;
    }
}