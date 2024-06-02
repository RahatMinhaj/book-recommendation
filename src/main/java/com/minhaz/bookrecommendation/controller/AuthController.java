package com.minhaz.bookrecommendation.controller;

import com.minhaz.bookrecommendation.dto.AppUserDto;
import com.minhaz.bookrecommendation.dto.JWTAuthenticationResponseDto;
import com.minhaz.bookrecommendation.entity.UserRoles;
import com.minhaz.bookrecommendation.param.LoginParam;
import com.minhaz.bookrecommendation.param.RefreshTokenRequestParam;
import com.minhaz.bookrecommendation.param.AppUserParam;
import com.minhaz.bookrecommendation.service.AuthService;
import com.minhaz.bookrecommendation.util.ApiConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiConstant.Auth.ROOT)
@RequiredArgsConstructor
public class AuthController{

    private final AuthService authService;

    @PostMapping(ApiConstant.Auth.CREATE)
    public ResponseEntity<AppUserDto> create(@RequestBody AppUserParam param){
        return ResponseEntity.ok(authService.createUser(param));
    }

    @PostMapping(ApiConstant.Auth.LOGIN)
    public ResponseEntity<JWTAuthenticationResponseDto> signIn(@RequestBody LoginParam param){
        return ResponseEntity.ok(authService.signIn(param));
    }

    @PostMapping(ApiConstant.Auth.REFRESH_TOKEN)
    public ResponseEntity<JWTAuthenticationResponseDto> refreshTokenRequest(@RequestBody RefreshTokenRequestParam refreshTokenRequestParam){
        return ResponseEntity.ok(authService.refreshTokenRequest(refreshTokenRequestParam));
    }

    @PostMapping(ApiConstant.Auth.ASSIGN_ROLE)
    public ResponseEntity<AppUserDto> assignRole(@RequestParam String userName, @RequestParam UserRoles userRoles){
        return ResponseEntity.ok(authService.assignRole(userName, userRoles));
    }

    @GetMapping("/test")
    public String check(){
        return "hellow world";
    }
}
