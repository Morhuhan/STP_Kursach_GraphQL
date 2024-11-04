package STP_KURS.GraphQL;

import STP_KURS.GraphQL.Entities.User;
import STP_KURS.GraphQL.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User user1 = new User();
                user1.setUsername("user1");
                user1.setPassword(passwordEncoder.encode("password1"));
                User user2 = new User();
                user2.setUsername("user2");
                user2.setPassword(passwordEncoder.encode("password2"));
                userRepository.save(user1);
                userRepository.save(user2);
                System.out.println("Test users created.");
            }
        };
    }
}