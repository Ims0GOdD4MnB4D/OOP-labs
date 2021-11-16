package model.account;

import exception.IllegalMoneyAmountException;
import exception.OutOfCreditLimitException;
import lombok.Getter;
import lombok.Setter;
import model.client.Client;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class CreditAccount extends Account {
    private double commission;
    private int creditLimit;

    public CreditAccount(Client client, int money, int creditLimit, double commission) {
        super(client, money);
        this.creditLimit = creditLimit;
        this.commission = commission;
    }

    @Override
    public double getBalance() {
        refreshBalance();
        return balance;
    }

    @Override
    public void addMoney(double moneyAmount) {
        refreshBalance();
        if (moneyAmount <= 0) {
            throw new IllegalMoneyAmountException();
        }
        balance += moneyAmount;
    }

    @Override
    public void withdrawMoney(double moneyAmount) {
        refreshBalance();
        if (moneyAmount <= 0) {
            throw new IllegalMoneyAmountException();
        }
        if (balance - moneyAmount < creditLimit)
            throw new OutOfCreditLimitException();
        balance -= moneyAmount;
    }

    private boolean isCommissionActive() {
        return balance < 0;
    }

    private void refreshBalance() {
        long monthsCommission = ChronoUnit.MONTHS.between(LocalDateTime.now(), curTime);
        if (monthsCommission == 0 || !isCommissionActive())
            return;
        for (int i = 0; i < monthsCommission; ++i) {
            balance -= commission;
        }
        curTime = LocalDateTime.now();
    }
}
