package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForRegister;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto.UserDTOForUpdate;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTOForUpdate>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping("")
    public ResponseEntity<List<UserDTOForUpdate>> updateUser(@RequestBody UserDTOForUpdate userDTO){
        try {
            userService.updateUser(userDTO);
            return ResponseEntity.ok(userService.getAllUsers());
        }catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserDTOForUpdate>> deleteUser(@PathVariable UUID id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(userService.getAllUsers());
        }catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }


}
