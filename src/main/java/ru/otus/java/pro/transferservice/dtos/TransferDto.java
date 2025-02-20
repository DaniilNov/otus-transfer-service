package ru.otus.java.pro.transferservice.dtos;

public record TransferDto(String id, String clientId, String targetClientId, String sourceAccount, String targetAccount, String message,
                          int amount) {
}