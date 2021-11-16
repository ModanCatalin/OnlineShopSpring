package ro.sd.a2.service.mapper;

import org.springframework.stereotype.Component;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.User;

@Component
public class UserMapper
{
    public void map(UserDto userDto, User user)
    {
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
    }

    public UserDto mapUser(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
