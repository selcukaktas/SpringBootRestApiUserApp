package com.aktas.selcuk.service.impl;

import com.aktas.selcuk.dto.UserDto;
import com.aktas.selcuk.entity.User1;
import com.aktas.selcuk.exception.UserNotFound;
import com.aktas.selcuk.repository.UserRepository;
import com.aktas.selcuk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // lombok yardımı ile yapıcıyı oto oluşturuyor. altta kapattığım gibi constructor da yapabilirdik.
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper  modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User1 user = modelMapper.map(userDto, User1.class);
        user.setCreatedDate(new Date());
        user.setCreatedBy("Admin");
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User1> users = userRepository.findAll();
        List<UserDto> dtos = users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User1> user = userRepository.findById(id);
        if(user.isPresent()) {
            UserDto userDto = modelMapper.map(user.get(), UserDto.class);
            return userDto;
        }
        throw new UserNotFound("User bulunamadı.");
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        Optional<User1> resultUser = userRepository.findById(id);
        if(resultUser.isPresent()) {
            resultUser.get().setFirstName(user.getFirstName());
            resultUser.get().setLastName(user.getLastName());
            resultUser.get().setUpdatedAt(new Date());
            resultUser.get().setUpdatedBy("Admin");
            return modelMapper.map(userRepository.save(resultUser.get()), UserDto.class);   //userRepository.save();
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        Optional<User1> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return null;

    }

    @Override
//    public Page<UserDto> pagination(int currentPage, int pageSize) {
//        Pageable pageable = PageRequest.of(currentPage, pageSize);
//        Page<User1> users = userRepository.findAll(pageable);
//        Page<UserDto> dtos = Page.map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
//        return dtos;
        public Page<User1> pagination(int currentPage, int pageSize) {
            Pageable pageable = PageRequest.of(currentPage, pageSize);
            return userRepository.findAll(pageable);
    }

    @Override
    public Page<User1> pagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Slice<User1> slice(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
}
//        page.map(student -> new StudentDTO(student));


