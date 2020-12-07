package model.bank;

import exception.AccountNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.account.Account;
import model.account.CreditAccount;
import model.account.DebitAccount;
import model.account.DepositAccount;
import model.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Bank implements AbstractBank {
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    @Setter
    private double interestRate;
    @Setter
    private double commission;
    @Setter
    private int creditLimit;
    @Setter
    private double operationsLimit;

    @Builder
    public Bank(List<Account> accountList,
                List<Transaction> transactionList,
                double interestRate, double commission, int creditLimit, double operationsLimit) {
//        if(accountList != null)
//            for (Account account : accountList) defineAccountValues(account);
        this.interestRate = interestRate;
        this.commission = commission;
        this.creditLimit = creditLimit;
        this.accounts = accountList;
        this.transactions = transactionList;
        this.operationsLimit = operationsLimit;
    }

    public void addAccount(Account acc) {
        if(accounts == null)
            accounts = new ArrayList<>();
        //defineAccountValues(acc);
        accounts.add(acc);
    }

    public void addTransaction(Transaction transaction) {
        if(this.transactions == null)
            transactions = new ArrayList<>();
        transactions.add(transaction);
    }


    @Override
    public void withdrawFromAcc(double moneyAmount, UUID id) {
        accounts.forEach(
                acc -> {
                    if(acc.getAccountId().equals(id)) {
                        if(acc.isClientReliable() && moneyAmount > operationsLimit)
                            return;
                        acc.withdrawMoney(moneyAmount);
                        transactions.add(Transaction.builder().fromAcc(acc).build());
                    }
                }
        );
    }

    @Override
    public void addToAcc(double moneyAmount, UUID id) {
        accounts.forEach(
                acc -> {
                    if(acc.getAccountId().equals(id)) {
                        if(acc.isClientReliable() && moneyAmount > operationsLimit)
                            return;
                        acc.addMoney(moneyAmount);
                        transactions.add(Transaction.builder().toAcc(acc).build());
                    }
                }
        );
    }

    @Override
    public void cancelTransaction(UUID transferId) {
        transactions.forEach(
                transfer -> {
                    if(transfer.getTransferId().equals(transferId)) {
                        cancelTransaction(transferId);
                    }
                }
        );
    }

    public Account findAccById(UUID id) {
        //TODO: switch to lambda
        for(Account item : accounts)
            if(item.getAccountId().equals(id))
                return item;
        throw new AccountNotFoundException();
    }

    public boolean accExists(UUID id) {
        for(Account item : accounts)
            if(item.getAccountId().equals(id))
                return true;
        return false;
    }
}
