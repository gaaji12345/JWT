package com.example.JWTGaaji.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/blog")
public class BlogController {
    @GetMapping("/newMethod")
    public String newMethod(){
        return "HI";
    }
}
