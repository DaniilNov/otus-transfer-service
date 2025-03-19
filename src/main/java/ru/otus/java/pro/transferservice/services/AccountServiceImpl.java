package ru.otus.java.pro.transferservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.transferservice.entities.Account;
import ru.otus.java.pro.transferservice.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService  {

    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


}