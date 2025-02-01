package com.ead.course.service;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UtilsService {
    String createUrlGetAllUsersByCourses(UUID courseId, Pageable pageable);
}
