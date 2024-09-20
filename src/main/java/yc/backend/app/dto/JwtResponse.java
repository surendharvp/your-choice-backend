package yc.backend.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}

