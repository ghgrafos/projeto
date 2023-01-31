package com.vmx.projeto.service;

import java.util.List;

import com.vmx.projeto.model.User;

public interface UserService {
    public void saveUser(User user);
    public List<Object> isUserPresent(User user);
}
