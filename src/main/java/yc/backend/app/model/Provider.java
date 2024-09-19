package yc.backend.app.model;

import javax.persistence.*;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role; // ROLE_PROVIDER

    // Getters and Setters
    // ...
}

