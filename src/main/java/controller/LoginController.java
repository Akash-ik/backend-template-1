package controller;

import entity.ForgotPasswordRequest;
import entity.LoginRequest;
import entity.RegisterRequest;
import entity.ResetPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
    }

    @PostMapping("/forgot-password")
    public boolean forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return userService.forgotPassword(forgotPasswordRequest.getUsername());
    }

    @PostMapping("/reset-password")
    public boolean resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        return userService.resetPassword(resetPasswordRequest.getUsername(), resetPasswordRequest.getToken(), resetPasswordRequest.getNewPassword());
    }
}