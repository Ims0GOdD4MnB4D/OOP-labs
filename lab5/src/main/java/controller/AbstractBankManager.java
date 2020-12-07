package controller;

import java.util.UUID;

public interface AbstractBankManager {
    void transfer(UUID fromBank, UUID toBank, double moneyAmount);
    void cancelTransaction(UUID transferId);
}
