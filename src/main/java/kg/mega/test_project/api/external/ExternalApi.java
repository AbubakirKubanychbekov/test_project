package kg.mega.test_project.api.external;

import kg.mega.test_project.dto.SimpleResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

public interface ExternalApi {

    @GetMapping("/fetch-external")
    @PreAuthorize("hasAnyAuthority('USER')")
    SimpleResponse fetchExternalData();
}
