package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.AuthenticationResponse;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.RegisterRequest;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForRegister;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForUpdate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service.AuthenticationService;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTOForRegister>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping("/update-user")
    public ResponseEntity<List<UserDTOForRegister>> updateUser(@RequestBody UserDTOForUpdate userDTO){
        try {
            userService.updateUser(userDTO);
            return ResponseEntity.ok(userService.getAllUsers());
        }catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserDTOForRegister>> deleteUser(@PathVariable UUID id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(userService.getAllUsers());
        }catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }
//    @PostMapping("")
//    public ResponseEntity manageUserDevicesRelationship(@RequestBody UserDTOForUpdate userDTO){
//        try {
//            userService.manageUserDevicesRelationship(userDTO);
//            return ResponseEntity.ok().build();
//        }catch (Exception exception){
//            return ResponseEntity.badRequest().build();
//        }
//    }

}
