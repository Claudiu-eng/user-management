package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.AuthenticationRequest;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.AuthenticationResponse;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.RegisterRequest;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service.AuthenticationService;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
