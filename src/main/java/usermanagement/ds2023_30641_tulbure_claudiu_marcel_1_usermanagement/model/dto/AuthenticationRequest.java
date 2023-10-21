package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto;


import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;


}
