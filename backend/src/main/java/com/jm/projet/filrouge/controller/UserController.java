package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get details of a specific user",
            response = UserDTO.class,
            notes = "Get details of a specific user",
            nickname = "getUserById")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 400, message = "Invalid type value"),
                    @ApiResponse(code = 401, message = "Unauthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Vehicle not found"),
                    @ApiResponse(code = 500, message = "UnexpectedError")
            })
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable(value = "userId")
                    Long userId) {
        UserDTO userDTO = userService.findById (userId);

       // ResponseEntity.ok().h.body(userDTO);
        return Objects.isNull (userDTO) ? ResponseEntity.notFound ( ).build ( ) : ResponseEntity.ok (userDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> getUserByLogin(@RequestParam(required = true) String user) {
        UserDTO userDTO = userService.findUserByLogin (user);
        return Objects.isNull (userDTO) ? ResponseEntity.notFound ( ).build ( ) : ResponseEntity.ok (userDTO);
    }

    @PostMapping
    @ApiOperation(
            value = "New user",
            response = UserDTO.class,
            notes = "Create user",
            nickname = "create")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 400, message = "Invalid type value"),
                    @ApiResponse(code = 401, message = "Unauthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 404, message = "Vehicle not found"),
                    @ApiResponse(code = 500, message = "UnexpectedError")
            })
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.save (userDTO) ;
        return Objects.isNull (userDTO) ? ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build ()
                 : ResponseEntity.status (HttpStatus.CREATED).body (newUser);

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
    public Page<UserDTO> getFilteredUsers(
            @PageableDefault(size=25, page = 0, direction = Sort.Direction.ASC) Pageable pageable,
            @ApiParam(value = "Query param for 'gender'") @Valid @RequestParam(value = "gender", defaultValue = "") String gender,
            @ApiParam(value = "Query param for 'category'") @Valid @RequestParam(value = "category", defaultValue = "") String category,
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

        return userService.getFilteredUsers(pageable, g, category, login, region, department);
    }

  /*  @PostMapping
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
