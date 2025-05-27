package com.example.demo.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DashboardController {

    @GetMapping("/dashboard")
    fun dashboard(@AuthenticationPrincipal userDetails: UserDetails, model: Model): String {
        model.addAttribute("username", userDetails.username)
        return "dashboard"
    }
} 