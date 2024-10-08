package az.edu.turing.fintechproproject.dao.repository;

import az.edu.turing.fintechproproject.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
