package com.sam.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @SequenceGenerator( name = "users_sequence",  sequenceName = "users_sequence",          allocationSize = 1   )
    @GeneratedValue(            strategy = SEQUENCE,            generator = "users_sequence"    )
    @Column(            name = "id",            updatable = false    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank
    @Email
    private String email;

    // Getters and Setters
}
