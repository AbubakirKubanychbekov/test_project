package kg.mega.test_project.services.impl;

import kg.mega.test_project.dto.SimpleResponse;
import kg.mega.test_project.services.ExternalService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {
    private final RestTemplate restTemplate;
    private final Logger log = LoggerFactory.getLogger(ExternalServiceImpl.class);
    @Override
    public SimpleResponse fetchExternalData() {
        URI url = URI.create("https://api.restful-api.dev/objects");
        try {
            String response = restTemplate.getForObject(url, String.class);
            log.info("Полученный ответ от внешнего API: {}", response);
        } catch (Exception e) {
            log.error("Ошибка при выполнении запроса к внешнему API: {}", e.getMessage());
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Ошибка при запросе к внешнему API: " + e.getMessage())
                    .build();
        }
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Данные успешно залогированы")
                .build();
    }
}
