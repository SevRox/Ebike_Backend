package tech.getarays.backend.service;

import tech.getarays.backend.model.Role;
import tech.getarays.backend.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers(); // Not good (performance wise) if there is huge amount of user better implementations is with page.

}
