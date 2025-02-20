package ru.otus.java.pro.transferservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;
}