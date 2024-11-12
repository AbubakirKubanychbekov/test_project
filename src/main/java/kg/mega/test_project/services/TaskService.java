package kg.mega.test_project.services;

import kg.mega.test_project.dto.SimpleResponse;
import kg.mega.test_project.dto.request.TaskRequest;
import kg.mega.test_project.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAll();

    SimpleResponse save(TaskRequest taskRequest);

    TaskResponse getTaskById(Long id);

    SimpleResponse update(Long id, TaskRequest taskRequest);

    SimpleResponse delete(Long id);
}
