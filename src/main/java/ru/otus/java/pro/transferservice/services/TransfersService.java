package ru.otus.java.pro.transferservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.transferservice.dtos.ExecuteTransferDtoRq;
import ru.otus.java.pro.transferservice.entities.Transfer;
import ru.otus.java.pro.transferservice.exceptions_handling.BusinessLogicException;
import ru.otus.java.pro.transferservice.exceptions_handling.ValidationException;
import ru.otus.java.pro.transferservice.exceptions_handling.ValidationFieldError;
import ru.otus.java.pro.transferservice.repositories.TransfersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransfersService {
    private final TransfersRepository transfersRepository;

    public Optional<Transfer> getTransferById(String id, String clientId) {
        return transfersRepository.findByIdAndClientId(id, clientId);
    }

    public List<Transfer> getAllTransfers(String clientId) {
        return transfersRepository.findAllByClientIdOrTargetClientId(clientId, clientId);
    }

    public void execute(String clientId, ExecuteTransferDtoRq executeTransferDtoRq) {
        validateExecuteTransferDtoRq(executeTransferDtoRq);

        if (executeTransferDtoRq.amount() > 10000) {
            throw new BusinessLogicException("Сумма перевода превышает допустимый лимит", "TRANSFER_LIMIT_EXCEEDED");
        }

        Transfer transfer = new Transfer();
        transfer.setId(UUID.randomUUID().toString());
        transfer.setClientId(clientId);
        transfer.setTargetClientId(executeTransferDtoRq.targetClientId());
        transfer.setSourceAccount(executeTransferDtoRq.sourceAccount());
        transfer.setTargetAccount(executeTransferDtoRq.targetAccount());
        transfer.setAmount(executeTransferDtoRq.amount());
        transfer.setMessage(executeTransferDtoRq.message());

        log.info("Saving transfer: {}", transfer);
        transfersRepository.save(transfer);
    }

    private void validateExecuteTransferDtoRq(ExecuteTransferDtoRq executeTransferDtoRq) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (executeTransferDtoRq.sourceAccount().length() != 12) {
            errors.add(new ValidationFieldError("sourceAccount", "Длина поля счет отправителя должна составлять 12 символов"));
        }
        if (executeTransferDtoRq.targetAccount().length() != 12) {
            errors.add(new ValidationFieldError("targetAccount", "Длина поля счет получателя должна составлять 12 символов"));
        }
        if (executeTransferDtoRq.amount() <= 0) {
            errors.add(new ValidationFieldError("amount", "Сумма перевода должна быть больше 0"));
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("EXECUTE_TRANSFER_VALIDATION_ERROR", "Проблемы заполнения полей перевода", errors);
        }
    }
}
