package com.equitasit.user_service.service;

import com.equitasit.user_service.dto.UserDTO;
import com.equitasit.user_service.entity.User;
import com.equitasit.user_service.exception.UserException;
import com.equitasit.user_service.repository.UserRepository;
import com.equitasit.user_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        log.debug("enter");
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        log.debug("exit");
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        log.debug("enter");
        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        if (!optionalUser.isPresent()) {
            log.error("user not found for user id {} ", userDTO.getId());
            throw new UserException(MsgConstants.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        modelMapper.map(userDTO, user);
        User savedUser = userRepository.save(user);
        log.debug("exit");
        return modelMapper.map(savedUser, UserDTO.class);

    }

    public UserDTO get(Integer userId) {
        log.debug("enter");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            log.error("user not found for user id {} ", userId);
            throw new UserException(MsgConstants.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        log.debug("exit");
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAll() {
        log.debug("enter");
        List<UserDTO> list = userRepository.findAll().stream().map(p -> modelMapper.map(p, UserDTO.class)).collect(Collectors.toList());
        log.info("users size {} ", list.size());
        log.debug("users list {} ", list);
        log.debug("exit");
        return list;
    }

    @Transactional
    public void remove(Integer userId) {
        log.debug("enter");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            log.error("user not found for user id {} ", userId);
            throw new UserException(MsgConstants.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        userRepository.delete(user);
        log.info("delete success  for user {} ", user);
        log.debug("exit");
    }

    @Transactional
    public UserDTO saveLastLoginInfo(UserDTO userDTO) {

        userDTO.setLastLoginTime(new Date());

        UserDTO savedUserDTO;

        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());
        if (optionalUser.isPresent()) {
            userDTO.setId(optionalUser.get().getId());
            savedUserDTO = update(userDTO);
        } else {
            savedUserDTO = save(userDTO);
        }

        return savedUserDTO;
    }


}
