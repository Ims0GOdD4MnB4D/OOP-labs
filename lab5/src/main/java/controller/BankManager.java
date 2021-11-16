package controller;

import exception.AccountNotFoundException;
import exception.BankNotFoundException;
import lombok.Getter;
import model.bank.Bank;
import model.transaction.GlobalTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
public class BankManager implements AbstractBankManager {
    private List<Bank> banks = new ArrayList<>();
    private final List<GlobalTransaction> transactions = new ArrayList<>();

    public BankManager(List<Bank> banks) {
        this.banks.addAll(banks);
    }

    public BankManager(Bank... banks) {
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

    @Override
    public void transfer(UUID fromBank, UUID toBank, double moneyAmount) {
        if (!banks.contains(bankByAccId(fromBank))
                || !banks.contains(bankByAccId(toBank))) {
            throw new AccountNotFoundException();
        }

        banks.forEach(bank -> {
            //Verification is carried out using by UUID so no 'find' methods valid here
            if (bank.accExists(fromBank)) {
                bank.findAccById(fromBank).withdrawMoney(moneyAmount);
            }
            if (bank.accExists(toBank)) {
                bank.findAccById(toBank).addMoney(moneyAmount);
            }
        });
        transactions.add(new GlobalTransaction(bankByAccId(fromBank).findAccById(fromBank),
                bankByAccId(toBank).findAccById(toBank),
                moneyAmount,
                bankByAccId(fromBank),
                bankByAccId(toBank)));
    }

    @Override
    public void cancelTransaction(UUID transferId) {
        for (int i = 0; i < transactions.size(); ++i) {
            if (transactions.get(i).getTransferId().equals(transferId)) {
                transactions.get(i).cancelTransaction();
                transactions.remove(transactions.get(i));
            }
        }
    }
}
