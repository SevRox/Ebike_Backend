package tech.getarays.Ebike.Backend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarays.Ebike.Backend.NotFoundExeption;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User findUserById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new NotFoundExeption("User by id: " + id + " was not found"));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public Long deleteUserByLogin(String login){
        return userRepo.deleteBylogin(login);
    }

    public List<User> findUserByLogin(String login) {
        return userRepo.findAllBylogin(login);
    }
}

