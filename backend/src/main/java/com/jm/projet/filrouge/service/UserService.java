package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.mapper.UserMapper;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

    @Value("${users.faces.female.young}")
    private String femaleYoungIcon;

    @Value("${users.faces.female.adult}")
    private String femaleAdultIcon;

    @Value("${users.faces.female.older}")
    private String femaleOlderIcon;

    @Value("${users.faces.female.old}")
    private String femaleOldIcon;

    @Value("${users.faces.male.young}")
    private String maleYoungIcon;

    @Value("${users.faces.male.adult}")
    private String maleAdultIcon;

    @Value("${users.faces.male.older}")
    private String maleOlderIcon;

    @Value("${users.faces.male.old}")
    private String maleOldIcon;

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

    /**
     *Save User
     *
     * @param userDTO
     * @return
     */
    public UserDTO save(UserDTO userDTO) {
        // Get the current user
        Optional<User> user = this.userRepository.findById (userDTO.getId ( ));
        // Get the user to save
        User userToSave = userMapper.INSTANCE.toEntity (userDTO);
        // Add the not updatable attributes
        if (user.isPresent ( )) {
            userToSave.setPassword (user.get ( ).getPassword ( ));
            userToSave.setDateCreation (user.get ( ).getDateCreation ( ));
            userToSave.setAge (this.updateAge (user.get ( )).getAge ( ));
        }
        return userMapper.INSTANCE.toDTO (userRepository.save (userToSave));
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
        Page<User> userPage = userRepository.findAll(
                where(hasPseudoOptional(pseudo)
                        .and(hasGenderOptional(gender))
                        .and(hasDepartmentOptional(departmentId))
                        .and(hasRegionOptional(regionId))
                        .and(hasAgeCategoryOptional(ageCategory))
                ), pageable);

        return userPage.map(user -> userMapper.INSTANCE.toDTO(user));
    }

    /**
     * Get users filtered on a birthday date
     * @param pageable
     * @return
     */
    public Page<UserDTO> getUsersByBirthday(Pageable pageable) {
        LocalDate localDate = LocalDate.now();
        Page<User> userPage = userRepository.findAllByBirthday(pageable, localDate.getMonthValue(), localDate.getDayOfMonth());

        return userPage.map(user -> userMapper.INSTANCE.toDTO(user));
    }

    /**
     * Update ages and avatar icons for all users every day at 5h
     * This method is also executed when the server starts
     */
    @PostConstruct
    @Scheduled(cron="0 0 5 * * *")
    public void updateUserAges() {
        // Get all users
        List<User> users = this.userRepository.findAll();
        for(User user : users) {
            this.updateAge(user);
            // Save the user into the db
            this.userRepository.saveAndFlush(user);
        }
    }

    /**
     * Uodate the age and the default avatar of a user
     *
     * @param user
     * @return
     */
    private User updateAge(User user) {
        // Get current date
        LocalDate endDate = LocalDate.now();
        // Calculate age and update the user
        LocalDate startDate = user.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        long age = ChronoUnit.YEARS.between(startDate, endDate);
        user.setAge((int) age);

        // Update female age icon
        if(user.getGender() == User.Gender.F && age > 50) {
            user.setAvatar(femaleOldIcon);
        }
        if(user.getGender() == User.Gender.F && age <= 50) {
            user.setAvatar(femaleOlderIcon);
        }
        if(user.getGender() == User.Gender.F && age <= 35) {
            user.setAvatar(femaleAdultIcon);
        }
        if(user.getGender() == User.Gender.F && age <= 25) {
            user.setAvatar(femaleYoungIcon);
        }
        // Update male age icon
        if(user.getGender() == User.Gender.M && age > 50) {
            user.setAvatar(maleOldIcon);
        }
        if(user.getGender() == User.Gender.M && age <= 50) {
            user.setAvatar(maleOlderIcon);
        }
        if(user.getGender() == User.Gender.M && age <= 35) {
            user.setAvatar(maleAdultIcon);
        }
        if(user.getGender() == User.Gender.M && age <= 25) {
            user.setAvatar(maleYoungIcon);
        }

        return user;
    }

}
