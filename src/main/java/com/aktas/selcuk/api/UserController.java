package com.aktas.selcuk.api;

import com.aktas.selcuk.dto.UserDto;
import com.aktas.selcuk.entity.User1;
import com.aktas.selcuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    //@Autowired IOC içinde tuttuğumuz userservice referansını bu propertye enjekte eder. ama daha best practice olması için altta constructer ekleniyor
    private final UserService userService;
    public UserController(UserService userService) {

        this.userService = userService;
    }
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto resultUser = userService.createUser(user);
         return ResponseEntity.ok(resultUser);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto user){
        UserDto resultUser = userService.updateUser(id, user);
        return ResponseEntity.ok(resultUser);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        Boolean status = userService.deleteUser(id);
        return ResponseEntity.ok(status);
    }
    @GetMapping("/pagination")
//    public ResponseEntity<Page<UserDto>> pagination(@RequestParam int currentPage, @RequestParam int pageSize) {
//        return ResponseEntity.ok(userService.pagination(currentPage, pageSize));
    public ResponseEntity<Page<User1>> pagination(@RequestParam int currentPage, @RequestParam int pageSize) {
        return ResponseEntity.ok(userService.pagination(currentPage, pageSize));
    }

    @GetMapping("/pagination/v1")
    public ResponseEntity<Page<User1>> pagination(Pageable pageable) {
        return ResponseEntity.ok(userService.pagination(pageable));
    }

    @GetMapping("/pagination/v2")
    public ResponseEntity<Slice<User1>> slice(Pageable pageable) {
        return ResponseEntity.ok(userService.slice(pageable));
    }

}
