package cis.spring.users;

import cis.spring.errors.ConflictException;
import cis.spring.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public void saveUser(User user)
    {
        boolean found = checkValidUser(user);
        if(found) {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
        }
        else
                throw new ConflictException("There is a user with the same email !!");
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByEmail(username);
    }

    public boolean checkValidUser(User u){
        User user=getUserByUsername(u.getEmail());
        if(user==null)
            return true;
        else
            return false;
    }

    public List<User>getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails=userRepository.getUserByEmail(username);
        if(userDetails==null)
            throw new NotFoundException("There isn't a user with this username");
        else
            return userDetails;
    }
}
