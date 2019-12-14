package com.timetracker.domain.timereport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TimeReportRepository extends JpaRepository<TimeReport, Long> {
}
