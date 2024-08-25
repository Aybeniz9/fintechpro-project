package az.edu.turing.fintechproproject.service;

import az.edu.turing.fintechproproject.dao.repository.AccountRepository;
import az.edu.turing.fintechproproject.mapper.AccountMapper;
import az.edu.turing.fintechproproject.model.dto.AccountDto;
import az.edu.turing.fintechproproject.dao.entity.AccountEntity;
import az.edu.turing.fintechproproject.model.enums.AccountStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public Long createAccount(AccountDto accountDto) {
        AccountEntity accountEntity = accountMapper.dtoToEntity(accountDto);

        accountEntity = accountRepository.save(accountEntity);

        log.info("Created account with ID: {}", accountEntity.getId());

        return accountEntity.getId();
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        log.info("Retrieved balance for account ID {}: {}", accountId, accountEntity.getBalance());
        return accountEntity.getBalance();
    }

    @Transactional(readOnly = true)
    public AccountDto getAccountById(Long accountId) {
        Optional<AccountEntity> accountOptional = accountRepository.findById(accountId);

        return accountOptional.map(accountMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Transactional(readOnly = true)
    public AccountStatus getAccountStatus(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        log.info("Retrieved account status for account ID {}: {}", accountId, accountEntity.getAccountStatus());
        return accountEntity.getAccountStatus();
    }

    @Transactional
    public void blockAccount(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountEntity.setAccountStatus(AccountStatus.BLOCKED);
        accountRepository.save(accountEntity);

        log.info("Blocked account with ID: {}", accountId);
    }

    @Transactional
    public void activateAccount(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        accountEntity.setAccountStatus(AccountStatus.ACTIVE);
        accountRepository.save(accountEntity);

        log.info("Activated account with ID: {}", accountId);
    }


    @Transactional
    public void updateAccount(Long accountId, AccountDto accountDto) {

        AccountEntity existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        AccountEntity updatedAccount = accountMapper.dtoToEntity(accountDto);
        updatedAccount.setId(existingAccount.getId());

        accountRepository.save(updatedAccount);

        log.info("Updated account with ID: {}", updatedAccount.getId());
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);

        log.info("Deleted account with ID: {}", accountId);
    }

    @Transactional(readOnly = true)
    public List<AccountDto> getAllAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();

        return accountMapper.entityListToDtoList(accounts);
    }
}
