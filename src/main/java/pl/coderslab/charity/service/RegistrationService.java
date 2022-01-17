package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.ConfirmationToken;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.ConfirmationTokenRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    public RegistrationService(UserService userService, ConfirmationTokenService confirmationTokenService, ConfirmationTokenRepository confirmationTokenRepository, UserRepository userRepository) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        User user = userService.findByUserName(
                confirmationToken.getUser().getUsername());
        user.setEnabled(true);
        userRepository.save(user);    // zamien na servis
        return "redirect:/login";
    }

}
