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
class AuthController(private val userService: UserService) {

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/register")
    fun showRegistrationForm(model: Model): String {
        model.addAttribute("userRegistrationDto", UserRegistrationDto())
        return "register"
    }

    @PostMapping("/register")
    fun registerUser(
        @Valid @ModelAttribute userRegistrationDto: UserRegistrationDto,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes
    ): String {
        // Check if passwords match
        if (userRegistrationDto.password != userRegistrationDto.confirmPassword) {
            bindingResult.rejectValue("confirmPassword", "error.userRegistrationDto", "Passwords do not match")
        }

        // Check if username is already taken
        if (userService.isUsernameTaken(userRegistrationDto.username)) {
            bindingResult.rejectValue("username", "error.userRegistrationDto", "Username is already taken")
        }

        // Check if email is already taken
        if (userService.isEmailTaken(userRegistrationDto.email)) {
            bindingResult.rejectValue("email", "error.userRegistrationDto", "Email is already taken")
        }

        if (bindingResult.hasErrors()) {
            return "register"
        }

        userService.registerUser(userRegistrationDto)
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.")
        return "redirect:/login"
    }
} 