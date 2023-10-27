package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto;

import lombok.*;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.enums.Role;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOForUpdate {

    @NonNull
    private UUID id;
    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private List<Role> role;
    private String password;

}
