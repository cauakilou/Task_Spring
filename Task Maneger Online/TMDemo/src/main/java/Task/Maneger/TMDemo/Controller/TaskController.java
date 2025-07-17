package Task.Maneger.TMDemo.Controller;


import Task.Maneger.TMDemo.Module.Task;
import Task.Maneger.TMDemo.Module.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;

import static Task.Maneger.TMDemo.Module.Task.Alfa.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public @ResponseBody Task novaTask(@Valid Task task){
        task.setStatus(Task.Alfa.TO_DO);
        task.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
        task.setUpdate(task.getCreated());
        taskRepository.save(task);
        return task;
    }

    @PutMapping
    public @ResponseBody Task updateTask(@Valid Task task){
            try {
                Optional<Task> provisoria = taskRepository.findById(task.getId());
                task.setCreated(provisoria.get().getCreated());
                task.setStatus(provisoria.get().getStatus());
                task.setUpdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
                taskRepository.save(task);
                return task;
            }catch (NoSuchElementException e){
                System.out.println("Elemento Invalido");
                return null;
            }
    }

    @GetMapping
    @RequestMapping("/list/all")
    public Iterable<Task> listAll(){return taskRepository.findAll();}

    @DeleteMapping(path = "/{id}")
    public void deletarTask (@PathVariable int id){
        try {
            taskRepository.findById(id);
            taskRepository.deleteById(id);
        } catch (NoSuchElementException e){
            System.out.println("Elemento Invalido");
        }

    }

    @PutMapping(path = "/doing/{id}")
    public @ResponseBody Task markDoing(@PathVariable int id){
        try {
            Optional<Task> save = taskRepository.findById(id);
            Task task = save.get();
            task.setStatus(IN_PROGRESS);
            task.setUpdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
            taskRepository.save(task);
            return task;
        } catch (NoSuchElementException e){
            System.out.println("Elemento Invalido");
            return null;
        }
    }

    @PutMapping(path = "/done/{id}")
    public @ResponseBody Task markDone(@PathVariable int id){
        try {
            Optional<Task> save = taskRepository.findById(id);
            Task task = save.get();
            task.setStatus(DONE);
            task.setUpdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
            taskRepository.save(task);
            return task;
        } catch (NoSuchElementException e){
            System.out.println("Elemento Invalido");
            return null;
        }
    }

    @PutMapping(path = "/todo/{id}")
    public @ResponseBody Task markTodo(@PathVariable int id){
        try {
            Optional<Task> save = taskRepository.findById(id);
            Task task = save.get();
            task.setStatus(TO_DO);
            task.setUpdate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
            taskRepository.save(task);
            return task;
        }catch (NoSuchElementException e){
            System.out.println("Elemento Invalido");
            return null;
        }
        }


    @GetMapping(path = "/list/{status}")
    public Iterable<Task> listTodo(@PathVariable Task.Alfa status){
        return taskRepository.findByStatus(status);
    }




}
