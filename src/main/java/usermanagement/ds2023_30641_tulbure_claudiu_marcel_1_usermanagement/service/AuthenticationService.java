package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.exception.model.EmailExistentException;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.exception.model.UserNotFoundException;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.AuthenticationRequest;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.AuthenticationResponse;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.RegisterRequest;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.entity.User;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.mapper.UserMapper;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RestTemplate restTemplate;
    private final UserMapper userMapper;

    @Value("${device-management.url}")
    private String deviceManagementUrl;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) throws EmailExistentException {
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new EmailExistentException();
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new BadCredentialsException("Bad credentials"));
        try {
            restTemplate.postForEntity(deviceManagementUrl, userMapper.entityToDTOForDevices(user), String.class);
        } catch (Exception e) {
            userRepository.delete(user);
            throw new EmailExistentException();
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new BadCredentialsException("Bad credentials"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }


}
