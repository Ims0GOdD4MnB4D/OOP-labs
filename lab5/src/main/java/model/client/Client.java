package model.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Client implements AbstractClient {

    String name;
    String surname;
    private final UUID clientId;
    @Setter
    private String address;
    @Setter
    private Integer customerPassport;

    @Builder
    public Client(String name, String surname, String address, Integer customerPassport) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.customerPassport = customerPassport;
        this.clientId = new UUID(Integer.MAX_VALUE, 0);
    }
}
