package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto;

import lombok.*;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.enums.Role;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOForRegister {

    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private List<Role> role;

}
