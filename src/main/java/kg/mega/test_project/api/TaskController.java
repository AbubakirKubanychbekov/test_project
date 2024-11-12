package kg.mega.test_project.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import kg.mega.test_project.dto.SimpleResponse;
import kg.mega.test_project.dto.request.TaskRequest;
import kg.mega.test_project.dto.response.TaskResponse;
import kg.mega.test_project.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PermitAll
    @GetMapping()
    public List<TaskResponse> getAll() {
        return taskService.getAll();
    }


    @PostMapping
    @Operation(summary = "Сохранить задачи",
            description = "Добавляет новые задачи в список.")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }



    @GetMapping("/{id}")
    @Operation(summary = "Получить задачу по идентификатору",
            description = "Возвращает информацию о задаче с указанным идентификатором.")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public TaskResponse getById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить задачу по идентификатору",
            description = "Обновляет информацию о задаче с указанным идентификатором.")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }


    @PermitAll
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить задачу по идентификатору",
            description = "Удаляет задачу с указанным идентификатором.")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public SimpleResponse delete(@PathVariable Long id) {
        return taskService.delete(id);
    }
}
