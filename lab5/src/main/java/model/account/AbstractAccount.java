package model.account;


public interface AbstractAccount {
    boolean isClientReliable();

    double getBalance();

    void addMoney(double moneyAmount);

    void withdrawMoney(double moneyAmount);
}
