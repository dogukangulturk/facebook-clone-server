package com.orcrist.facebookcloneserver.util;

import com.orcrist.facebookcloneserver.dto.auth.RegisterDto;
import com.orcrist.facebookcloneserver.model.User;
import com.orcrist.facebookcloneserver.repository.UserRepository;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class GenerateUsername {

    private final UserRepository userRepository;

    public GenerateUsername(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void build(User user, RegisterDto dto){
        String  username = dto.getName() + "-" + dto.getLastname();
        Random random = new Random();
        int randomNum = 0;
        if (userRepository.findByUsername(username).isEmpty())
            user.setUsername(username);
        else
            do {
                while (randomNum <= 0)
                    randomNum = random.nextInt();
                user.setUsername(username + "-" + randomNum);
            }while (userRepository.findByUsername(username + "-" + randomNum).isPresent());
    }
}
