package com.example.demo.service

import com.example.demo.dto.UserRegistrationDto
import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun isUsernameTaken(username: String): Boolean {
        return userRepository.findByUsername(username) != null
    }

    fun isEmailTaken(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }

    @Transactional
    fun registerUser(registrationDto: UserRegistrationDto): User {
        if (registrationDto.password != registrationDto.confirmPassword) {
            throw IllegalStateException("Passwords do not match")
        }

        if (userRepository.findByUsername(registrationDto.username) != null) {
            throw IllegalStateException("Username already exists")
        }

        if (userRepository.findByEmail(registrationDto.email) != null) {
            throw IllegalStateException("Email already exists")
        }

        val user = User(
            username = registrationDto.username,
            email = registrationDto.email,
            password = passwordEncoder.encode(registrationDto.password)
        )

        return userRepository.save(user)
    }
} 