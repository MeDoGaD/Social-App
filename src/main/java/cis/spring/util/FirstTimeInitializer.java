package cis.spring.util;

import cis.spring.users.User;
import cis.spring.users.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstTimeInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    private Log logger= LogFactory.getLog(FirstTimeInitializer.class);

    @Override
    public void run(String... args) throws Exception {
        if(userService.getAllUsers().isEmpty())
        {
         logger.info("*********There is no users , we just create one user for you :)");
         userService.saveUser(new User("Mohamed","medo@gmail.com","medo123"));
        }
    }
}
