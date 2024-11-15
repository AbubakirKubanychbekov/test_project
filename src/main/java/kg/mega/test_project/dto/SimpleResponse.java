package kg.mega.test_project.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter @Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleResponse {
    HttpStatus httpStatus;
    String message;
}
