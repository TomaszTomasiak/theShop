package com.theshop.mapper;

import com.theshop.domain.User;
import com.theshop.domain.dto.UserDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        User userBean = new User();
        userBean.setId(userDto.getId());
        userBean.setFirstName(userDto.getFirstName());
        userBean.setLastName(userDto.getLastName());
        userBean.setMailAdress(userDto.getMailAdress());
        userBean.setPhoneNumber(userDto.getPhoneNumber());
        userBean.setPassword(userDto.getPassword());

        return userBean;
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMailAdress(),
                user.getPhoneNumber(),
                user.getPassword()
        );
    }

    public List<User> mapToUserList(final List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
