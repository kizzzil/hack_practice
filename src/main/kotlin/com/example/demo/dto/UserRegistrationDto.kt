package com.example.demo.dto

import jakarta.validation.constraints.*

data class UserRegistrationDto(
    @field:NotBlank(message = "Username is required")
    @field:Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    val username: String = "",

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Please provide a valid email address")
    val email: String = "",

    @field:NotBlank(message = "Password is required")
    @field:Size(min = 6, message = "Password must be at least 6 characters long")
    val password: String = "",

    @field:NotBlank(message = "Password confirmation is required")
    val confirmPassword: String = ""
) 