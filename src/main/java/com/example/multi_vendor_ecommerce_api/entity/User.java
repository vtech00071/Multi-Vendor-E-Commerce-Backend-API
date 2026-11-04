package com.example.multi_vendor_ecommerce_api.entity;

import com.example.multi_vendor_ecommerce_api.enums.Roles;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @JsonBackReference
    @OneToOne(mappedBy = "vendor")
    private VendorStore vendorStore;
}
