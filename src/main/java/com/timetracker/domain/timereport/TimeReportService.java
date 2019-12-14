package com.timetracker.domain.timereport;

import com.timetracker.domain.project.ProjectService;
import com.timetracker.domain.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TimeReportService {

    private TimeReportRepository timeReportRepository;
    private UserService userService;
    private ProjectService projectService;

    @Autowired
    public TimeReportService(TimeReportRepository timeReportRepository, UserService userService, ProjectService projectService) {
        this.timeReportRepository = timeReportRepository;
        this.userService = userService;
        this.projectService = projectService;
    }

    public List<TimeReport> getAll() {
        return timeReportRepository.findAll();
    }

    public void create(Long userId, Long projectId, double time) {
        TimeReport timeReport = new TimeReport();
        timeReport.setUser(userService.findById(userId));
        timeReport.setProject(projectService.findById(projectId));
        timeReport.setTime(time);
        timeReportRepository.save(timeReport);
    }

    public TimeReport findById(Long id) {
        return timeReportRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("The timereport is not found!"));
    }

    public void delete(Long id) {
        timeReportRepository.deleteById(id);
    }

    public void update(Long timeReportId, Optional<Long> userId, Optional<Long> projectId, Optional<Double> time) {
        TimeReport timeReport = findById(timeReportId);

        userId.ifPresent(id -> timeReport.setUser(userService.findById(id)));
        projectId.ifPresent(id -> timeReport.setProject(projectService.findById(id)));
        time.ifPresent(timeReport::setTime);
        timeReportRepository.save(timeReport);
    }
}