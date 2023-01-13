package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String role);

    User changeRole(String username, String role);

    List<User> getAll();
}
