package com.example.isaProject.serviceImpl;

import com.example.isaProject.model.Role;
import com.example.isaProject.repository.RoleRepository;
import com.example.isaProject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
