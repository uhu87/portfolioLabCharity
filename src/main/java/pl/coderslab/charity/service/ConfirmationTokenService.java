package pl.coderslab.charity.service;


import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.ConfirmationToken;
import pl.coderslab.charity.repository.ConfirmationTokenRepository;

import java.util.Optional;

@Service
public class ConfirmationTokenService {

        private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }


        public  void saveConfirmationToken(ConfirmationToken token){
            confirmationTokenRepository.save(token);
        }

        public Optional<ConfirmationToken> findConfirmationToken(String token){

        return confirmationTokenRepository.findByToken(token);
        }

}
