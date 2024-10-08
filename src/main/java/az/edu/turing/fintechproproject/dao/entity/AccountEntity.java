package az.edu.turing.fintechproproject.dao.entity;

import az.edu.turing.fintechproproject.model.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @Column(name = "pin", length = 4)
    private String pin;

    @Column(name = "cvv", length = 3)
    private String cvv;

}
