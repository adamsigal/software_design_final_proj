package UI;

import AppLauncher.Launcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminCommandLineInterface extends CLI{
	private AdminBuildingController buildingController = new AdminBuildingController();
	private AdminCompanyController companyController = new AdminCompanyController();
	private AdminITravelController iTravelController = new AdminITravelController();
	private AdminSectionController sectionController = new AdminSectionController();
	private Controller generalController = new Controller();

	/**
	 * initialize all the commands
	 */
	protected void initAvailableCommands(){
		_commands = new HashMap<>();
		_commands.put("help", this::displayHelp);

		_commands.put("create airport", this::createAirport);
		_commands.put("create train station", this::createTrainStation);
		_commands.put("create port", this::createPort);

		_commands.put("modify airport", this::modifyAirport);
		_commands.put("modify train station", this::modifyTrainStation);
		_commands.put("modify port", this::modifyPort);

		_commands.put("delete port", this::deletePort);
		_commands.put("delete airport", this::deleteAirport);
		_commands.put("delete train station", this::deleteTrainStation);

		_commands.put("create airline company", this::createAirlineCompany);
		_commands.put("create cruise company", this::createCruiseCompany);
		_commands.put("create train line company", this::createTrainLineCompany);

		_commands.put("delete airline company", this::deleteAirlineCompany);
		_commands.put( "delete cruise company", this::deleteCruiseCompany);
		_commands.put("delete train line company", this::deleteTrainLineCompany);

		_commands.put("modify airline company", this::modifyAirlineCompany);
		_commands.put("modify cruise company", this::modifyCruiseCompany);
		_commands.put("modify train line company", this::modifyTrainlineCompany);

		_commands.put("create flight", this::createFlight);
		_commands.put("create cruise", this::createCruise);
		_commands.put("create train traject", this::createTrainTraject);

		_commands.put("modify cruise", this::modifyCruise);
		_commands.put("modify train traject", this::modifyTrainTraject);
		_commands.put("modify flight", this::modifyFlight);

		_commands.put("assign train traject price", this::assignTrainTrajectPrice);
		_commands.put("assign cruise price", this::assignCruisePrice);
		_commands.put("assign flight price", this::assignFlightPrice);

		_commands.put("delete cruise", this::deleteCruise);
		_commands.put("delete flight", this::deleteFlight);
		_commands.put("delete train traject", this::deleteTrainTraject);

		_commands.put("create plane first section", this::createPlaneFirstSection);
		_commands.put("create plane economic section", this::createPlaneEconomicSection);
		_commands.put("create plane premium section", this::createPlanePremiumSection);
		_commands.put("create plane business section", this::createPlaneBusinessSection);

		_commands.put("create train economic section", this::createTrainEconomicSection);
		_commands.put("create train first section", this::createTrainFirstSection);
		_commands.put("create boat ocean section", this::createBoatOceanSection);

		_commands.put( "create boat interior section", this::createBoatInteriorSection);
		_commands.put("create boat suite section", this::createBoatSuiteSection);
		_commands.put("create boat family section", this::createBoatFamilySection);
		_commands.put("create boat deluxe section", this::createBoatDeluxeSection);

		_commands.put("undo", this::undo);
		_commands.put("exit", this::exit);
		_commands.put("display all travels",this::displayTravels);

	}

	/**
	 * begins interaction loop that ends when exit is entered in the terminal
	 */
	public void run(){
		initAvailableCommands();

		displayHelp();
		String demand = "please enter a command or enter help for a list of possible commands:";
		String response;
		while (isRunning){

			response= displayAndResponse(demand);
			response = response.trim().toLowerCase();

			if(_commands.get(response) != null){
				_commands.get(response).run();

			}else{
				display("Unknown Command, please use the help command!");
			}
		}
	}


	/**
	 * function to modify a port. asks the id of the port to modify and the new id that we want to give to it.
	 * We also ask if we want to modify the city in wich the port is situated. If yes we ask the new city
	 */
	private void modifyPort(){
		String oldId = displayAndResponse("enter id of the port you wish to modify");
		String newId = displayAndResponse("enter the new id of the port ");
		String city;
		if (displayAndResponse("do you wish to modify the city of the port?(yes/no)").compareTo("no")==0){
			city = buildingController.getCityOfBuilding(oldId);
		}
		else {
			city = displayAndResponse("enter the new city of the port");
		}

		buildingController.modifyPortCommandById(city,newId,oldId);

	}
	/**
	 * function to modify an airport. asks the id of the airport to modify and the new id that we want to give to it.
	 * We also ask if we want to modify the city in wich the airport is situated. If yes we ask the new city
	 */
	private void modifyAirport(){
		String oldId = displayAndResponse("enter id of the airport you wish to modify");
		String newId = displayAndResponse("enter the new id of the airport ");
		String city;
		if (displayAndResponse("do you wish to modify the city of the airport?(yes/no)").compareTo("no")==0){
			city = buildingController.getCityOfBuilding(oldId);
		}
		else {
			city = displayAndResponse("enter the new city of the airport");
		}

		buildingController.modifyAirportCommandById(city,newId,oldId);

	}
	/**
	 * function to modify a train station. asks the id of the train station to modify and the new id that we want to give to it.
	 * We also ask if we want to modify the city in wich the train station is situated. If yes we ask the new city
	 */
	private void modifyTrainStation(){
		String oldId = displayAndResponse("enter id of the train station you wish to modify");
		String newId = displayAndResponse("enter the new id of the train station ");
		String city;
		if (displayAndResponse("do you wish to modify the city of the train station?(yes/no)").compareTo("no")==0){
			city = buildingController.getCityOfBuilding(oldId);
		}
		else {
			city = displayAndResponse("enter the new city of the train station");
		}

		buildingController.modifyTrainStationCommandById(city,newId,oldId);
	}

	/**
	 * function to delete an airport. asks the id of the airport we wish to delete
	 */
	private void deleteAirport(){
		String id = displayAndResponse("enter the id of the airport you wish to delete");
		String city = buildingController.getCityOfBuilding(id);
		buildingController.deleteAirport(id,city);

	}
	/**
	 * function to delete a port. asks the id of the port we wish to delete
	 */
	private void deletePort(){
		String id = displayAndResponse("enter the id of the port you wish to delete");
		String city = buildingController.getCityOfBuilding(id);
		buildingController.deletePort(id,city);
	}
	/**
	 * function to delete a train station. asks the id of the train station we wish to delete
	 */
	private void deleteTrainStation(){
		String id = displayAndResponse("enter the id of the train station you wish to delete");
		String city = buildingController.getCityOfBuilding(id);
		buildingController.deleteTrainStation(id,city);
	}

	/**
	 * function to create a train station. asks the id of the train station we wish to create and the city in wich it is situated
	 */
	private void createTrainStation(){
		String id = displayAndResponse("enter the  id of the train station you wish to create");
		String city = displayAndResponse("enter city of the train station you wish to create");
		buildingController.createTrainStationCommand(id,city);
	}
	/**
	 * function to create an airport. asks the id of the airport we wish to create and the city in wich it is situated
	 */
	private void createAirport(){
		String id = displayAndResponse("enter the  id of the  airport you wish to create");
		String city = displayAndResponse("enter city of the  airport you wish to create");
		buildingController.createAirportCommand(id,city);
	}
	/**
	 * function to create an port. asks the id of the port we wish to create and the city in wich it is situated
	 */
	private void createPort(){
		String id = displayAndResponse("enter the  id of the  port you wish to create");
		String city = displayAndResponse("enter city of the  port you wish to create");
		buildingController.createPortCommand(id,city);
	}
	/**
	 * function to create an airline company. asks the id of the airline company we wish to create
	 */
	private void createAirlineCompany(){
		String id = displayAndResponse("enter the  id of the  company you wish to create");
		companyController.createAirlineCompany(id);
	}
	/**
	 * function to create a cruise company. asks the id of the cruise company we wish to create
	 */
	private void createCruiseCompany(){
		String id = displayAndResponse("enter the  id of the  company you wish to create");
		companyController.createCruiseCompany(id);
	}
	/**
	 * function to create a train line company. asks the id of the train line company we wish to create
	 */
	private void createTrainLineCompany(){
		String id = displayAndResponse("enter the  id of the  company you wish to create");
		companyController.createTrainLineCompany(id);
	}
	/**
	 * function to modify an airline company. asks the id of the airline company we wish to modify and the new id
	 */
	private void modifyAirlineCompany(){
		String oldId = displayAndResponse("enter the  id of the  company you wish to modify");
		String newId = displayAndResponse("enter the  new id of the  company");
		companyController.modifyAirlineCompanyById(newId,oldId);
	}
	/**
	 * function to modify a cruise company. asks the id of the cruise company we wish to modify and the new id
	 */
	private void modifyCruiseCompany(){
		String oldId = displayAndResponse("enter the  id of the  company you wish to modify");
		String newId = displayAndResponse("enter the  new id of the  company");
		companyController.modifyAirlineCompanyById(newId,oldId);
	}
	/**
	 * function to modify a train line company. asks the id of the train line company we wish to modify and the new id
	 */
	private void modifyTrainlineCompany(){
		String oldId = displayAndResponse("enter the  id of the  company you wish to modify");
		String newId = displayAndResponse("enter the  new id of the  company");
		companyController.modifyTrainlineCompanyById(newId,oldId);
	}
	/**
	 * function to delete an airline company. asks the id of the airline company we wish to delete
	 */
	private void deleteAirlineCompany(){
		String id = displayAndResponse("enter the  id of the  company you wish to delete");
		companyController.deleteAirlineCompany(id);
	}
	/**
	 * function to delete a cruise company. asks the id of the cruise company we wish to delete
	 */
	private void deleteCruiseCompany(){
		String id = displayAndResponse("enter the  id of the  company you wish to delete");
		companyController.deleteCruiseCompany(id);
	}
	/**
	 * function to delete a train line company. asks the id of the train line company we wish to delete
	 */
	private void deleteTrainLineCompany(){
		String id = displayAndResponse("enter the  id of the  company you wish to delete");
		companyController.deleteTrainlineCompany(id);
	}

	/**
	 * function to create a train traject. asks the arrival date, the arrival time, the  departure date, the  departure time,
	 * the id of the train station of departure, the id of the train station of destination, the id of the traject and the identifier
	 * of the train line company. If the traject has intermediate stops then we asks id of each intermediate train station
	 */
	private void createTrainTraject(){
		String arrivalDate = displayAndResponse("enter the arrival date");
		String arrivalTime = displayAndResponse("enter the arrival time");
		String departureDate = displayAndResponse("enter the departure date");
		String departureTime = displayAndResponse("enter the time of departure");
		String sourceId = displayAndResponse("enter the id of the source of the trip");
		String destinationId = displayAndResponse("enter the id of the destination of the trip");
		String travelId = displayAndResponse("enter the id of the itinerary");
		String companyId = displayAndResponse("enter the identifier of the train line company");
		//Arraylist of String corresponding to the ids of the intermediate stops
		ArrayList<String> sequence = getArrayIntermediateDest("train station");

		iTravelController.createTrainTraject(arrivalDate,arrivalTime,departureDate,departureTime,sourceId,destinationId,travelId,sequence,companyId);
	}
	/**
	 * function to create a flight . asks the arrival date, the arrival time, the  departure date, the  departure time,
	 * the id of the airport of departure, the id of airport of destination and the id of the flight.
	 */
	private void createFlight(){
		String arrivalDate = displayAndResponse("enter the arrival date");
		String arrivalTime = displayAndResponse("enter the arrival time");
		String departureDate = displayAndResponse("enter the departure date");
		String departureTime = displayAndResponse("enter the time of departure");
		String sourceId = displayAndResponse("enter the id of the source of the trip");
		String destinationId = displayAndResponse("enter the id of the destination of the trip");
		String travelId = displayAndResponse("enter the id of the itinerary");
		String companyId = displayAndResponse("enter the identifier of the airline Company");

		iTravelController.createFlight(arrivalDate,arrivalTime,departureDate,departureTime,sourceId,destinationId,travelId,companyId);
	}
	/**
	 * function to create a cruise. asks the arrival date, the arrival time, the  departure date, the  departure time,
	 * the id of the port of departure, the id of the port of destination, the id of the cruise and the identifier of the cruise line company.
	 * If the cruise has intermediate stops then we asks id of each intermediate port
	 */
	private void createCruise(){
		String arrivalDate = displayAndResponse("enter the arrival date");
		String arrivalTime = displayAndResponse("enter the arrival time");
		String departureDate = displayAndResponse("enter the departure date");
		String departureTime = displayAndResponse("enter the time of departure");
		String sourceId = displayAndResponse("enter the id of the source of the trip");
		String destinationId = displayAndResponse("enter the id of the destination of the trip");
		String travelId = displayAndResponse("enter the id of the itinerary");
		String companyId = displayAndResponse("enter the identifier of the cruise line company");

		//Arraylist of String corresponding to the ids of the intermediate stops
		ArrayList<String> sequence = getArrayIntermediateDest("port");
		iTravelController.createCruise(arrivalDate,arrivalTime,departureDate,departureTime,sourceId,destinationId,travelId,sequence,companyId);
	}
	/**
	 * function to modify a flight. asks the id of the flight we want to modify, the arrival date, the arrival time,
	 *  the departure date, the departure time,the id of the airport of departure,
	 *  the id of airport of destination and  the new id of the flight.
	 */
	private void modifyFlight(){

		String oldId = displayAndResponse("enter the id of the itinerary you wish to modify");
		String arrivalDate = displayAndResponse("enter the new arrival date");
		String arrivalTime = displayAndResponse("enter the new arrival time");
		String departureDate = displayAndResponse("enter the new departure date");
		String departureTime = displayAndResponse("enter the new time of departure");
		String sourceId = displayAndResponse("enter the new  id of the source of the trip");
		String destinationId = displayAndResponse("enter the new id of the destination of the trip");
		String newTravelId = displayAndResponse("enter the new id of the itinerary");
		iTravelController.modifyFlightById(arrivalDate,arrivalTime,departureDate,departureTime,sourceId,destinationId,oldId,newTravelId);
	}
	/**
	 * function to modify a cruise. asks the id of the cruise we want to modify, the arrival date, the arrival time,
	 *  the departure date, the departure time,the id of the port of departure,
	 *  the id of port of destination and  the new id of the flight.
	 *  If the cruise has intermediate stops we asks the id of each intermediate port
	 */
	private void modifyCruise(){
		String oldId = displayAndResponse("enter the id of the itinerary you wish to modify");
		String arrivalDate = displayAndResponse("enter the new arrival date");
		String arrivalTime = displayAndResponse("enter the new arrival time");
		String departureDate = displayAndResponse("enter the new departure date");
		String departureTime = displayAndResponse("enter the new time of departure");
		String sourceId = displayAndResponse("enter the new  id of the source of the trip");
		String destinationId = displayAndResponse("enter the new id of the destination of the trip");
		String newTravelId = displayAndResponse("enter the new id of the itinerary");

		ArrayList<String> sequence = getArrayIntermediateDest("port");

		iTravelController.modifyCruiseById(arrivalDate,arrivalTime,departureDate,departureTime,sourceId,destinationId,oldId,newTravelId,sequence);
	}
	/**
	 * function to modify a train traject. asks the id of the train traject we want to modify, the arrival date, the arrival time,
	 *  the departure date, the departure time,the id of the train station of departure,
	 *  the id of the train station of destination and  the new id of the flight.
	 *  If the cruise has intermediate stops we asks the id of each intermediate train station
	 */
	private void modifyTrainTraject(){
		String oldId = displayAndResponse("enter the id of the itinerary you wish to modify");
		String arrivalDate = displayAndResponse("enter the new arrival date");
		String arrivalTime = displayAndResponse("enter the new arrival time");
		String departureDate = displayAndResponse("enter the new departure date");
		String departureTime = displayAndResponse("enter the new time of departure");
		String sourceId = displayAndResponse("enter the new  id of the source of the trip");
		String destinationId = displayAndResponse("enter the new id of the destination of the trip");
		String newTravelId = displayAndResponse("enter the new id of the itinerary");

		ArrayList<String> sequence = getArrayIntermediateDest("train station");

		iTravelController.modifyTrainTrajectById(arrivalDate,arrivalTime,departureDate,departureTime,sourceId,destinationId,oldId,newTravelId,sequence);
	}

	/**
	 *
	 * @param typeOfBuilding the name of the type of building
	 * @return an Arraylist of String corresponding to the ids of the intermediate stops
	 */
	private ArrayList<String> getArrayIntermediateDest(String typeOfBuilding){
		ArrayList<String> sequence = new ArrayList<>();
		if (displayAndResponse("Does the itinerary have intermediate stops? (yes/no)").compareTo("yes")==0){
			boolean remaining = true;
			int i = 0;
			String response;
			//while there remains intermediate stops to enter
			while (remaining){
				++i;
				if (i==1){
					response = 	displayAndResponse("enter the id of the firsth intermediate "+typeOfBuilding);

				}
				else{
					response = displayAndResponse("enter the id of the "+ i +"ith intermediate "+typeOfBuilding);
				}
				sequence.add(response);

				remaining= displayAndResponse("is there more intermediate "+typeOfBuilding+"?(yes/no)").compareTo("yes")==0;
			}
		}
		return sequence;
	}

	/**
	 * function to delete a cruise. asks the id of the cruise we want to delete
	 */
	private void deleteCruise(){
		String id = displayAndResponse("enter the id of the cruise to be deleted");
		iTravelController.deleteCruiseById(id);
	}
	/**
	 * function to delete a flight. asks the id of the flight we want to delete
	 */
	private void deleteFlight(){
		String id = displayAndResponse("enter the id of the flight to be deleted");
		iTravelController.deleteFlightById(id);
	}
	/**
	 * function to delete a train traject. asks the id of the train traject we want to delete
	 */
	private void deleteTrainTraject(){
		String id = displayAndResponse("enter the id of the train traject to be deleted");
		iTravelController.deleteTrainTrajectById(id);
	}

	/**
	 * function to assign a price to a certain train traject. asks the id of the train traject to wich we want to assign the price and
	 * the price.
	 */
	private void assignTrainTrajectPrice(){
		String id = displayAndResponse("enter the id of the train traject you wish to change the price");
		float price = Float.parseFloat(displayAndResponse("enter the new price of the train traject"));
		iTravelController.assignTrainTrajectPrice(price,id);
		display("price modified");
	}
	/**
	 * function to assign a price to a certain cruise. asks the id of the cruise to wich we want to assign the price and
	 * the price.
	 */
	private void assignCruisePrice(){
		String id = displayAndResponse("enter the id of the cruise you wish to change the price");
		float price = Float.parseFloat(displayAndResponse("enter the new price of the cruise"));
		iTravelController.assignCruisePrice(price,id);
		display("price modified");
	}
	/**
	 * function to assigne a price to a certain flight. asks the numerical part of the id of the flight to wich we want to assign the price,
	 * the id of the airline company that offers the flight and the price.
	 */
	private void assignFlightPrice(){
		int intId = parseIntFromResponse("enter the numerical part of the id of the flight");
		String id = displayAndResponse("enter the id of the airline thath offers the flight you wish to change the price");
		float price = Float.parseFloat(displayAndResponse("enter the new price of the flight"));

		iTravelController.assignFlightPrice(price,intId,id);
		display("price modified");
	}

	/**
	 * function to create a first class section in a specific plane. asks the id of the plane to wich we want to add a section
	 * and the type of seat that the section will contain in the form of a character
	 */
	private void createPlaneFirstSection(){
		String id = displayAndResponse("enter the id of the plane to wich you want to add the section");
		char seatType = displayAndResponse("enter identifier of the type of seat").charAt(0);
		sectionController.createPlaneFirstSection(id,seatType);
	}

	/**
	 * function to create a premium section in a specific plane. asks the id of the plane to wich we want to add a section
	 * and the type of seat that the section will contain in the form of a character.
	 */
	private void createPlanePremiumSection(){
		String id = displayAndResponse("enter the id of the plane to wich you want to add the section");
		char seatType = displayAndResponse("enter identifier of the type of seat").charAt(0);

		sectionController.createPlanePremiumSection(id,seatType);
	}
	/**
	 * function to create a business class section in a specific plane. asks the id of the plane to wich we want to add a section
	 * and the type of seat that the section will contain in the form of a character.
	 */
	private void createPlaneBusinessSection(){
		String id = displayAndResponse("enter the id of the plane to wich you want to add the section");
		char seatType = displayAndResponse("enter identifier of the type of seat").charAt(0);

		sectionController.createPlaneBusinessSection(id,seatType);
	}
	/**
	 * function to create an economic section in a specific plane. asks the id of the plane to wich we want to add a section
	 * and the type of seat that the section will contain in the form of a character.
	 */
	private void createPlaneEconomicSection(){
		String id = displayAndResponse("enter the id of the plane to wich you want to add the section");
		char seatType = displayAndResponse("enter identifier of the type of seat").charAt(0);

		sectionController.createPlaneEconSection(id,seatType);
	}
	/**
	 * function to create an economic section in a specific train. asks the id of the train to wich we want to add a section.
	 */
	private void createTrainEconomicSection(){
		String id = displayAndResponse("enter the id of the train to wich you want to add the section");
		sectionController.createTrainEconSection(id);
	}
	/**
	 * function to create a first clas section in a specific train. asks the id of the train to wich we want to add a section.
	 */
	private void createTrainFirstSection(){
		String id = displayAndResponse("enter the id of the train to wich you want to add the section");
		sectionController.createTrainFirstSection(id);
	}
	/**
	 * function to create a ocean section in a specific cruise ship. asks the id of the cruise ship to wich we want to add a section.
	 */
	private void createBoatOceanSection(){
		String id = displayAndResponse("enter the id of the cruise ship to wich you want to add the section");
		sectionController.createBoatOceanSection(id);
	}
	/**
	 * function to create an interior section in a specific cruise ship. asks the id of the cruise ship to wich we want to add a section.
	 */
	private void createBoatInteriorSection(){
		String id = displayAndResponse("enter the id of the cruise ship to wich you want to add the section");
		sectionController.createBoatInteriorSection(id);
	}
	/**
	 * function to create a family section in a specific cruise ship. asks the id of the cruise ship to wich we want to add a section.
	 */
	private void createBoatFamilySection(){
		String id = displayAndResponse("enter the id of the cruise ship to wich you want to add the section");
		sectionController.createBoatFamilySection(id);
	}
	/**
	 * function to create a deluxe section in a specific cruise ship. asks the id of the cruise ship to wich we want to add a section.
	 */
	private void createBoatDeluxeSection(){
		String id = displayAndResponse("enter the id of the cruise ship to wich you want to add the section");
		sectionController.createBoatDeluxeSection(id);
	}
	/**
	 * function to create a suite section in a specific cruise ship. asks the id of the cruise ship to wich we want to add a section.
	 */
	private void createBoatSuiteSection(){
		String id = displayAndResponse("enter the id of the cruise ship to wich you want to add the section");
		sectionController.createBoatSuiteSection(id);
	}

	/**
	 * function to undo the previous action
	 */
	private void undo(){
		generalController.undo();
	}

	/**
	 * function to exit the interaction loop
	 */
	private void exit(){
		isRunning = false;
		Launcher.isRunning = false;
	}

	/**
	 * function to display the informations of all the travels
	 */
	private void displayTravels(){
		ArrayList<String> list = 	sectionController.visitITravel();
		for (String travel: list) {
			display(travel);
		}
	}
	

}
