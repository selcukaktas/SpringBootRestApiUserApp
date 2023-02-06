package com.aktas.selcuk.service;

import com.aktas.selcuk.dto.UserDto;
import com.aktas.selcuk.entity.User1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto user);
    Boolean deleteUser(Long id);

    Page<User1> pagination(int currentPage, int pageSize);
    Page<User1> pagination(Pageable pageable);

    Slice<User1> slice(Pageable pageable);

}
