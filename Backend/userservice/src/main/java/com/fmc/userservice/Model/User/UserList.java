package com.fmc.userservice.Model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a wrapper class which holds a list of User objects.
 *
 * This allows for backward compatibility due to the ability of returning a JSON object rather than a list.
 */

public class UserList {
    private List<User> users;

    public UserList(){
        users = new ArrayList<>();
    }

    public UserList(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonIgnore
    public Boolean isEmpty(){
        return users.isEmpty();
    }
}
