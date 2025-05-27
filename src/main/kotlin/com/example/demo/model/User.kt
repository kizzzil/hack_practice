package com.example.demo.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["username"]),
        UniqueConstraint(columnNames = ["email"])
    ]
)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @field:NotBlank(message = "Username is required")
    @field:Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    var username: String = "",

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Please provide a valid email address")
    @Column(nullable = false, unique = true)
    var email: String = "",

    @field:NotBlank(message = "Password is required")
    @field:Size(min = 6, message = "Password must be at least 6 characters long")
    @Column(nullable = false)
    var password: String = ""
) {
    // Default constructor required by JPA
    constructor() : this(0, "", "", "")
} 