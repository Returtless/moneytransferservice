package com.example.moneytransferservice.controller;

import com.example.moneytransferservice.exception.InputDataError;
import com.example.moneytransferservice.exception.TransferError;
import com.example.moneytransferservice.model.Confirmation;
import com.example.moneytransferservice.model.Operation;
import com.example.moneytransferservice.model.Transfer;
import com.example.moneytransferservice.service.TransferService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@AllArgsConstructor
public class TransferController {
    private static final Logger LOG = LogManager.getLogger(TransferController.class);

    TransferService transferService;

    @PostMapping("/transfer")
    @CrossOrigin(origins = "*")
    public Operation save(@RequestBody Transfer transfer) {
        Transfer sendTransfer = transferService.saveTransfer(transfer);
        String msg = String.format("%s  Карта отправителя = %s, Карта получателя = %s, Информация о переводе = %s",
                new Timestamp(System.currentTimeMillis()), transfer.getCardFrom(),
                transfer.getCardTo(), transfer.getAmount());
        LOG.info(msg);
        return sendTransfer.getOperationId();
    }


    @PostMapping("/confirmOperation")
    @CrossOrigin(origins = "*")
    public Operation confirm(@RequestBody Confirmation confirmOperation) {
        String code = confirmOperation.getCode();
        if (code == null || code.isEmpty()) {
            throw new TransferError("Код подтверждения не заполнен.");
        }
        String msg = String.format("%s  Подтверждение операции %s с кодом %s",
                new Timestamp(System.currentTimeMillis()), confirmOperation.getOperationId(), confirmOperation.getCode());
        LOG.info(msg);
        return transferService.confirmTransfer(confirmOperation.getOperationId());
    }


    @ExceptionHandler(InputDataError.class)
    public ResponseEntity<String> errorInputDataHandler(InputDataError e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferError.class)
    public ResponseEntity<String> errorTransferHandler(TransferError e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
