package com.example.demo.service

import com.example.demo.dto.UserRegistrationDto
import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun registerUser(registrationDto: UserRegistrationDto): User {
        if (userRepository.existsByUsername(registrationDto.username)) {
            throw IllegalArgumentException("Username already exists")
        }
        if (userRepository.existsByEmail(registrationDto.email)) {
            throw IllegalArgumentException("Email already exists")
        }
        if (registrationDto.password != registrationDto.confirmPassword) {
            throw IllegalArgumentException("Passwords do not match")
        }

        val user = User(
            username = registrationDto.username,
            email = registrationDto.email,
            password = registrationDto.password // Note: In a real application, you should hash the password
        )

        return userRepository.save(user)
    }
} 