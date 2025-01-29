package com.ead.course.service.impl;

import com.ead.course.repositories.CourseUserRepository;
import com.ead.course.service.CourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseUserImpl implements CourseUserService {
    @Autowired
    CourseUserRepository courseUserRepository;
}
