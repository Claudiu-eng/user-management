package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.mapper;

import org.springframework.stereotype.Component;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForRegister;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForDevices;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForUpdate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.entity.User;

import java.util.List;

@Component
public class UserMapper {

    private UserDTOForRegister entityToDTORegister(User user){
        return UserDTOForRegister.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public List<UserDTOForRegister> entityListToDTO(List<User> users){
        return users.stream().map(this::entityToDTORegister).toList();
    }

    public UserDTOForDevices entityToDTOForDevices(User user){
        return UserDTOForDevices.builder()
                .id(user.getId())
                .build();
    }


    public User dtoUpdateToEntity(UserDTOForUpdate userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .build();
    }
}
