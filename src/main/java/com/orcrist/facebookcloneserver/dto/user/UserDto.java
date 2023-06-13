package com.orcrist.facebookcloneserver.dto.user;

import com.orcrist.facebookcloneserver.model.Role;
import com.orcrist.facebookcloneserver.model.UserBio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record UserDto(
        Long id,
        String name,
        String lastname,
        String username,
        String email,
        String picture,
        String cover,
        String gender,
        Set<String> roles,
        LocalDate birthday,
        Boolean isVerified,
        UserBio details,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles.stream().map(Role::getRole).collect(Collectors.toSet());
//    }
}
