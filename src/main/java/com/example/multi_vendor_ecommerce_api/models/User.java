package com.example.multi_vendor_ecommerce_api.models;

import com.example.multi_vendor_ecommerce_api.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            nullable = false,
            unique = true,
            length = 500

    )
    private String username;

    @Column(
            nullable = false,
            unique = true,
            length = 500

    )
    private String email;

    @Column(
            nullable = false,
            unique = false,
            length = 500
    )
    private String password;

    @Column(
            updatable = false,
            nullable = false
    )
    //this is the role column
    @Enumerated(EnumType.STRING)
    private Roles role;

    //this is for the time the account was created
    @Column(
            updatable = false,
            nullable = false
    )

    @CreationTimestamp
    private LocalDateTime createAt;


    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModified;
}
