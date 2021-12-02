package org.leverx.dealerstat.services;

import org.leverx.dealerstat.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();

    void save(User user);
}
