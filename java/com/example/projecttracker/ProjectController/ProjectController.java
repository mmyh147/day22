package com.example.projecttracker.ProjectController;


import com.example.projecttracker.API.ApiResponse;
import com.example.projecttracker.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();


    @PostMapping("post")
    public ResponseEntity post(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        projects.add(project);
        return  ResponseEntity.status(200).body(new ApiResponse("project added successfully"));
    }


    @GetMapping("projects")
    public ArrayList<Project> getProjects() {
        return projects;
    }



    @PutMapping("update/{ID}")
    public ResponseEntity updateProject(@PathVariable String ID, @RequestBody @Valid Project updatedProject, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        for (Project project : projects) {

            if (project.getID() != null && project.getID().equals(ID)) {

                 projects.set(projects.indexOf(project), updatedProject);

                 return ResponseEntity.status(200).body(new ApiResponse("the project has been updated successfully"));
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("The ID not found!"));
    }


    @DeleteMapping("delete/{ID}")
    public ResponseEntity deleteProject(@PathVariable String ID) {

        for (Project project : projects) {
            if (project.getID().equals(ID)) {
                projects.remove(project);
                return ResponseEntity.status(200).body(new ApiResponse("The project has been deleted!"));

            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("No project found with ID : " + ID));
    }


    @PostMapping("status/{ID}")
    public ResponseEntity projectStatus(@PathVariable String ID){

        for (Project project : projects) {
            if (project.getID().equals(ID)) {
                if (projects.get(projects.indexOf(project)).getStatus().equals("Not Started")){

                    projects.get(projects.indexOf(project)).setStatus("In Progress");
                    return  ResponseEntity.status(200).body(new ApiResponse("The project status changed to In Progress!"));
                }else if(projects.get(projects.indexOf(project)).getStatus().equals("In Progress")){
                    projects.get(projects.indexOf(project)).setStatus("Completed");
                    return  ResponseEntity.status(200).body(new ApiResponse("The project status changed to Completed!"));

                } else if (projects.get(projects.indexOf(project)).getStatus().equals("Completed")) {
                    return  ResponseEntity.status(200).body(new ApiResponse("The project already Completed!"));

                }

            }
        }
        return  ResponseEntity.status(200).body(new ApiResponse("No project found with ID : " + ID));
    }

    @GetMapping("search/{searchByTitle}")
    public Project getByTitle(@PathVariable String searchByTitle){
        return projects.stream().filter(project -> project.getTitle().equals(searchByTitle))
                .findFirst()
                .orElse(null);
    }


    @GetMapping("company/{searchByCompany}")
    public ArrayList<Project> getByCompany(@PathVariable String searchByCompany) {
        ArrayList<Project> projectsByCompany = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equals(searchByCompany)) {
                projectsByCompany.add(project);
            }
        }
        return projectsByCompany;
    }
}
