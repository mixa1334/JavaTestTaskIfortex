package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query(value = """
                SELECT *
                FROM Sessions
                WHERE device_type = :#{#type?.getCode()}
                ORDER BY started_at_utc
                LIMIT 1""", nativeQuery = true)
    Session getFirstDesktopSession(@Param("type") DeviceType deviceType);

    @Query(value = """
                SELECT *
                FROM Sessions
                WHERE ended_at_utc < :end AND user_id IN (
                    SELECT id FROM Users WHERE deleted = false
                )""", nativeQuery = true)
    List<Session> getSessionsFromActiveUsersEndedBefore2025(@Param("end")LocalDateTime endDate);
}