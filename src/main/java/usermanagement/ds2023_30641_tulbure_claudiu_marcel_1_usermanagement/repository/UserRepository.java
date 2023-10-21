package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.model.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

}
