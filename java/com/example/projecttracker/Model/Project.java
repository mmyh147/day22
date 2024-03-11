package com.example.projecttracker.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty
    @Size(min = 3)
    private String ID;

    @NotEmpty
    @Size(min = 9)
    private String title;

    @NotEmpty
    @Size(min = 16)
    private String description;

    @NotEmpty
    @Size(min = 7)
    private String companyName;

    @NotEmpty
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$")
    private String status;
}
