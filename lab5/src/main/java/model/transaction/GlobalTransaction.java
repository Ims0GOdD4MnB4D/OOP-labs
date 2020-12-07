package model.transaction;

import lombok.Getter;
import model.account.Account;
import model.bank.Bank;

@Getter
public class GlobalTransaction extends Transaction{
    private final Bank fromBank;
    private final Bank toBank;

    public GlobalTransaction(Account fromAcc, Account toAcc, double moneyAmount, Bank fromBank, Bank toBank) {
        super(fromAcc, toAcc, moneyAmount);
        this.fromBank = fromBank;
        this.toBank = toBank;
    }

    @Override
    public void cancelTransaction() {
        fromBank.findAccById(fromAcc.getAccountId()).Undo(moneyAmount);
        toBank.findAccById(toAcc.getAccountId()).Undo(-moneyAmount);
    }

}
