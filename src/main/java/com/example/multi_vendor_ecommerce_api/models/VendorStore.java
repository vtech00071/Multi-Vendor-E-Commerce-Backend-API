package com.example.multi_vendor_ecommerce_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity

public class VendorStore {
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
    private String name;

    @Column(
            nullable = false,
            unique = true,
            length = 500
    )
    private String description;
    //this is the owing side so it will carry the foreign key
    //that means its the vendor that will carry the foreign key
    @OneToOne
    @JoinColumn(name = "vendor_id" , referencedColumnName = "id")
    private User vendor;

    //this is the time stamp for when it was created
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt;


    //this is the timestamp for when something is updated
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModified;

}
