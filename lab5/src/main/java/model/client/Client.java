package model.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.UUID;

@Getter
public class Client implements AbstractClient {

    Pair<String, String> requiredInfo;
    private final UUID clientId;
    @Setter
    private String address;
    @Setter
    private Integer customerPassport;

    @Builder
    public Client(Pair<String, String> requiredInfo, String address, Integer customerPassport) {
        this.requiredInfo = requiredInfo;
        this.address = address;
        this.customerPassport = customerPassport;
        this.clientId = new UUID(Integer.MAX_VALUE, 0);
    }
}
