package UI;

import AppLauncher.Launcher;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public class ClientCommandLineInterface extends CLI {

    ClientController controller = new ClientController();
    ;


    @Override
    protected void initAvailableCommands() {
        _commands.put("display available seats", this::displayAvailReservables);
        _commands.put("reserve seat", this::reserveSeat);
        _commands.put("pay seat", this::paySeat);
        _commands.put("cancel reservation", this::cancelReservation);
        _commands.put("exit", this::exit);
        _commands.put("help",this::displayHelp);
        _commands.put("change reservation",this::changeReservation);
    }

    /**
     * begins interaction loop that ends when exit is entered in the terminal
     */
    public void run() {
        initAvailableCommands();

        displayHelp();
        String demand = "please enter a command or enter help for a list of possible commands:";
        String response;
        while (isRunning){
            response = displayAndResponse(demand);

            response = response.trim().toLowerCase();
            if (_commands.get(response) != null) {
                _commands.get(response).run();

            } else {
                display("Unknown Command, please use the help command!");
            }
        }

    }

    /**
     * stops the interaction loop
     */
    private void exit() {
        isRunning = false;
        Launcher.isRunning = false;
    }

    /**
     * displays informations on all availables seats
     */
    private void displayAvailReservables() {
        String originId = displayAndResponse("enter the id of the origin building");
        String arrivalId = displayAndResponse("enter the id of the arrival building");

        String date = displayAndResponse("enter the date of departure");

        char classe = getClasseChar();

        ArrayList<String> response = controller.displayAvailReservables(originId, arrivalId, date, classe);

        for (String reservable : response) {
            display(reservable);
        }
    }


    /**
     * @param input input to formate
     * @return the input String without spaces and the beginning char capitalized
     */
    private String prepareStringForDisplayAv(String input) {
        //https://stackoverflow.com/questions/15633228/how-to-remove-all-white-spaces-in-java/36444332
        //remove all spaces in the String and uppercase the beginning char
        input = input.replaceAll("\\s+", "");
        input = Character.toUpperCase(input.charAt(0)) + input.substring(1);
        return input;
    }

    /**
     * function to reserve a seat. Asks the travel id, the classe wanted and the client identifier
     */
    private void reserveSeat() {
        String travelId = displayAndResponse("enter the id of the travel");
        boolean preferWindow = displayAndResponse("do you prefer a window seat? yes or no").trim().compareToIgnoreCase("yes") == 0;
        try {
            char classe = getClasseChar();
//        int clientId = parseIntFromResponse("enter your client identifier");
//        controller.setCurrClient(clientId);
            int reservationNumber = controller.bookReservable(preferWindow, travelId, classe);


            if(reservationNumber>=0){display("your reservation number is " + reservationNumber);}
            else if (reservationNumber==-2){display("there is no travel with this identifier");}
            else if (reservationNumber==-1){
                display("there are no available seat matching your criterias");
            }
        }catch (NoClassWithThisName e){
            display(e.getMessage());
        }

    }

    /**
     * function to pay for a seat. Asks the reservation number, the client identifier, the name, adress,email,birth date, passport number,
     * passport expiration date of the client, the credit card number and it's security code.
     */
    private void paySeat() {
        int reservationId = parseIntFromResponse("enter the reservation number");

        String name = displayAndResponse("enter your name");
        String adress = displayAndResponse("enter your adress");
        String email = displayAndResponse("enter your email");
        String phoneNumber = displayAndResponse("enter your phone number");
        String birthDate = displayAndResponse("enter your birth date");

        int passportNum = parseIntFromResponse("enter the number of your passport");

        String passportExpDate = displayAndResponse("enter the expiration date of your passport");

        int creditCardNumber = parseIntFromResponse("enter your credit card number");
        String creditCardSecCode = displayAndResponse("enter the security code of the credit card");

        if (controller.paySeatCreditCard(name, adress, email, phoneNumber, birthDate, passportNum, passportExpDate, creditCardNumber, creditCardSecCode, reservationId)) {
            display("payment confirmed");
        } else {
            display("there was a problem with the payment");
        }
    }

    /**
     * cancels the reservation: asks the reservation number and the client identifier
     */
    private void cancelReservation() {
        int reservationNumber = parseIntFromResponse("please enter the reservation number");

        if(controller.cancelBookedReservable(reservationNumber)){
            display("reservation number " + reservationNumber+" is canceled");
        }
        else{
            display("reservation number " + reservationNumber+" could not be canceled");
        }
    }

    /**
     * changes the reservation : asks the reservation number, the id of the new travel, the preference for a window seat, credit card number,
     * credit card security code, the classe wanted and the client identifier
     */
    private void changeReservation() {
        int reservationNumber = parseIntFromResponse("please enter the reservation number");

        String travelId = displayAndResponse("enter the id of the  new travel");
        boolean preferWindow = displayAndResponse("do you prefer a window seat? yes or no").trim().compareToIgnoreCase("yes") == 0;
        try {
            char classe = getClasseChar();



        String creditCardSecCode = displayAndResponse("enter the security code of the credit card");

        if (controller.changeBookedReservable(reservationNumber,travelId,creditCardSecCode,classe,preferWindow)){
            display("reservation changed");
        }
        else {
            display("reservation could not be changed");
        }
        }catch (NoClassWithThisName e){
            display(e.getMessage());
            return;
        }

    }


    /**
     * helper function that asks the full name of the classe wanted and the mean of travel to return the identifying char for the classe
     *
     * @return the identifying char for the full name of the class entered by the user when prompted
     */
    private char getClasseChar() {
        String classeAsString = prepareStringForDisplayAv(displayAndResponse("enter the class wanted"));
        //format string for hashmap in controller
        String travelType = displayAndResponse("enter the mean of travel (boat,train,plane)").toLowerCase().trim();
        classeAsString = travelType + classeAsString;
        if (controller.getSectionNameToChar().get(classeAsString)==null){
            String keysAcc="";
            Set keys = controller.getSectionNameToChar().keySet();
            int size = keys.size();
            int i = 0;
            for (Object key: keys) {
                keysAcc+= (String) key;
                if (i<(size-1)){
                    keysAcc+=", ";
                }
                i++;
            }
            throw new NoClassWithThisName("there is no class with this name. The classes available are : "+keysAcc);
        }

        char classe = controller.getSectionNameToChar().get(classeAsString);

        return classe;

    }




    private  class NoClassWithThisName extends RuntimeException{

        public NoClassWithThisName(String message) {
            super(message);
        }
    }


}
