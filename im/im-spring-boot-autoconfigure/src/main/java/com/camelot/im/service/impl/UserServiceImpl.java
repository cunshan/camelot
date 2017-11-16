package com.camelot.im.service.impl;

import com.camelot.im.domain.User;
import com.camelot.im.repository.UserRepository;
import com.camelot.im.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;


  @Override
  public User login(User param) {
    return userRepository
        .findDistinctByLoginAccountAndPassword(param.getLoginAccount(), param.getPassword());
  }
}
