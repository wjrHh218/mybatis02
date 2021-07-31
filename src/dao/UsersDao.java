package dao;

import pojo.Users;

import java.util.List;

public interface UsersDao {
    void addUser(Users user);
    void updateUser(Users user);
    void deleteUser(Users user);
    List<Users> findUsers();
    List<Users> findUsersByName(String name);
}
