package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.mapper.UserMapper;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.jm.projet.filrouge.repository.UserSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

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

    /**
     * Filter users
     *
     * @param pageable the page to get
     * @param gender the gender to get (optional)
     * @param ageCategory the age category to get (optional)
     * @param pseudo the login to get (optional)
     * @param regionId the region id to get (optional)
     * @param departmentId the department id to get (optional)
     * @return a page of users
     */
    public Page<UserDTO> getFilteredUsers(
            Pageable pageable, User.Gender gender, String ageCategory, String pseudo, int regionId, int departmentId) {
        // Find filtered users in the repository
        Page<User> userPage = userRepository.findAll(
                where(hasPseudoOptional(pseudo)
                        .and(hasGenderOptional(gender))
                        .and(hasDepartmentOptional(departmentId))
                        .and(hasRegionOptional(regionId))
                        .and(hasAgeCategoryOptional(ageCategory))), pageable);

        return userPage.map(user -> userMapper.INSTANCE.toDTO(user));
    }
}
