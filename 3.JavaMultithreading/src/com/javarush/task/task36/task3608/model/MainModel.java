package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.dao.UserDao;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model{

    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> usersBetweenLevels = getAllUsers();
        modelData.setUsers(usersBetweenLevels);
        modelData.setDisplayDeletedUserList(false);
    }

    public void loadDeletedUsers() {
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(true);
    }

    @Override
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
        modelData.setDisplayDeletedUserList(false);
    }

    public void deleteUserById(long id){
        userService.deleteUser(id);
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllUsers());
    }

    public void changeUserData(String name, long id, int level){
        User orUpdateUser = userService.createOrUpdateUser(name, id, level);
        modelData.setActiveUser(orUpdateUser);
        modelData.setUsers(getAllUsers());
    }

    private List<User> getAllUsers() {
       return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1,100));
    }



}
