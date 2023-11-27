package com.example.yatchdice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deploy")
public class DeployTestController {
    @GetMapping("/test")
    public String testDeploy(){
        return "test";
    }
}
