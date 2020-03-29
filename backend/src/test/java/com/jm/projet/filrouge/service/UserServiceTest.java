package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.mapper.DepartmentMapper;
import com.jm.projet.filrouge.mapper.UserMapper;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.repository.DepartmentRepository;
import com.jm.projet.filrouge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Column;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    UserRepository userRepo;

    @Mock
    UserMapper userMapper ;

    @BeforeEach
    public  void SetUp() {
        this.userService = new UserService(userRepo, userMapper);
    }

    @Test
    public void whenFindAll_thenReturnUserList() {
        // given
        User user = User.builder ()
                .id (1L)
                .firstname ("santa")
                .build ();

        List<User> expectedUsers = Arrays.asList(user);
        doReturn(expectedUsers).when(userRepo).findAll();

        // when
        List<UserDTO> actualUsers = userService.findAll();

        // then
        assertThat(actualUsers).isEqualTo(userMapper.INSTANCE.toListDTO (expectedUsers));
    }

    @Test
    public void whenFindById_thenReturnUser() {
        Optional<User> expectedUser = Optional.of( User.builder()
                .id (1L)
                .firstname ("santa")
                .build());

        doReturn(expectedUser).when(userRepo).findById (1L);

        // when
        UserDTO actualUser = userService.findById (1L);

        // then
        assertThat(actualUser).isEqualTo(userMapper.INSTANCE.toDTO (expectedUser.get ()));
    }


    @Test
    public void whenUserByLogin_thenReturnUser() {
        User user = User.builder ()
                .id (1L)
                .firstname ("santa")
                .build ();

        doReturn(user).when(userRepo).findUserByLogin ("login");

        // when
        UserDTO actualUsers = userService.findUserByLogin ("login");

        // then
        assertThat(actualUsers).isEqualTo(userMapper.INSTANCE.toDTO (user));
    }

    @Test
    public void should_save_user() {
        User user = User.builder ()
                .id (1L)
                .firstname ("santa")
                .lastname ("Claus")
                .login ("ppn")
                .password ("Test")
                .email ("test")
                .build ();

        doReturn(user).when(userRepo).save (user);


        UserDTO actualUsers = userService.save(userMapper.INSTANCE.toDTO (user));

        // then
        assertThat(userMapper.INSTANCE.toEntity (actualUsers))
                .isNotNull()
               .isEqualToComparingOnlyGivenFields(user, "id", "firstname");
    }
}
