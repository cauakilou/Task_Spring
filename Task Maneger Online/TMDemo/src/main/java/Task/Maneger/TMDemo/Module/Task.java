package Task.Maneger.TMDemo.Module;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    public enum Alfa{
        TO_DO,IN_PROGRESS,DONE
    }

    @Enumerated(EnumType.STRING)
    @NotNull
    public Alfa status;

    @NotBlank
    private String created;

    @NotBlank
    private String update;

    public Task() {
    }

    public Task(@NotBlank String name) {
        this.name = name;
        this.update = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
        this.created = this.update;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public Alfa getStatus() {
        return status;
    }

    public void setStatus(Alfa status) {
        this.status = status;
    }
}

