package ru.otus.java.pro.transferservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.java.pro.transferservice.entities.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}