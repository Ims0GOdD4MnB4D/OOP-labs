package model.account;

import exception.IllegalMoneyAmountException;
import exception.TryingGetMoreMoneyThanActualException;
import lombok.Getter;
import lombok.Setter;
import model.client.Client;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class DepositAccount extends Account {
    @Setter
    private double interestRate;
    private double remainder = 0;
    @Setter
    private LocalDateTime term;

    public DepositAccount(Client client, int money, LocalDateTime term) {
        super(client, money);
        this.term = term;
        refreshInterest();
    }

    @Override
    public double getBalance() {
        refreshBalance();
        return balance;
    }

    @Override
    public void addMoney(double moneyAmount) {
        if(moneyAmount <= 0) {
            throw new IllegalMoneyAmountException();
        }
        refreshBalance();
        balance += moneyAmount;
    }

    @Override
    public void withdrawMoney(double moneyAmount) {
        refreshBalance();
        if(!isWithdrawAvailable())
            return;
        if(moneyAmount <= 0) {
            throw new IllegalMoneyAmountException();
        }
        if(balance < moneyAmount)
            throw new TryingGetMoreMoneyThanActualException();
        balance -= moneyAmount;
    }

    private void refreshBalance() {
        long daysRemainder = ChronoUnit.DAYS.between(LocalDateTime.now(), curTime);
        refreshInterest();
        if(daysRemainder == 0)
            return;
        for(int i=0; i<daysRemainder; ++i) {
            remainder += (remainder + balance) * interestRate/100/365;
        }
         if(ChronoUnit.MONTHS.between(LocalDateTime.now(), curTime) != 0) {
            balance = balance + remainder;
            remainder = 0;
        }
        curTime = LocalDateTime.now();
    }

    private void refreshInterest() {
        if(balance < 50000)
            interestRate = 3.0;
        else if(balance <= 100000)
            interestRate = 3.5;
        else
            interestRate = 4.0;
    }

    private boolean isWithdrawAvailable() {
        return curTime.isAfter(term);
    }
}
