package com.example.moneytransferservice.service;

import com.example.moneytransferservice.model.Operation;
import com.example.moneytransferservice.model.Transfer;
import com.example.moneytransferservice.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService {

    TransferRepository repository;

    public Transfer saveTransfer(Transfer transfer) {
        return repository.saveTransfer(transfer);
    }

    public Operation confirmTransfer(Operation confirm){
        return repository.confirmTransfer(confirm);
    }

}
