package com.equitasit.user_service.controller;

import com.equitasit.user_service.dto.UserDTO;
import com.equitasit.user_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity save(@RequestBody UserDTO userDTO) {

        log.debug("enter");
        log.info("saving the user info {}", userDTO);
        UserDTO savedUserDTO = userService.save(userDTO);
        log.info("saved user info {}", userDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody UserDTO userDTO) {
        log.debug("enter");
        log.info("updating the user info {}", userDTO);
        UserDTO savedUserDTO = userService.update(userDTO);
        log.info("updated user info {}", savedUserDTO);
        log.debug("exit");
        return ResponseEntity.ok(savedUserDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer userID) {
        log.debug("enter");
        log.info("getting user info for id {}", userID);
        UserDTO userDTO = userService.get(userID);
        log.info("user info {}", userDTO);
        log.debug("exit");
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Integer userId) {

        log.debug("enter");
        log.info("removing user info for id {}", userId);

        userService.remove(userId);

        log.info("getting user info for id {}", userId);
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity get() {
        log.debug("enter");

        List<UserDTO> userDTOList = userService.getAll();

        log.info("users size {}", userDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("lastLogin")
    public ResponseEntity lastLogin(@RequestBody UserDTO userDTO) {
        log.debug("enter");
        log.info("updating the user info {}", userDTO);
        UserDTO savedUserDTO = userService.saveLastLoginInfo(userDTO);
        log.info("updated user info {}", savedUserDTO);
        log.debug("exit");
        return ResponseEntity.ok(savedUserDTO);
    }


}
