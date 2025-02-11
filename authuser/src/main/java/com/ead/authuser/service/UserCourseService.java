package com.ead.authuser.service;

import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface UserCourseService {
    boolean existsByUserAndCourseId(UserModel userModel, @NotNull UUID courseId);

    UserCourseModel save(UserCourseModel userCourseModel);
}
