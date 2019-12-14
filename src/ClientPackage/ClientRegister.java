package ClientPackage;

import ObserverPackage.ObserverManager;
import ObserverPackage.Subject;

import java.util.ArrayList;

public class ClientRegister implements Subject {

    public static ClientRegister getInstance() {
        return instance;
    }

    private static ClientRegister instance = new ClientRegister();

    private ArrayList<Client> clientRegister = new ArrayList<>();

    public void addClient(Client client) {
        clientRegister.add(client);
        notifyObserver();
    }

    public ArrayList<Client> getClientRegister() {
        return clientRegister;
    }

    public Client getClient(int clientId) {
        for (Client client : clientRegister) {
            if (client.getClientId() == clientId) {
                return client;
            }
        }
        return null;

    }

    @Override
    public void notifyObserver() {
        //push notification
        ObserverManager.getInstance().notify(this, clientRegister);
    }

    public void deleteClient(int clientId) {
        int index = 0;
        for (Client client : clientRegister) {
            if (client.getClientId() == clientId) {
                clientRegister.remove(index);
                notifyObserver();
                return;
            }
            ++index;
        }
    }

    private ClientRegister() {

    }

}