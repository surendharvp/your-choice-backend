package com.backEnd.serviceMarketplace.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long userId;
    @NonNull
    private String userName;
    @NonNull
    private String password;  // Hashed password
    @NonNull
    private String email;
    private LocalDateTime createdAt = LocalDateTime.now();

}
