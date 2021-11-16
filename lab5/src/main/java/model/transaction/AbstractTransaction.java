package model.transaction;

public interface AbstractTransaction {
    void cancelTopUpTransaction();

    void cancelWithdrawingTransaction();
}
