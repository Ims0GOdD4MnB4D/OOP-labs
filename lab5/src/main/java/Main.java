import model.account.DebitAccount;
import model.client.Client;
import org.apache.commons.lang3.tuple.MutablePair;

public class Main {
    public static void main(String[] args) {
        Client client = Client.builder().
                requiredInfo(new MutablePair<>("Rakim", "Mayers"))
                .build();
        DebitAccount debitAccount = new DebitAccount(client, 1000000, 1.5);
        System.out.println(debitAccount.isClientReliable());
        client.setAddress("NY, Midtown Manhattan");
        System.out.println(debitAccount.isClientReliable());
        client.setCustomerPassport(1234567);
        System.out.println(debitAccount.isClientReliable());
    }
}
