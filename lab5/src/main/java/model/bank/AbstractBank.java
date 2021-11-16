package model.bank;

import java.util.UUID;

public interface AbstractBank {
    void withdrawFromAcc(double moneyAmount, UUID id);

    void addToAcc(double moneyAmount, UUID id);

    void cancelTransaction(UUID transferId);
}
