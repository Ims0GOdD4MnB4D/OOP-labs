import controller.BankManager;
import model.account.DebitAccount;
import model.account.DepositAccount;
import model.bank.Bank;
import model.client.Client;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class BankTest {
        @Test
        public void test1() {
            Client rocky = Client.builder().
                    requiredInfo(new MutablePair<>("Rakim", "Mayers"))
                    .address("NY, Midtown Manhattan")
                    .customerPassport(1234567)
                    .build();
            Client travis = Client.builder()
                    .requiredInfo(new MutablePair<>("Jacques", "Webster ||"))
                    .address("Beverly Hills, CA")
                    .customerPassport(14881337)
                    .build();
            DebitAccount rockyAccount = new DebitAccount(rocky, 100000, 1.5);
            DebitAccount travisAccount = new DebitAccount(travis, 100000, 2.0);
            Bank mobBank = Bank.builder()
                    .commission(100)
                    .creditLimit(1000)
                    .interestRate(1.5)
                    .operationsLimit(1000)
                    .build();
            Bank cactusBank = Bank.builder()
                    .commission(150)
                    .creditLimit(10000)
                    .interestRate(2.0)
                    .operationsLimit(5000)
                    .build();
            mobBank.addAccount(rockyAccount);
            cactusBank.addAccount(travisAccount);
            BankManager cartiManager = new BankManager(mobBank, cactusBank);
            cartiManager.transfer(rockyAccount.getAccountId(), travisAccount.getAccountId(), 5000);
            cartiManager.cancelTransaction(cartiManager.getTransactions()
                        .get(cartiManager.getTransactions().size() - 1).getTransferId());
            Pair<Double, Double> expected = new MutablePair<>(100000.0, 100000.0);
            Pair<Double, Double> actual = new MutablePair<>(rockyAccount.getBalance(), travisAccount.getBalance());
            Assert.assertEquals(expected, actual);
        }

        @Test
        public void test2() {
            Client rocky = Client.builder().
                    requiredInfo(new MutablePair<>("Rakim", "Mayers"))
                    .address("NY, Midtown Manhattan")
                    .customerPassport(1234567)
                    .build();
            DepositAccount rockyAccount = new DepositAccount(rocky, 100000, LocalDateTime.now());
            Bank mobBank = Bank.builder()
                    .commission(100)
                    .creditLimit(1000)
                    .interestRate(1.5)
                    .operationsLimit(1000)
                    .build();
            mobBank.addAccount(rockyAccount);
            double expected = 0;
            for(int i=0; i<31; ++i)
                expected += (expected + rockyAccount.getBalance())
                        * rockyAccount.getInterestRate()/100/365;
            expected += rockyAccount.getBalance();
            rockyAccount.setCurTime(rockyAccount.getTerm().plusMonths(1).plusDays(1));
            Assert.assertEquals(expected, rockyAccount.getBalance(), 0);
        }
    }
