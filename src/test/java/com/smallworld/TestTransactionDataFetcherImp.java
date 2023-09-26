package com.smallworld;

import com.smallworld.data.Transaction;
import com.smallworld.service.TransactionService;
import com.smallworld.service.impl.TransactionDataFetcherImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
@ExtendWith(MockitoExtension.class)
class TestTransactionDataFetcherImp {
    @Mock
    TransactionService transactionService;
    @InjectMocks
    private TransactionDataFetcherImpl transactionDataFetcher;

    private List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(
                Transaction.builder()
                        .mtn(663458)
                        .amount(430.2)
                        .senderFullName("Tom Shelby")
                        .senderAge(22)
                        .beneficiaryFullName("Alfie Solomons")
                        .beneficiaryAge(33)
                        .issueId(1)
                        .issueSolved(false)
                        .issueMessage("Looks like money laundering").build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(1284564)
                        .amount(150.2)
                        .senderFullName("Tom Shelby")
                        .senderAge(22)
                        .beneficiaryFullName("Arthur Shelby")
                        .beneficiaryAge(60)
                        .issueId(2)
                        .issueSolved(true)
                        .issueMessage("Never gonna give you up").build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(1284564)
                        .amount(150.2)
                        .senderFullName("Tom Shelby")
                        .senderAge(22)
                        .beneficiaryFullName("Arthur Shelby")
                        .beneficiaryAge(60)
                        .issueId(3)
                        .issueSolved(false)
                        .issueMessage("Looks like money laundering").build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(96132456)
                        .amount(67.8)
                        .senderFullName("Aunt Polly")
                        .senderAge(34)
                        .beneficiaryFullName("Aberama Gold")
                        .beneficiaryAge(58)
                        .issueId(null)
                        .issueSolved(true)
                        .issueMessage(null).build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(5465465)
                        .amount(985.0)
                        .senderFullName("Arthur Shelby")
                        .senderAge(60)
                        .beneficiaryFullName("Ben Younger")
                        .beneficiaryAge(47)
                        .issueId(15)
                        .issueSolved(false)
                        .issueMessage("Something's fishy").build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(1651665)
                        .amount(97.66)
                        .senderFullName("Tom Shelby")
                        .senderAge(22)
                        .beneficiaryFullName("Oswald Mosley")
                        .beneficiaryAge(37)
                        .issueId(65)
                        .issueSolved(true)
                        .issueMessage("Never gonna let you down").build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(6516461)
                        .amount(33.22)
                        .senderFullName("Aunt Polly")
                        .senderAge(34)
                        .beneficiaryFullName("MacTavern")
                        .beneficiaryAge(30)
                        .issueId(null)
                        .issueSolved(true)
                        .issueMessage(null).build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(32612651)
                        .amount(666.0)
                        .senderFullName("Grace Burgess")
                        .senderAge(31)
                        .beneficiaryFullName("Michael Gray")
                        .beneficiaryAge(58)
                        .issueId(54)
                        .issueSolved(false)
                        .issueMessage("Something ain't right").build()
        );

        transactions.add(
                Transaction.builder()
                        .mtn(32612651)
                        .amount(666.0)
                        .senderFullName("Grace Burgess")
                        .senderAge(31)
                        .beneficiaryFullName("Michael Gray")
                        .beneficiaryAge(58)
                        .issueId(78)
                        .issueSolved(true)
                        .issueMessage("Never gonna run around and desert you").build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(32612651)
                        .amount(666.0)
                        .senderFullName("Grace Burgess")
                        .senderAge(31)
                        .beneficiaryFullName("Michael Gray")
                        .beneficiaryAge(58)
                        .issueId(99)
                        .issueSolved(false)
                        .issueMessage("Don't let this transaction happen").build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(36448252)
                        .amount(154.15)
                        .senderFullName("Billy Kimber")
                        .senderAge(58)
                        .beneficiaryFullName("Winston Churchill")
                        .beneficiaryAge(48)
                        .issueId(null)
                        .issueSolved(true)
                        .issueMessage(null).build()
        );
        transactions.add(
                Transaction.builder()
                        .mtn(645645111)
                        .amount(215.17)
                        .senderFullName("Billy Kimber")
                        .senderAge(58)
                        .beneficiaryFullName("Major Campbell")
                        .beneficiaryAge(41)
                        .issueId(null)
                        .issueSolved(true)
                        .issueMessage(null).build()
        );

        transactions.add(
                Transaction.builder()
                        .mtn(45431585)
                        .amount(89.77)
                        .senderFullName("Billy Kimber")
                        .senderAge(58)
                        .beneficiaryFullName("Luca Changretta")
                        .beneficiaryAge(46)
                        .issueId(null)
                        .issueSolved(true)
                        .issueMessage(null).build()
        );
        return transactions;
    }
    private List<Transaction> getEmptyTransactions() {
        return Collections.emptyList();
    }

    /**
     * Tests that the getTotalTransactionAmount method calculates the correct total transaction amount when transactions exist.
     */
    @Test
    void getTestTotalTransactionAmount_TransactionNotEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Assertions.assertEquals(2889.17, transactionDataFetcher.getTotalTransactionAmount());
    }


    /**
     * Tests the calculation of total transaction amount when transactions are empty.
     */
    @Test
    void getTestTotalTransactionAmount_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertEquals(0.0, transactionDataFetcher.getTotalTransactionAmount());
    }



    /**
     * Tests the calculation of total transaction amount sent by "Tom Shelby" when transactions are not empty.
     */
    @Test
    void getTestTotalTransactionAmountSentBy_TransactionNotEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Assertions.assertEquals(678.06, transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"));
    }



    /**
     * Tests the calculation of total transaction amount sent by "Alfie Solomons" when transactions are not empty, and no transactions are sent by this sender.
     */
    @Test
    void getTestTotalTransactionAmountSentBy_TransactionNotEmptyAgainstSender() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        double totalTransactionAmountSentBy = transactionDataFetcher.getTotalTransactionAmountSentBy("Alfie Solomons");
        Assertions.assertEquals(0.0, totalTransactionAmountSentBy);
    }


    /**
     * Tests the calculation of total transaction amount sent by "Tom Shelby" when transactions are empty.
     */
    @Test
    void getTestTotalTransactionAmountSentBy_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        double totalTransactionAmountSentBy = transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby");
        Assertions.assertEquals(0.0, totalTransactionAmountSentBy);
    }



    /**
     * Tests the retrieval of the maximum transaction amount when transactions are not empty.
     */
    @Test
    void getTestMaxTransactionAmount_TransactionNotEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Assertions.assertEquals(985.0, transactionDataFetcher.getMaxTransactionAmount());
    }



    /**
     * Tests the retrieval of the maximum transaction amount when transactions are empty.
     */
    @Test
    void getTestMaxTransactionAmount_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertEquals(0.0, transactionDataFetcher.getMaxTransactionAmount());
    }


    /**
     * Tests the counting of unique clients when transactions are not empty.
     */
    @Test
    void getTestCountUniqueClients_TransactionNotEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Assertions.assertEquals(14, transactionDataFetcher.countUniqueClients());
    }

    /**
     * Tests the counting of unique clients when transactions are empty.
     */
    @Test
    void getTestCountUniqueClients_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertEquals(0, transactionDataFetcher.countUniqueClients());
    }


    /**
     * Tests whether "Tom Shelby" has open compliance issues when there are unresolved issues in transactions.
     */
    @Test
    void getTestHasOpenComplianceIssues_ClientHasUnresolvedIssues() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Assertions.assertTrue(transactionDataFetcher.hasOpenComplianceIssues("Tom Shelby"));
    }



    /**
     * Tests whether "Ben Younger" has open compliance issues when there are no unresolved issues in transactions.
     */
    @Test
    void getTestHasOpenComplianceIssues_NoUnresolvedIssues() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Assertions.assertTrue(transactionDataFetcher.hasOpenComplianceIssues("Ben Younger"));
    }

    /**
     * Tests whether "Tom Shelby" has open compliance issues when there are no transactions available.
     */
    @Test
    void getTestHasOpenComplianceIssues_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertFalse(transactionDataFetcher.hasOpenComplianceIssues("Tom Shelby"));
    }



    /**
     * Tests the retrieval of transactions by beneficiary name when transactions are not empty.
     */
    @Test
    void getTestTransactionsByBeneficiaryName_TransactionNotEmpty() {
        List<Transaction> transactions = getTransactions();
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Map<String, List<Transaction>> actualResult = transactionDataFetcher.getTransactionsByBeneficiaryName();
        Map<String, List<Transaction>> expectedResult = new HashMap<>();
        expectedResult.put("Alfie Solomons", new ArrayList<>(List.of(transactions.get(0))));
        expectedResult.put("Arthur Shelby", new ArrayList<>(List.of(transactions.get(1),transactions.get(2))));
        expectedResult.put("Aberama Gold", new ArrayList<>(List.of(transactions.get(3))));
        expectedResult.put("Ben Younger", new ArrayList<>(List.of(transactions.get(4))));
        expectedResult.put("Oswald Mosley", new ArrayList<>(List.of(transactions.get(5))));
        expectedResult.put("MacTavern", new ArrayList<>(List.of(transactions.get(6))));
        expectedResult.put("Michael Gray", new ArrayList<>(List.of(transactions.get(7),transactions.get(8),transactions.get(9))));
        expectedResult.put("Winston Churchill", new ArrayList<>(List.of(transactions.get(10))));
        expectedResult.put("Major Campbell", new ArrayList<>(List.of(transactions.get(11))));
        expectedResult.put("Luca Changretta", new ArrayList<>(List.of(transactions.get(12))));

        for (Map.Entry<String, List<Transaction>> entry : actualResult.entrySet()) {
            ReflectionAssert.assertReflectionEquals(entry.getValue(), expectedResult.get(entry.getKey()));
        }
    }

    /**
     * Tests the retrieval of transactions by beneficiary name when transactions are empty.
     */
    @Test
    void getTestTransactionsByBeneficiaryName_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertEquals(Collections.emptyMap(), transactionDataFetcher.getTransactionsByBeneficiaryName());
    }

    /**
     * Tests the retrieval of unsolved issue IDs when transactions are not empty.
     */
    @Test
    void getTestUnsolvedIssueIds_TransactionNotEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Set<Integer> actualResult = transactionDataFetcher.getUnsolvedIssueIds();
        Set<Integer> expectedResult = new HashSet<>(List.of(1,3,99,54,15));
        Assertions.assertEquals(expectedResult, actualResult);
    }


    /**
     * Tests the retrieval of unsolved issue IDs when transactions are empty.
     */
    @Test
    void getTestUnsolvedIssueIds_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertEquals(Collections.emptySet(), transactionDataFetcher.getUnsolvedIssueIds());
    }



    /**
     * Tests the retrieval of all solved issue messages when transactions are not empty.
     */
    @Test
    void getTestAllSolvedIssueMessages_TransactionNotEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        List<String> actualResult = transactionDataFetcher.getAllSolvedIssueMessages();
        List<String> expectedResult = List.of("Never gonna give you up", "Never gonna let you down","Never gonna run around and desert you");
        Assertions.assertEquals(expectedResult, actualResult);
    }



    /**
     * Tests the retrieval of all solved issue messages when transactions are empty.
     */

    @Test
    void getTestAllSolvedIssueMessages_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertEquals(Collections.emptyList(), transactionDataFetcher.getAllSolvedIssueMessages());
    }



     // Unit test to test getTop3TransactionsByAmount when transaction list exits.
    /**
     * Tests the retrieval of the top 3 transactions by amount when transactions are not empty.
     */
    @Test
    void getTestTop3TransactionsByAmount_TransactionNotEmpty() {
        List<Transaction> transactions = getTransactions();
        Mockito.when(transactionService.getAllTransaction()).thenReturn(transactions);
        List<Transaction> actualResult = transactionDataFetcher.getTop3TransactionsByAmount();
        List<Transaction> expectedResult = new ArrayList<>(List.of(transactions.get(4), transactions.get(7), transactions.get(0)));
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests the retrieval of the top 3 transactions by amount when transactions are  empty.
     */
    @Test
    void getTestTop3TransactionsByAmount_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertEquals(Collections.emptyList(), transactionDataFetcher.getTop3TransactionsByAmount());
    }



    /**
     * Tests the retrieval of the top sender when transactions are not empty.
     */
    @Test
    void getTestTopSender_TransactionNotEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getTransactions());
        Assertions.assertEquals(Optional.of("Arthur Shelby"), transactionDataFetcher.getTopSender());
    }


    /**
     * Tests the retrieval of the top sender when transactions are empty.
     */

    @Test
    void getTestGetTopSender_TransactionEmpty() {
        Mockito.when(transactionService.getAllTransaction()).thenReturn(getEmptyTransactions());
        Assertions.assertEquals(Optional.empty(), transactionDataFetcher.getTopSender());
    }

}