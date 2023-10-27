package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.exception.model.UserNotFoundException;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.RegisterRequest;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForRegister;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForUpdate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.entity.User;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.mapper.UserMapper;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    @Value("${device-management.url}")
    private String deviceManagementUrl;

    public List<UserDTOForUpdate> getAllUsers() {
        return userMapper.entityListToDTO(userRepository.findAll());
    }

    @Transactional
    public void updateUser(UserDTOForUpdate userDTO) throws UserNotFoundException {

        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }


    @Transactional
    public void deleteUser(UUID id) throws UserNotFoundException {
        if(userRepository.existsById(id)){
            try {
                restTemplate.delete(deviceManagementUrl+"/"+id, Response.class);
            }catch (HttpClientErrorException exception){
                throw new UserNotFoundException();
            }
            userRepository.deleteById(id);

        }else {
            throw new UserNotFoundException();
        }
    }


}
