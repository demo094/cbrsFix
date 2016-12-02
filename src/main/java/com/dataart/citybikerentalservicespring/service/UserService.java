package com.dataart.citybikerentalservicespring.service;

import com.dataart.citybikerentalservicespring.constants.TokenType;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.paymentexceptions.InvalidAmountException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.EmailDuplicationException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.InvalidUserCredentialsException;
import com.dataart.citybikerentalservicespring.persistence.model.Token;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.persistence.model.UserRole;
import com.dataart.citybikerentalservicespring.persistence.repo.TokenRepository;
import com.dataart.citybikerentalservicespring.persistence.repo.UserRepository;
import com.dataart.citybikerentalservicespring.persistence.repo.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserMailService userMailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(String email, String password) throws CbrsException {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new InvalidUserCredentialsException();
        } else if (userRepository.findByEmail(email) != null) {
            throw new EmailDuplicationException();
        } else {
            String tokenBody = UUID.randomUUID().toString();
            List<UserRole> roles = new ArrayList<>();
            roles.add(userRoleRepository.findUserRole());
            User user = new User(email, passwordEncoder.encode(password), new Date(), roles);
            userRepository.save(user);
            tokenRepository.save(new Token(TokenType.ACTIVATION_TOKEN, tokenBody, user));
            userMailService.send(email, "Registration success", "registration.ftl",
                    userMailService.prepareRegistrationEmail(email, tokenBody));
        }
    }

    @Transactional
    public void updateBalance(Integer userId, BigDecimal amount) throws CbrsException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException();
        } else {
            userRepository.addMoney(amount, userId);
        }
    }

    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void setUserActivated(User user, boolean activated) {
        userRepository.setActivated(activated, user.getId());
    }

    @Transactional
    public User findById(Integer userId) {
        return userRepository.findOne(userId);
    }

    @Transactional
    public Token findTokenByBody(String tokenBody) {
        return tokenRepository.findByBody(tokenBody);
    }

    @Transactional
    public void resendActivationToken(String email) {
        User user = userRepository.findByEmail(email);
        String tokenBody = UUID.randomUUID().toString();
        tokenRepository.save(new Token(TokenType.ACTIVATION_TOKEN, tokenBody, user));

        userMailService.send(email, "Resend activation request", "resendlink.ftl",
                userMailService.prepareResendTokenEmail(email, tokenBody));
    }

    @Transactional
    public void sendResetPasswordEmail(String email) {
        User user = userRepository.findByEmail(email);
        String tokenBody = UUID.randomUUID().toString();
        tokenRepository.save(new Token(TokenType.PASSWORD_RESET_TOKEN, tokenBody, user));

        userMailService.send(email, "Reset password request", "resetpassword.ftl",
                userMailService.prepareResetPasswordEmail(email, tokenBody));
    }

    @Transactional
    public void updatePasswordHash(User user, String password) throws CbrsException {
        if (password == null || password.isEmpty()) {
            throw new InvalidUserCredentialsException();
        }
        String newPasswordHash = passwordEncoder.encode(password);
        user.setPasswordHash(newPasswordHash);
    }
}
