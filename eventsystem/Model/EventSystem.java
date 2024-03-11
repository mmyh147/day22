package com.example.eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class EventSystem {

    @NotEmpty(message = "ID should be entered")
    private String ID;
    @NotEmpty(message = "description should be entered")
    private String description;
    @Min(value = 26, message = "it should be 26 or greater")
    private Integer capacity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
}
