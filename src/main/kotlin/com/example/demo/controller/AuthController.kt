package com.example.demo.controller

import com.example.demo.dto.UserRegistrationDto
import com.example.demo.model.User
import com.example.demo.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
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

    @PostMapping("/api/auth/register")
    @ResponseBody
    fun registerUserApi(@Valid @RequestBody userRegistrationDto: UserRegistrationDto): ResponseEntity<User> {
        return try {
            val user = userService.registerUser(userRegistrationDto)
            ResponseEntity.ok(user)
        } catch (e: IllegalStateException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }
} 