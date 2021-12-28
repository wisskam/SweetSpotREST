package com.sweetspotrecipies.sweetspot.controller.api;

import com.sweetspotrecipies.sweetspot.api.mapper.UserMapper;
import com.sweetspotrecipies.sweetspot.api.mapper.RecipeMapper;
import com.sweetspotrecipies.sweetspot.api.model.BookDTO;
import com.sweetspotrecipies.sweetspot.api.model.UserDTO;
import com.sweetspotrecipies.sweetspot.model.User;
import com.sweetspotrecipies.sweetspot.security.BookUserDetails;
import com.sweetspotrecipies.sweetspot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${url.prefix.api.v1}/user")
public class RestUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    @GetMapping("/")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Iterable<UserDTO>> index() {
        try {
            return new ResponseEntity<>(
                    userMapper.map(
                            userService.listAll()
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        try {
            BookUserDetails bookUserDetails = (BookUserDetails) authentication.getPrincipal();
            User userEntity = userService.find(bookUserDetails.getId());
            return new ResponseEntity(
                    userMapper.userToUserDTO(
                            userEntity
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
        try {
            User userEntity = userService.find(id);
            return new ResponseEntity(
                    userMapper.userToUserDTO(
                            userEntity
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer id,
                                                @RequestBody UserDTO userDTO) {
        try {
            User userEntity = userService.find(id);
            if (userEntity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.save(
                    userMapper.userDTOToUser(
                            userDTO
                    )
            );
            return new ResponseEntity<>(
                    userMapper.userToUserDTO(
                            userEntity
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        try {
            User userEntity = userService.find(id);
            if (userEntity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}