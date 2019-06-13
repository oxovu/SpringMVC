package ru.Technopolis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.Technopolis.model.entities.TodoUser;
import ru.Technopolis.model.repos.TodoUserRepository;

@Service
public class DBUserDetailsService implements UserDetailsService {

    @Autowired
    private TodoUserRepository todoUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TodoUser user = todoUserRepository.findTodoUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new TodoUserPrincipal(user);
    }
}
