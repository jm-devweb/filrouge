package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.service.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/api/users")
@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.findAll ( );
        return users.isEmpty ( ) ? ResponseEntity.noContent ( ).build ( ) : ResponseEntity.ok (users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable(value = "userId")
                    Long userId) {
        UserDTO userDTO = userService.findById (userId);
        return Objects.isNull (userDTO) ? ResponseEntity.notFound ( ).build ( ) : ResponseEntity.ok (userDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> getUserByLogin(@RequestParam(required = true) String user) {
        UserDTO userDTO = userService.findUserByLogin (user);
        return Objects.isNull (userDTO) ? ResponseEntity.notFound ( ).build ( ) : ResponseEntity.ok (userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok (userService.save (userDTO));
    }

    /**
     * Filter users
     *
     * @param pageable the page to get
     * @param gender the gender to get (optional)
     * @param ageCategory the age category to get (optional)
     * @param login the login to get (optional)
     * @param region the region id to get (optional)
     * @param department the department id to get (optional)
     * @return a page of users
     */
    @GetMapping("/filter")
    @PageableAsQueryParam
    public Page<UserDTO> getFilteredUsers(
            @PageableDefault(size=25, page = 0, direction = Sort.Direction.ASC) @Parameter(hidden=true) Pageable pageable,
            @ApiParam(value = "Query param for 'gender'") @Valid @RequestParam(value = "gender", defaultValue = "") String gender,
            @ApiParam(value = "Query param for 'ageCategory'") @Valid @RequestParam(value = "ageCategory", defaultValue = "") String ageCategory,
            @ApiParam(value = "Query param for 'login'") @Valid @RequestParam(value = "login", defaultValue = "") String login,
            @ApiParam(value = "Query param for 'region'") @Valid @RequestParam(value = "region", defaultValue = "0") Integer region,
            @ApiParam(value = "Query param for 'department'") @Valid @RequestParam(value = "department", defaultValue = "0") Integer department
    ) {
        // Transtypage gender String -> Enum User.Gender
        User.Gender g;
        switch (gender){
            case "M":
                g = User.Gender.M;
                break;
            case "F":
                g = User.Gender.F;
                break;
            case "N":
                g = User.Gender.N;
                break;
            default:
                g = null;
        }

        return userService.getFilteredUsers(pageable, g, ageCategory, login, region, department);
    }

/*
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO,
                                 HttpServletResponse httpResponse,
                                 WebRequest request) {

        UserDTO saveUser = userService.save (userDTO);
        httpResponse.setStatus(HttpStatus.CREATED.value());
        httpResponse.setHeader("Location", String.format("%s/api/users/%s",
                request.getContextPath(), saveUser.getId ()));
        return ResponseEntity.ok(saveUser);
    }
*/



    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO testUserDTO = userService.findById (userDTO.getId ( ));
        return Objects.isNull (testUserDTO) ? ResponseEntity.badRequest ( ).build ( ) : ResponseEntity.ok (userService.save (userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        UserDTO testUserDTO = userService.findById (id);
        if (Objects.isNull (testUserDTO)) {
            ResponseEntity.badRequest ( ).build ( );
        }
        userService.delete (id);
        return ResponseEntity.ok ( ).build ( );
    }
}
