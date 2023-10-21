package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @NonNull
    private String token;

}
