package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = """
                SELECT *
                FROM Users
                WHERE id IN (
                    SELECT DISTINCT user_id
                    FROM Sessions
                    WHERE device_type = :#{#type?.getCode()}
                )""", nativeQuery = true)
    List<User> getUsersWithAtLeastOneMobileSession(@Param("type") DeviceType deviceType);

    @Query(value = """
                SELECT *
                FROM Users
                WHERE id = (
                    SELECT user_id
                    FROM Sessions
                    GROUP BY user_id
                    ORDER BY COUNT(id) DESC LIMIT 1
                )""", nativeQuery = true)
    User getUserWithMostSessions();
}
