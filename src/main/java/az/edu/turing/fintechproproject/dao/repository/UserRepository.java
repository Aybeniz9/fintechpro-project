package az.edu.turing.fintechproproject.dao.repository;

import az.edu.turing.fintechproproject.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
