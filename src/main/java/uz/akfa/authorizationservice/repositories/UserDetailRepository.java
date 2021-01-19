package uz.akfa.authorizationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.akfa.authorizationservice.entities.UserEntity;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}

