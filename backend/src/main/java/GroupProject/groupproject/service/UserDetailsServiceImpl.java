package GroupProject.groupproject.service;

import GroupProject.groupproject.entity.AuthUsers;
import GroupProject.groupproject.repository.AuthUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthUsersRepository authUsersRepository;

    @Autowired
    public UserDetailsServiceImpl(AuthUsersRepository authUsersRepository) {
        this.authUsersRepository = authUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUsers user = authUsersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));

        return new User(user.getUsername(),
                        user.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        new HashSet<>());
    }
}
