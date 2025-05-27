package com.example.demo.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UserTest {

    @Test
    fun `test user creation with valid data`() {
        // Given
        val username = "testuser"
        val email = "test@example.com"
        val password = "password123"

        // When
        val user = User(
            username = username,
            email = email,
            password = password
        )

        // Then
        assertEquals(username, user.username, "Username should match")
        assertEquals(email, user.email, "Email should match")
        assertEquals(password, user.password, "Password should match")
        assertEquals(0L, user.id, "New user should have id = 0")
    }
} 