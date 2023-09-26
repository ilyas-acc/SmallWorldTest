package com.smallworld.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;
import com.smallworld.service.TransactionService;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private static List<Transaction> transactions;
    private static final String jsonFile="src/main/resource/transactions.json";


    @PostConstruct
    void initTransaction() {
        log.info("getAllTransactions method call: Getting all Transactions.");
        getAllTransactions();
    }


    @Override
    public List<Transaction> getAllTransaction() {
        return transactions;
    }


    @SneakyThrows
    private void getAllTransactions() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File initialFile = new File(jsonFile);
            transactions = mapper.readValue(initialFile, new TypeReference<List<Transaction>>() {
            });
        } catch (Exception e) {

            log.info("Exception in Loading Transaction."+e);
            throw e;
        }
    }
}