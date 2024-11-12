package kg.mega.test_project.dto.response;

import kg.mega.test_project.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationSignInResponse(
        Long id,
        String fullName,
        String email,
        Role role
) {
}
