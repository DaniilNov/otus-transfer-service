package ru.otus.java.pro.transferservice.dtos;

public record ExecuteTransferDtoRq(String targetClientId, String sourceAccount, String targetAccount, String message, int amount) {
}