package ar.davinci.edu.infraestructure.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class Exercise {

    private Long id;
    private String name;
    private String type;
    private String description;
    private String difficulty;


}