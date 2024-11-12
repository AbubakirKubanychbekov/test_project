package kg.mega.test_project.services.impl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import kg.mega.test_project.dal.entity.User;
import kg.mega.test_project.dal.repository.UserRepository;
import kg.mega.test_project.dto.request.SignInRequest;
import kg.mega.test_project.dto.request.SignUpRequest;
import kg.mega.test_project.dto.response.AuthenticationSignInResponse;
import kg.mega.test_project.dto.response.AuthenticationSignUpResponse;
import kg.mega.test_project.enums.Role;
import kg.mega.test_project.exceptions.AlreadyExistException;
import kg.mega.test_project.exceptions.BadCredentialException;
import kg.mega.test_project.exceptions.NotFoundException;
import kg.mega.test_project.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        User user = User.builder()
                .firstName("Jonh")
                .lastName("Wick")
                .email("jonh@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .build();
        if (!userRepository.existsByEmail(user.getEmail())){
            userRepository.save(user);
            log.info("Администратор успешно сохранен с идентификатором:" + user.getId());
        }
    }

    @Override
    public AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistException("Пользователь с адресом электронной почты:"
                    + signUpRequest.getEmail() + " уже существует");
        }
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        log.info("Пользователь успешно сохранен с идентификатором:" + user.getId());
        return new AuthenticationSignUpResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getRole());
    }

    @Override
    public AuthenticationSignInResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() ->
                new NotFoundException(String.format("Пользователь с адресом электронной почты: %s не найден!", signInRequest.getEmail())));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.info("Недействительный пароль");
            throw new BadCredentialException("Недействительный пароль");
        }
        return new AuthenticationSignInResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getRole());
    }
}
