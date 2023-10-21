package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.connector.Response;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.AuthenticationRequest;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.AuthenticationResponse;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.RegisterRequest;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.entity.User;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.mapper.UserMapper;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserMapper userMapper;

    @Value("${device-management.url}")
    private String deviceManagementUrl;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("Email already exists");
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        restTemplate.postForEntity(deviceManagementUrl, userMapper.entityToDTOForDevices(user), String.class);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }


}
