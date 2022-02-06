package com.example.moneytransferservice.repository;

import com.example.moneytransferservice.model.Operation;
import com.example.moneytransferservice.model.Transfer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class TransferRepository {
    private final List<Transfer> transferRepository = new ArrayList<>();

    public List<Transfer> getTransferRepository() {
        return transferRepository;
    }

    public Transfer saveTransfer(Transfer transfer){
        transferRepository.add(transfer);
        return transfer;
    }

    public Operation confirmTransfer(Operation operation){
        return operation;
    }
}
