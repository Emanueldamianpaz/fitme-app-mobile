package ar.davinci.edu.infraestructure.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class Scoring {

    private Long id;
    private String scoring;
    private String tip;

}