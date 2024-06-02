package com.minhaz.bookrecommendation.service;

import com.minhaz.bookrecommendation.entity.AppUser;
import com.minhaz.bookrecommendation.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class  AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName = null;
        String password = null;
        List<GrantedAuthority> authorities = null;
        Optional<AppUser> appUser = appUserRepository.findByUserName(username);
        if(appUser.isPresent()){
            username = appUser.get().getUsername();
            password = appUser.get().getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(appUser.get().getAuthorities().toString()));
        }
        return new User(username, password, authorities);
    }
}
