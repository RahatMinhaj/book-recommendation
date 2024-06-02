package com.minhaz.bookrecommendation.config;

import com.minhaz.bookrecommendation.dto.AppUserDto;
import com.minhaz.bookrecommendation.entity.AppUser;
import com.minhaz.bookrecommendation.entity.UserRoles;
import com.minhaz.bookrecommendation.param.AppUserParam;
import com.minhaz.bookrecommendation.repository.AppUserRepository;
import com.minhaz.bookrecommendation.service.AuthService;
import com.minhaz.bookrecommendation.util.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Configuration
public class SeedConfig implements CommandLineRunner {

    private final AppUserRepository appUserRepository;

    private final AuthService authService;

    public SeedConfig(AppUserRepository appUserRepository, AuthService authService) {
        this.appUserRepository = appUserRepository;
        this.authService = authService;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<AppUser> byUserName = appUserRepository.findByUserName(CommonConstant.ADMIN_USERNAME);
        if (byUserName.isEmpty()){
            AppUserDto user = authService.createUser(AppUserParam.builder()
                    .userName(CommonConstant.ADMIN_USERNAME)
                    .appPassword(CommonConstant.ADMIN_USERNAME)
                    .email("admin@mail.com")
                    .build());
            if (Objects.nonNull(user)){
                authService.assignRole(user.getUserName(), UserRoles.ADMIN);
            }
        }
    }
}
