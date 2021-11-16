package ro.sd.a2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.User;
import ro.sd.a2.factory.UserFactory;
import ro.sd.a2.factory.UserRoleEnum;
import ro.sd.a2.service.mapper.UserMapper;
import ro.sd.a2.service.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService
{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    /**
     * Metoda care creaza un user
     *
     * @param userDto - input param whatever
     * @return - ceva
     * @throws ArithmeticException - when ceva/0
     */
    public String createUser(UserDto userDto)
    {

        User user = UserFactory.generateUserWithRole(UserRoleEnum.REGULAR);

        userMapper.map(userDto, user);

        userRepository.save(user);

        log.info("Successfully created user " + "nume user");
        return "user created successfully";
    }

    public String createAdmin(UserDto userDto)
    {

        User user = UserFactory.generateUserWithRole(UserRoleEnum.ADMIN);

        userMapper.map(userDto, user);

        userRepository.save(user);

        log.info("Successfully created user " + "nume user");
        return "user created successfully";
    }


    public User findByName(String name) throws Exception
    {
        User user = userRepository.findByName(name);
        if (user == null)
        {
            throw new Exception("cannot find User");
        }
        else
        {
            return user;
        }
    }

    public List<UserDto> getAllUsers()
    {

        return userRepository.findAll().stream().map(userMapper::mapUser).collect(Collectors.toList());

    }

    public String deleteUser(UserDto userDto)
    {

        if (userRepository.deleteByEmail(userDto.getEmail()) < 1)
        {
            return "user not found";
        }

        return "user deleted";

    }

    public String updateUser(UserDto userDto)
    {

        Optional<User> oldUser = userRepository.findByEmail(userDto.getEmail());

        if (oldUser.isPresent())
        {
            userMapper.map(userDto, oldUser.get());
            userRepository.save(oldUser.get());
            return "user updated successfully";
        }

        return "user not found";

    }



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        return userRepository.findByEmail(s).get();
    }
}
