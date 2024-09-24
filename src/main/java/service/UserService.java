package service;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public boolean loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean forgotPassword(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            // Generate a password reset token and send it to the user's email
            String token = UUID.randomUUID().toString();
            user.get().setPasswordResetToken(token);
            userRepository.save(user.get());
            // Simulate sending an email with the token
            System.out.println("Password reset token sent to user's email: " + token);
            return true;
        }
        return false;
    }

    public boolean resetPassword(String username, String token, String newPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPasswordResetToken().equals(token)) {
            user.get().setPassword(newPassword);
            user.get().setPasswordResetToken(null);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }
}
