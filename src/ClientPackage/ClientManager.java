package ClientPackage;

import ReservationPackage.Reservation;

import java.util.ArrayList;

public class ClientManager {
    private static ClientManager instance = new ClientManager();

    public static ClientManager getInstance() {
        return instance;
    }
    private ClientManager(){};


    public Client createClient(String name, String address, String email, String phoneNumber, String dateOfBirth, int passPort, String expiration){
        Client client = new Client();
        client.setName(name);
        client .setaddress(address);
        client.setEmail(email);
        client.setPhoneNumber(phoneNumber);
        client .setDateOfBirth(dateOfBirth);
        client.setPassportNumber(passPort);
        client.setPassportExpirationDate(expiration);
        client.setReservationList(new ArrayList<>());
        client.setClientId(ClientRegister.getInstance().getClientRegister().size()+1);
        ClientRegister.getInstance().addClient(client);
        return client;
    }
}
