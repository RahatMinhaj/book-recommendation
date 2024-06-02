package com.minhaz.bookrecommendation.repository;

import com.minhaz.bookrecommendation.entity.AppUser;
import com.minhaz.bookrecommendation.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUserName(String userName);
    AppUser findByUserRoles(UserRoles role);
}
