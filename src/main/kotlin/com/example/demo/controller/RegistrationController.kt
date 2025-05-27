package com.example.demo.controller

import com.example.demo.dto.UserRegistrationDto
import com.example.demo.service.UserService
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class RegistrationController(private val userService: UserService) {

    @GetMapping("/register")
    fun showRegistrationForm(model: Model): String {
        model.addAttribute("user", UserRegistrationDto())
        return "registration"
    }

    @PostMapping("/register")
    fun registerUser(
        @Valid @ModelAttribute("user") userDto: UserRegistrationDto,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes
    ): String {
        if (bindingResult.hasErrors()) {
            return "registration"
        }

        return try {
            userService.registerUser(userDto)
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.")
            "redirect:/login"
        } catch (e: IllegalArgumentException) {
            bindingResult.rejectValue("username", "error.user", e.message ?: "Registration failed")
            "registration"
        }
    }
} 