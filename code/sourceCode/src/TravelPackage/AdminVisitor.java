package TravelPackage;

import ReservablePackage.*;
import SectionPackage.*;

public class AdminVisitor implements Visitor {
    @Override
    public String visit(ITravel travel, char classe) {
        //classe is empty ' ' in admin
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
                out+=section.getSectionId()+section.getReservableList().get(0).getSeatType().getSeatId();
                out+= getReservedSeatFormat(section);
                out+= section.getBasePrice()*section.getPriceModifier();
                out+="|";
            }
        }
        if(travel instanceof Cruise){
            for(IBoatSection section : ((Cruise) travel).getBoat().getSectionList()){
                out+= section.getSectionId()+"C";//C is for cabin
                out+= getReservedSeatFormat(section);
                out+= section.getBasePrice()*section.getPriceModifier();
                out+="|";
            }
        }
        if(travel instanceof TrainTraject){
            for(ITrainSection section : ((TrainTraject) travel).getTrain().getSectionList()){
                out+= section.getSectionId() + "S";
                out+= getReservedSeatFormat(section);
                out+= section.getBasePrice()*section.getPriceModifier();
                out+="|";
            }
        }
        return out;
    }
    private String getReservedSeatFormat(ISection section){
        String out ="";
        int reserved = 0;
        if(section instanceof IPlaneSection){
            for(PlaneSeatable planeSeat : ((IPlaneSection) section).getReservableList()){
                if(planeSeat.getStateIReservable() instanceof ReservedState){
                    reserved ++;
                }
            }
            out="("+reserved+"/"+((IPlaneSection) section).getReservableList().size()+')';
        }
        if(section instanceof ITrainSection){
            for(TrainSeatable trainSeat : ((ITrainSection) section).getReservableList()){
                if(trainSeat.getStateIReservable() instanceof  ReservedState){
                    reserved++;
                }
            }

        }
        if(section instanceof IBoatSection){
            for(ICabinable cabine : ((IBoatSection) section).getReservableList()){
                if(cabine.getStateIReservable() instanceof ReservedState){
                    reserved++;
                }
            }
            out="("+reserved+"/"+((IBoatSection) section).getReservableList().size()+')';
        }
        return out;
    }

}