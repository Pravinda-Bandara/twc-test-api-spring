package lk.twc.intern.test.service.impl;

import lk.twc.intern.test.entity.User;
import lk.twc.intern.test.repository.UserRepository;
import lk.twc.intern.test.service.UserService;
import lk.twc.intern.test.to.UserTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserTO saveUser(UserTO userTO) {
        // Map UserTO to User entity
        User user = modelMapper.map(userTO, User.class);
        System.out.println(user.getUserName());

        // Check if the username already exists
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Hash the password
        String hashedPassword = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt());

        // Set the hashed password to the user entity
        user.setUserPassword(hashedPassword);

        // Save the user
        userRepository.save(user);

        // Map the saved user back to UserTO and return
        return modelMapper.map(user, UserTO.class);
    }


    @Override
    public UserTO getUser(UserTO userTO) {
        // Find the user by username
        Optional<User> optionalUser = userRepository.findByUserName(userTO.getUserName());

        // Check if the user exists
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = optionalUser.get();

        // Verify the password
        if (!BCrypt.checkpw(userTO.getUserPassword(), user.getUserPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Map the user to UserTO and return
        return modelMapper.map(user, UserTO.class);
    }

}
