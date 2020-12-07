package controller;

import exception.BankNotFoundException;
import lombok.Getter;
import model.account.Account;
import model.bank.Bank;
import model.transaction.GlobalTransaction;
import model.transaction.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Getter
public class BankManager implements AbstractBankManager {
    private List<Bank> banks = new ArrayList<>();
    private final List<GlobalTransaction> transactions = new ArrayList<>();

    public BankManager() {

    }
    public BankManager(List <Bank> banks) {
        this.banks.addAll(banks);
    }

    public BankManager(Bank ... banks) {
        this.banks = Arrays.asList(banks);
    }

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    public Bank bankByAccId(UUID id) {
        for (Bank bank : banks)
            if (bank.accExists(id))
                return bank;
        throw new BankNotFoundException();
    }

    public void transfer(UUID fromBank, UUID toBank, double moneyAmount) {
        banks.forEach(bank -> {
            if(bank.accExists(fromBank)) {
                bank.findAccById(fromBank).withdrawMoney(moneyAmount);
            }
            if(bank.accExists(toBank)) {
                bank.findAccById(toBank).addMoney(moneyAmount);
            }

        });
        transactions.add(new GlobalTransaction(bankByAccId(fromBank).findAccById(fromBank),
                bankByAccId(toBank).findAccById(toBank),
                moneyAmount,
                bankByAccId(fromBank),
                bankByAccId(toBank)));
    }

    public void cancelTransaction(UUID transferId) {
        transactions.forEach(
                transfer -> {
                    if(transfer.getTransferId().equals(transferId)) {
                        transfer.cancelTransaction();
                    }
                }
        );
    }
}
