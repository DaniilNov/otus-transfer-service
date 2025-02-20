package ru.otus.java.pro.transferservice.dtos;

import java.util.List;

public record TransfersPageDto(List<TransferDto> entries) {
}