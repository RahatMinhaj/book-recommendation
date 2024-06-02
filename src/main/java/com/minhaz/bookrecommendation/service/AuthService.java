package com.minhaz.bookrecommendation.service;

import com.minhaz.bookrecommendation.dto.AppUserDto;
import com.minhaz.bookrecommendation.dto.JWTAuthenticationResponseDto;
import com.minhaz.bookrecommendation.entity.AppUser;
import com.minhaz.bookrecommendation.entity.UserRoles;
import com.minhaz.bookrecommendation.param.AppUserParam;
import com.minhaz.bookrecommendation.param.LoginParam;
import com.minhaz.bookrecommendation.param.RefreshTokenRequestParam;
import com.minhaz.bookrecommendation.repository.AppUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AppUserDto createUser(AppUserParam param){
        AppUser user = new AppUser();
        BeanUtils.copyProperties(param, user);
        user.setAppPassword(passwordEncoder.encode(user.getPassword()));
        user = appUserRepository.save(user);
        return AppUserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public AppUserDto assignRole(String userName, UserRoles userRoles) {
        AppUser byUserName = appUserRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("User is not found"));
        byUserName.setUserRoles(userRoles);
        byUserName = appUserRepository.save(byUserName);
        return AppUserDto.builder()
                .userId(byUserName.getUserId())
                .email(byUserName.getEmail())
                .userName(byUserName.getUsername())
                .build();
    }

    public JWTAuthenticationResponseDto refreshTokenRequest(RefreshTokenRequestParam refreshTokenRequestParam){
        String userName = jwtService.extractUserName(refreshTokenRequestParam.getToken());
        AppUser user = appUserRepository.findByUserName(userName).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequestParam.getToken(), user)){
            var jwt = jwtService.generateToken(user);
            JWTAuthenticationResponseDto response = new JWTAuthenticationResponseDto();
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequestParam.getToken());
            return response;
        }
        return null;
    }

    public JWTAuthenticationResponseDto signIn(LoginParam param){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(param.getUserName(), param.getAppPassword()));
        AppUser user = appUserRepository.findByUserName(param.getUserName()).orElseThrow(()-> new IllegalArgumentException("invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JWTAuthenticationResponseDto response = new JWTAuthenticationResponseDto();
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        return response;
    }
}
