package ar.davinci.edu.infraestructure.model;

import lombok.*;


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