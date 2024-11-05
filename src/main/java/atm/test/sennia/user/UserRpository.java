package atm.test.sennia.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRpository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
