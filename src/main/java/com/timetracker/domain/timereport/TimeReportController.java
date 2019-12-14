package com.timetracker.domain.timereport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timereports")
public class TimeReportController {
    private TimeReportService timeReportService;

    @Autowired
    public TimeReportController(TimeReportService timeReportService) {
        this.timeReportService = timeReportService;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        return exception.getMessage();
    }

    @GetMapping
    public List<TimeReport> getAll() {
        return timeReportService.getAll();
    }

    @GetMapping("/{id}")
    public TimeReport findById(@PathVariable("id") Long id) {
        return timeReportService.findById(id);
    }

    @PostMapping
    public void create(@RequestParam("userId") Long userId, @RequestParam("projectId") Long projectId, @RequestParam("time") double time) {
        timeReportService.create(userId, projectId, time);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long timeReportId,
                       @RequestParam(value = "userId", required = false) Optional<Long> userId,
                       @RequestParam(value = "projectId", required = false) Optional<Long> projectId,
                       @RequestParam(value = "time", required = false) Optional<Double> time) {
        timeReportService.update(timeReportId, userId, projectId, time);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        timeReportService.delete(id);
    }
}