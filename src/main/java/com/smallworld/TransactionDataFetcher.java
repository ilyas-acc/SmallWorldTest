package com.smallworld;

import com.smallworld.data.Transaction;
import com.smallworld.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public interface  TransactionDataFetcher {

    double getTotalTransactionAmount();

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    double getTotalTransactionAmountSentBy(String senderFullName);

    /**
     * Returns the highest transaction amount
     */
    double getMaxTransactionAmount();

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    long countUniqueClients();

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    boolean hasOpenComplianceIssues(String clientFullName);

    /**
     * Returns all transactions indexed by beneficiary name
     */
    Map<String, List<Transaction>> getTransactionsByBeneficiaryName();

    /**
     * Returns the identifiers of all open compliance issues
     */
    Set<Integer> getUnsolvedIssueIds();

    /**
     * Returns a list of all solved issue messages
     */
    List<String> getAllSolvedIssueMessages();

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    List<Transaction> getTop3TransactionsByAmount();

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    Optional<String> getTopSender();
}
