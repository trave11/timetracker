package com.timetracker.domain.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Project with " + id + " id not found!"));
    }

    public void create(Project project) {
        projectRepository.save(project);
    }

    public void update(Long id, Project project) {
        Project existingProject = findById(id);
        existingProject.setName(project.getName());
        projectRepository.save(existingProject);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
