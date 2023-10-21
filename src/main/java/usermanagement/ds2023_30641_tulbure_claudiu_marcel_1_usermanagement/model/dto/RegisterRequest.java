package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto;

import lombok.*;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.enums.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private List<Role> role;
}
