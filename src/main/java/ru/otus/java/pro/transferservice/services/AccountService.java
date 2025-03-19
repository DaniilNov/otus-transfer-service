package ru.otus.java.pro.transferservice.services;

import ru.otus.java.pro.transferservice.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Long id);
    List<Account> getAllAccounts();
}