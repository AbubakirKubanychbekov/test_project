package kg.mega.test_project.dto.response;

import kg.mega.test_project.enums.Role;

public record AuthenticationSignUpResponse(
        Long id,
        String fullName,
        String email,
        Role role
) {
}
