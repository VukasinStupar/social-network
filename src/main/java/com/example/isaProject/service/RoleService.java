package com.example.isaProject.service;

import com.example.isaProject.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findByName(String name);
}
