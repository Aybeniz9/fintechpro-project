package az.edu.turing.fintechproproject.dao.repository;

import az.edu.turing.fintechproproject.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
}