package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service;


import jakarta.transaction.Transactional;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForRegister;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForUpdate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.mapper.UserMapper;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${device-management.url}")
    private String deviceManagementUrl;

    public List<UserDTOForRegister> getAllUsers() {
        return userMapper.entityListToDTO(userRepository.findAll());
    }

    @Transactional
    public void updateUser(UserDTOForUpdate userDTO) {
        if(userRepository.existsById(userDTO.getId())){
            userRepository.save(userMapper.dtoUpdateToEntity(userDTO));
        }else {
            throw new RuntimeException("User not found");
        }
    }

    @Transactional
    public void deleteUser(UUID id) {
        if(userRepository.existsById(id)){
            restTemplate.delete(deviceManagementUrl, id, Response.class);
            userRepository.deleteById(id);
        }else {
            throw new RuntimeException("User not found");
        }
    }


}
