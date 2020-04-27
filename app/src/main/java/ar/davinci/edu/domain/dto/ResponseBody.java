package ar.davinci.edu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseBody {

    private String code;
    private String description;

}
