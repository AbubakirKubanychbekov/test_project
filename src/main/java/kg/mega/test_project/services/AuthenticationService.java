package kg.mega.test_project.services;

import kg.mega.test_project.dto.request.SignInRequest;
import kg.mega.test_project.dto.request.SignUpRequest;
import kg.mega.test_project.dto.response.AuthenticationSignInResponse;
import kg.mega.test_project.dto.response.AuthenticationSignUpResponse;

public interface AuthenticationService {
    AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest);

    AuthenticationSignInResponse signIn(SignInRequest signInRequest);
}
