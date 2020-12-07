package model.account;

import exception.IllegalMoneyAmountException;
import lombok.Getter;
import lombok.Setter;
import model.client.Client;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class Account implements AbstractAccount {
    private final UUID accountId;
    private final Client client;
    protected double balance;
    @Setter
    protected LocalDateTime curTime;

    public Account(Client client, int moneyAmount) {
        if(moneyAmount < 0)
            throw new IllegalMoneyAmountException();
        this.client = client;
        this.balance = moneyAmount;
        curTime = LocalDateTime.now();
        accountId = new UUID(Integer.MAX_VALUE, 0);
    }

    public void Undo(double moneyAmount) {
        balance += moneyAmount;
    }

    @Override
    public abstract double getBalance();

    @Override
    public abstract void addMoney(double moneyAmount);

    @Override
    public abstract void withdrawMoney(double moneyAmount);

    @Override
    public boolean isClientReliable() {
        return client.getCustomerPassport() != null && client.getAddress() != null;
    }
}
