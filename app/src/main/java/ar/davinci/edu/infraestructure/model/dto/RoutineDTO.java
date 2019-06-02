package ar.davinci.edu.infraestructure.model.dto;

public class RoutineDTO {

    int id;
    String name;
    String description;
    RoutineTemplateDTO routineTemplate;


    public RoutineDTO(int id, String name, String description, RoutineTemplateDTO routineTemplate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.routineTemplate = routineTemplate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoutineTemplateDTO getRoutineTemplate() {
        return routineTemplate;
    }

    public void setRoutineTemplate(RoutineTemplateDTO routineTemplate) {
        this.routineTemplate = routineTemplate;
    }
}
