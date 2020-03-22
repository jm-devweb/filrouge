package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.mapper.UserMapper;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO findById(Long userId) {
        Optional<User> found = userRepository.findById(userId);
        return found.isPresent () ? userMapper.INSTANCE.toDTO (found.get ()) :  null ;
    }

    public List<UserDTO> findAll() {
        List<User> found = userRepository.findAll();
        return userMapper.INSTANCE.toListDTO (found) ;
    }

    public UserDTO findUserByLogin(String login) {
        User found = userRepository.findUserByLogin (login);
        return userMapper.INSTANCE.toDTO (found) ;
    }

    public UserDTO save(UserDTO userDTO) {
        return userMapper.INSTANCE.toDTO(userRepository.save(userMapper.INSTANCE.toEntity(userDTO)));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
