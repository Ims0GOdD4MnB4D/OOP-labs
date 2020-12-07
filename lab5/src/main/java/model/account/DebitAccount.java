package model.account;

import exception.TryingGetMoreMoneyThanActualException;
import exception.IllegalMoneyAmountException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.client.Client;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

@Getter
public class DebitAccount extends Account {

    @Setter
    private double interestRate;
    private int remainder = 0;

    @Builder
    public DebitAccount(Client client, int money, double percent) {
        super(client, money);
        interestRate = percent;
    }

    @Override
    public double getBalance() {
        refreshBalance();
        return balance;
    }

    @Override
    public void withdrawMoney(double moneyAmount) {
        refreshBalance();
        if(moneyAmount <= 0) {
            throw new IllegalMoneyAmountException();
        }
        if(balance < moneyAmount)
            throw new TryingGetMoreMoneyThanActualException();
        balance -= moneyAmount;
    }

    @Override
    public void addMoney(double moneyAmount) {
        refreshBalance();
        if(moneyAmount <= 0) {
            throw new IllegalMoneyAmountException();
        }
        balance += moneyAmount;
    }

    private void refreshBalance() {
        long daysRemainder = ChronoUnit.DAYS.between(LocalDateTime.now(), curTime);
        if(daysRemainder == 0)
            return;
        for(int i=0; i<daysRemainder; ++i) {
            remainder += (remainder + balance) * interestRate/100/365;
        }
        curTime = LocalDateTime.now();
        if(ChronoUnit.MONTHS.between(LocalDateTime.now(), curTime) != 0) {
            balance = balance + remainder;
            remainder = 0;
        }
    }
}
