package com.smallworld.service.impl;
import com.smallworld.TransactionDataFetcher;
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

@Slf4j
@RequiredArgsConstructor
public class TransactionDataFetcherImpl implements TransactionDataFetcher {

    private final TransactionService transactionService;

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        log.info("getTotalTransactionAmount method call: ");
        //Here Set is used to identify duplicate mtn Number
        Set<Integer> viewed = new HashSet<>();
        List<Transaction> transactions = transactionService.getAllTransaction();
        return transactions.isEmpty() ? 0.0 :
                transactions.stream()
                        .filter(t->{
                            if (viewed.contains(t.getMtn()))
                            {
                                return false;
                            }
                            else
                            {
                                viewed.add(t.getMtn());
                                return true;
                            }
                        })     // fetching unique transactions as single transaction can have more then one entry because of multiple issues.
                        .mapToDouble(Transaction::getAmount).sum();

    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */

    public double getTotalTransactionAmountSentBy(String senderFullName) {

        log.info("getTotalTransactionAmountSentBy method call: ");
        //Here Set is used to identify duplicate mtn Number
        Set<Integer> viewed = new HashSet<>();

        List<Transaction> transactions = transactionService.getAllTransaction();
        return transactions.isEmpty() ? 0.0 : transactions.stream()
                .filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
                .filter(t->{
                    if (viewed.contains(t.getMtn()))
                    {
                        return false;
                    }
                    else
                    {
                        viewed.add(t.getMtn());
                        return true;
                    }
                })
                .mapToDouble(Transaction::getAmount).sum();
    }

    /**
     * Returns the highest transaction amount
     */

    public double getMaxTransactionAmount() {
        log.info("getMaxTransactionAmount method call: ");
        List<Transaction> transactions = transactionService.getAllTransaction();
        return transactions.isEmpty() ? 0.0 : transactions.stream()
                .max(Comparator.comparingDouble(Transaction::getAmount))
                .get().getAmount();
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */

    public long countUniqueClients() {
        log.info("countUniqueClients method call: ");
        Set<Integer> viewed = new HashSet<>();
        List<Transaction> transactions = transactionService.getAllTransaction();
        return transactions.isEmpty() ? 0 : transactions.stream()
                .flatMap(t -> Stream.of(t.getSenderFullName(), t.getBeneficiaryFullName()))
                .distinct().count();

    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */

    public boolean hasOpenComplianceIssues(String clientFullName) {

        log.info("hasOpenComplianceIssues method call: ");
        List<Transaction> transactions = transactionService.getAllTransaction();
        return transactions.isEmpty() ? Boolean.FALSE : transactions.stream()
                .anyMatch(t -> (t.getSenderFullName().equals(clientFullName) ||
                        t.getBeneficiaryFullName().equals(clientFullName)) &&
                        t.getIssueSolved().equals(Boolean.FALSE));


    }

    /**
     * Returns all transactions indexed by beneficiary name
     */

    public Map<String, List<Transaction>> getTransactionsByBeneficiaryName() {
        log.info("getTransactionsByBeneficiaryName method call: ");
        List<Transaction> transactions = transactionService.getAllTransaction();
        return transactions.isEmpty() ? Collections.emptyMap() : transactions.stream().collect(Collectors.groupingBy(Transaction::getBeneficiaryFullName));

    }

    /**
     * Returns the identifiers of all open compliance issues
     */

    public Set<Integer> getUnsolvedIssueIds() {

        log.info("getUnsolvedIssueIds method call: ");
        List<Transaction> transactions = transactionService.getAllTransaction();
        return transactions.isEmpty() ? Collections.emptySet() :
                transactions.stream()
                        .filter(transaction -> transaction.getIssueSolved().equals(Boolean.FALSE))
                        .map(Transaction::getIssueId).collect(Collectors.toSet());

    }

    /**
     * Returns a list of all solved issue messages
     */

    public List<String> getAllSolvedIssueMessages() {
        log.info("getAllSolvedIssueMessages method call: ");
        List<Transaction> transactions = transactionService.getAllTransaction();
        return transactions.isEmpty() ? Collections.emptyList() :
                transactions.stream()
                        .filter(transaction ->
                                Objects.nonNull(transaction.getIssueMessage()) &&
                                        transaction.getIssueSolved().equals(Boolean.TRUE))
                        .map(Transaction::getIssueMessage).toList();

    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */

    public List<Transaction> getTop3TransactionsByAmount() {
        List<Transaction> transactions = transactionService.getAllTransaction();
        //Here Set is used to identify duplicate mtn Number
        Set<Integer> viewed = new HashSet<>();
        //First we will sort in descending order then we will fetch top 3 transaction
        log.info("getTop3TransactionsByAmount method call: ");
        return transactions.isEmpty() ? Collections.emptyList() :
                transactions.stream()
                        .filter(t->{
                            if (viewed.contains(t.getMtn()))
                            {return false;}
                            else
                            {viewed.add(t.getMtn());
                                return true;
                            }
                        })
                        .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                        .limit(3).collect(Collectors.toList());

    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */

    public Optional<String> getTopSender() {
        log.info("getTopSender method call: ");
        List<Transaction> transactions = transactionService.getAllTransaction();
        //Here Set is used to identify duplicate mtn Number

        Set<Integer> viewed = new HashSet<>();

        Map<String, Double> senderWithTotalAmountMap =
                transactions.stream()
                        .filter(t->{
                            if (viewed.contains(t.getMtn()))
                            {
                                return false;
                            }
                            else
                            {
                                viewed.add(t.getMtn());
                                return true;
                            }
                        })
                        .collect(Collectors.groupingBy(
                                Transaction::getSenderFullName,
                                Collectors.summingDouble(Transaction::getAmount)));

        return transactions.isEmpty() ? Optional.empty() :
                Optional.ofNullable(Collections.max(senderWithTotalAmountMap.entrySet(), Map.Entry.comparingByValue()).getKey());

    }

}
