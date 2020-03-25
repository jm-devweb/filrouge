package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_find_ById() throws Exception {
        final Long PARAM = 1L ;
        User user = User.builder ()
                .id (1L)
                .firstname ("Tera")
                .build ();

        Optional<User> find = this.userRepo.findById (PARAM);
        assertThat(find.get().getFirstname ()).isEqualTo(user.getFirstname ());
    }

    @Test
    public void should_find_all_users() throws Exception {

        // when
        Iterable<User> actual = this.userRepo.findAll();

        // then
        assertThat(actual)
       //         .hasSize(1)
                .doesNotContainNull();
    }

    @Test
    public void should_find_ByLogin() throws Exception {
        final String PARAM = "tcriple0" ;
        User user = User.builder ()
                .id (1L)
                .firstname ("Tera")
                .login ("tcriple0")
                .build ();

        User find = this.userRepo.findUserByLogin (PARAM);
        assertThat(find.getFirstname ()).isEqualTo(user.getFirstname ());
    }

    @Test
    public void should_save_user() throws Exception {
        User user = User.builder ()
                .firstname ("new")
                .lastname ("person")
                .login ("newlogin")
                .password ("toto")
                .email ("new@truc.fr")
                .gender (User.Gender.M)
                .build ();

        User find = this.userRepo.save (user);
        assertThat(find.getFirstname ()).isEqualTo(user.getFirstname ());
    }
}
