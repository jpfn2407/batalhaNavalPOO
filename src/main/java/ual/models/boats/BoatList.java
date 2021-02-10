package ual.models.boats;

import java.util.*;
import ual.models.boats.*;

public class BoatList {
    private List<Boat> speedboats;
    private List<Boat> submarines;
    private List<Boat> frigates;
    private List<Boat> cruisers;
    private List<Boat> aircraftCarriers;

    public BoatList(){
        /*this.speedboatNumber = 4;
        this.submarineNumber = 3;
        this.frigateNumber = 2;
        this.cruiserNumber = 1;
        this.aircraftCarrier = 1;*/
        this.speedboats = new ArrayList<>();
        this.submarines = new ArrayList<>();
        this.frigates = new ArrayList<>();
        this.cruisers = new ArrayList<>();
        this.aircraftCarriers = new ArrayList<>();
        for(int i = 0; i<4; i++){
            this.speedboats.add(new Speedboat());
        }
        for(int i = 0; i<3; i++){
            this.submarines.add(new Submarine());
        }
        for(int i = 0; i<2; i++){
            this.frigates.add(new Frigate());
        }
        this.cruisers.add(new Cruiser());
        this.aircraftCarriers.add(new AircraftCarrier());
    }

    public boolean hasBoatsLeftToPlace(String type) {
        if(type.equals("L")){
            return this.speedboats.size() != 0;
        }
        else if(type.equals("S")){
            return this.submarines.size() != 0;
        }
        else if(type.equals("F")){
            return this.frigates.size() != 0;
        }
        else if(type.equals("C")){
            return this.cruisers.size() != 0;
        }
        else if(type.equals("P")){
            return this.aircraftCarriers.size() != 0;
        }
        else {
            return this.hasAnyBoatsLeftToPlace();
        }
    }

    public boolean hasAnyBoatsLeftToPlace(){
        return (this.speedboats.size() != 0 ||
                this.submarines.size() != 0 ||
                this.frigates.size() != 0 ||
                this.cruisers.size() != 0 ||
                this.aircraftCarriers.size() != 0);
    }

    public Boat getLastBoat(String type){
        if(type.equals("L")){
            return this.speedboats.get(this.speedboats.size() - 1);
        }
        else if(type.equals("S")){
            return this.submarines.get(this.submarines.size() - 1);
        }
        else if(type.equals("F")){
            return this.frigates.get(this.frigates.size() - 1);
        }
        else if(type.equals("C")){
            return this.cruisers.get(this.cruisers.size() - 1);
        }
        else if(type.equals("P")){
            return this.aircraftCarriers.get(this.aircraftCarriers.size() - 1);
        }
        else{
            return null;
        }
    }

    public void removeBoatFromList(String type){
        if(type.equals("L")){
            this.speedboats.remove(this.speedboats.size() - 1);
        }
        else if(type.equals("S")){
            this.submarines.remove(this.submarines.size() - 1);
        }
        else if(type.equals("F")){
            this.frigates.remove(this.frigates.size() - 1);
        }
        else if(type.equals("C")){
            this.cruisers.remove(this.cruisers.size() - 1);
        }
        else if(type.equals("P")){
            this.aircraftCarriers.remove(this.aircraftCarriers.size() - 1);
        }
    }

    public void reAddBoat(Boat boat){
        if(boat instanceof Speedboat){
            this.speedboats.add(boat);
        }
        else if(boat instanceof Submarine){
            this.submarines.add(boat);
        }
        else if(boat instanceof Frigate){
            this.frigates.add(boat);
        }
        else if(boat instanceof Cruiser){
            this.cruisers.add(boat);
        }
        else if(boat instanceof AircraftCarrier){
            this.aircraftCarriers.add(boat);
        }
    }
}
