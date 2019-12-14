package TravelPackage;

import ReservablePackage.*;
import SectionPackage.IBoatSection;
import SectionPackage.IPlaneSection;
import SectionPackage.ISection;
import SectionPackage.ITrainSection;

public class UserVisitor implements Visitor {


    @Override
    public String  visit(ITravel travel, char classe) {
        String out = "";
        out += travel.getSource().getBuildingId() + "-" + travel.getDestination().getBuildingId();
        out += ":[" + travel.getCompany().getCompanyId() + "]";
        out += travel.getTravelId().getId();
        out += "(" + travel.getDepartureDate()+":";
        out += travel.getDepartureTime() + "-";
        out += travel.getArrivalDate() + ":";
        out += travel.getDepartureTime() + ")";
        out += "|";

        if(travel instanceof Flight){
            for(IPlaneSection section : ((Flight) travel).getPlane().getSectionList()){
                if(section.getSectionId() == classe){
                    out+= section.getBasePrice()*section.getPriceModifier();
                    out+="|";
                    out += getAvailableSeats(section);

                }

            }
        }
        if(travel instanceof Cruise){
            for(IBoatSection section : ((Cruise) travel).getBoat().getSectionList()){
                if(section.getSectionId() == classe){
                    out+= section.getBasePrice()*section.getPriceModifier();
                    out+="|";

                }
            }
        }
        if(travel instanceof TrainTraject){
            for(ITrainSection section : ((TrainTraject) travel).getTrain().getSectionList()){
                if(section.getSectionId() == classe){
                    out+= section.getBasePrice()*section.getPriceModifier();
                    out+="|";

                }
            }
        }
        return out;
    }
    private String getAvailableSeats(ISection section){
        String out ="";
        int available = 0;
        if(section instanceof IPlaneSection){
            for(PlaneSeatable planeSeat : ((IPlaneSection) section).getReservableList()){
                if(planeSeat.getStateIReservable() instanceof AvailableState){
                    available ++;
                }
            }

        }
        if(section instanceof ITrainSection){
            for(TrainSeatable trainSeat : ((ITrainSection) section).getReservableList()){
                if(trainSeat.getStateIReservable() instanceof  AvailableState){
                    available++;
                }
            }

        }
        if(section instanceof IBoatSection){
            for(ICabinable cabine : ((IBoatSection) section).getReservableList()){
                if(cabine.getStateIReservable() instanceof AvailableState){
                    available++;
                }
            }
        }
        return available+"";
    }

}