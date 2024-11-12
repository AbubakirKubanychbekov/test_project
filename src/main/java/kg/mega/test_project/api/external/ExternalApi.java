package kg.mega.test_project.api.external;

import kg.mega.test_project.dto.SimpleResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Abubakir Dev
 */

public interface ExternalApi {

    @GetMapping("/fetch-external")
    SimpleResponse fetchExternalData();
}
