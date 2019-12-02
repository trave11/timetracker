package com.timetracker.domain.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        return exception.getMessage();
    }

    @GetMapping
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable("id") Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Project project) {
        projectService.create(project);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Project project) {
        projectService.update(id, project);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") Long id) {
        projectService.delete(id);
    }
}
