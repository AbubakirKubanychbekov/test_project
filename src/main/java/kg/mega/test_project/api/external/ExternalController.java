package kg.mega.test_project.api.external;

import kg.mega.test_project.dto.SimpleResponse;
import kg.mega.test_project.services.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external-api")
@RequiredArgsConstructor
public class ExternalController {

    private final ExternalService externalService;

    @GetMapping("/fetch-external")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public SimpleResponse fetchExternalData() {
        return externalService.fetchExternalData();
    }
}
