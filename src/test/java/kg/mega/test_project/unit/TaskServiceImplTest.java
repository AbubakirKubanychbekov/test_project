package kg.mega.test_project.unit;

import kg.mega.test_project.dal.entity.Task;
import kg.mega.test_project.dal.repository.TaskRepository;
import kg.mega.test_project.dto.SimpleResponse;
import kg.mega.test_project.dto.request.TaskRequest;
import kg.mega.test_project.dto.response.TaskResponse;
import kg.mega.test_project.services.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@DataJpaTest
public class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    private final TaskRequest taskRequest = TaskRequest.builder()
            .description("Task for Mega")
            .done(false)
            .build();

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;


    @Test
    public void testCreateTask(){
        Task task = new Task();
        when(taskRepository.save(any(Task.class))).thenReturn(new Task());
        SimpleResponse result = taskServiceImpl.save(taskRequest);

        verify(taskRepository, times(1)).save(any(Task.class));
        assertEquals(HttpStatus.OK, result.getHttpStatus());
        assertEquals("Task with id : %s success saved".formatted(task.getId()),result.getMessage());
    }

    @Test
    public void testGetAllTasks(){
        when(taskRepository.getAllTasks()).thenReturn(Collections.emptyList());
        List<TaskResponse> responses = taskServiceImpl.getAll();

        assertNotNull(responses);
        assertTrue(responses.isEmpty());
        Mockito.verify(taskRepository, times(1)).getAllTasks();
    }

    @Test
    public void testGetById(){
        Long taskId = 1L;
        TaskResponse taskResponse = new TaskResponse();
        when(taskRepository.getTaskById(taskId)).thenReturn(Optional.of(taskResponse));
        TaskResponse result= taskServiceImpl.getTaskById(taskId);

        verify(taskRepository, times(1)).getTaskById(taskId);
        assertEquals(taskResponse,result);
    }

    @Test public void testDeleteById() {
        Long taskId = 1L;
        Task taskToDelete = new Task();
        taskToDelete.setId(taskId);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskToDelete));
        when(taskRepository.existsById(taskId)).thenReturn(true);
        SimpleResponse result = taskServiceImpl.delete(taskId);
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);
        assertEquals(HttpStatus.OK, result.getHttpStatus());
        assertEquals("Task with id : %s is deleted".formatted(taskId), result.getMessage());
    }
}
