package com.example.todo.controller;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//
@RestController
@RequestMapping("${api.prefix}/security")
@AllArgsConstructor
public class SecurityController {

    @GetMapping("/session-id")
    public String getSession(HttpServletRequest httpServletRequest){
        return httpServletRequest.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest httpServletRequest){
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

}


//import jakarta.servlet.http.HttpServletRequest;
//import lombok.AllArgsConstructor;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.web.bind.annotation.*;

//@AllArgsConstructor
//@RestController
//@RequestMapping("${api.prefix}/security")
//public class SecurityController {
//
//    @GetMapping("/session")
//    public String Hello(HttpServletRequest httpServletRequest) {
//
//        return httpServletRequest.getSession().getId();
//
//    }
//
//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest) {
//        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
//    }
//
//
//}
