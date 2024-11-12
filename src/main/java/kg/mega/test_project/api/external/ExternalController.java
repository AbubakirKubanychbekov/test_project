package kg.mega.test_project.api.external;

import kg.mega.test_project.dto.SimpleResponse;
import kg.mega.test_project.services.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExternalController implements ExternalApi {

    private final ExternalService externalService;

    @Override
    public SimpleResponse fetchExternalData() {
        return externalService.fetchExternalData();
    }
}
