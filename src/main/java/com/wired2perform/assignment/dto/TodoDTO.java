package com.wired2perform.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * TodoDTO class
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    @JsonProperty("userId")
    private Long id;

    private List<String> todos;

    private Date createdDate;

    private Date updatedDate;

}
