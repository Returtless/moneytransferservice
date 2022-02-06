package com.example.moneytransferservice.controller;

import com.example.moneytransferservice.exception.InputDataError;
import com.example.moneytransferservice.exception.TransferError;
import com.example.moneytransferservice.model.Confirmation;
import com.example.moneytransferservice.model.Operation;
import com.example.moneytransferservice.model.Transfer;
import com.example.moneytransferservice.service.TransferService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransferController {
    private static final Logger LOG = LoggerFactory.getLogger("transferController");

    TransferService transferService;

    @PostMapping("/transfer")
    public Operation save(@RequestBody Transfer transfer) {
        Transfer sendTransfer = transferService.saveTransfer(transfer);
        String msg = String.format("CardFrom = %s, CardTo = %s, Amount = %s", transfer.getCardFrom(),
                transfer.getCardTo(), transfer.getAmount());
        LOG.info(msg);
        return sendTransfer.getOperationId();
    }


    @PostMapping("/confirmOperation")
    public Operation confirm(@RequestBody Confirmation confirmOperation) {
        String code = confirmOperation.getCode();
        if (code == null || code.isEmpty()) {
            throw new TransferError("Verification code is empty.");
        }
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
