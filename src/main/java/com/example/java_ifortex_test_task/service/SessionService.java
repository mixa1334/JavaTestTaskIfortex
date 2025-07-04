package com.example.java_ifortex_test_task.service;

import com.example.java_ifortex_test_task.dto.SessionResponseDTO;
import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.Session;
import com.example.java_ifortex_test_task.mapper.SessionMapper;
import com.example.java_ifortex_test_task.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private static final LocalDateTime before2025 = LocalDateTime.of(2025, 1, 1, 0, 0, 0);
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    // Returns the first (earliest) desktop Session
    public SessionResponseDTO getFirstDesktopSession() {
        Session firstDesktopSession = sessionRepository.getFirstDesktopSession(DeviceType.DESKTOP);
        return sessionMapper.toDto(firstDesktopSession);
    }

    // Returns only Sessions from Active users that were ended before 2025
    public List<SessionResponseDTO> getSessionsFromActiveUsersEndedBefore2025() {
        return sessionRepository
                .getSessionsFromActiveUsersEndedBefore2025(before2025)
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }
}