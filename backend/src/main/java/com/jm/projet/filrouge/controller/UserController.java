package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.UserDTO;
import com.jm.projet.filrouge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO,
                                 HttpServletResponse httpResponse,
                                 WebRequest request) {

        UserDTO saveUser = userService.save (userDTO);
        httpResponse.setStatus(HttpStatus.CREATED.value());
        httpResponse.setHeader("Location", String.format("%s/api/users/%s",
                request.getContextPath(), saveUser.getId ()));
        return ResponseEntity.ok(saveUser);
    }



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
