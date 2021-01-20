package ru.mishapp.spring_boot.dao;

import ru.mishapp.spring_boot.models.Role;

import java.util.List;

public interface RoleDao {
    Role getRole(long id);
    List<Role> getAllRoles();
}
