package entity;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String username;
    private String token;
    private String newPassword;
}
