package ual.models.tables;

import ual.models.boats.*;
import ual.models.players.Player;

import java.util.*;
import java.util.Arrays;

public class TableClass implements Table{
    private TablePiece[][] table;
    private List<Boat> speedboats;
    private List<Boat> submarines;
    private List<Boat> frigates;
    private List<Boat> cruisers;
    private List<Boat> aircraftCarriers;
    private OngoingScore score;

    public TableClass(){
        // Matriz [y][x] de String
        this.table = new Boat[10][10];
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
        this.score = new OngoingScore();
    }

    //table.length = y ou linha
    //table[0].length = x ou coluna
    public boolean isBoatInside( String tableColumn, String tableLine) {
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        if( (posX >= 0) && (posX <= 9) && (posY >= 0) && (posY <= 9)){
            return true;
        }
        return false;
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

    public boolean isValidBoatPosition(String type, String tableColumn, String tableLine, String tableOrientation) {
        Integer boatSize = this.getBoatSize(type);
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        boolean valid = true;
        if(tableOrientation.equals("N")){
            for(int i=0; i<boatSize; i++){
                if( ((posX >= 0) && (posX <= 9) && (posY >= 0) && (posY <= 9)) && valid){
                    valid = this.checkAroundForOtherBoats(posX, posY);
                    posY--;
                }else{
                    valid = false;
                }
            }
        }
        else if(tableOrientation.equals("S")){
            for(int i=0; i<boatSize; i++){
                if( ((posX >= 0) && (posX <= 9) && (posY >= 0) && (posY <= 9)) && valid){
                    valid = this.checkAroundForOtherBoats(posX, posY);
                    posY++;
                }else{
                    valid = false;
                }
            }
        }
        else if(tableOrientation.equals("E")){
            for(int i=0; i<boatSize; i++){
                if( ((posX >= 0) && (posX <= 9) && (posY >= 0) && (posY <= 9)) && valid){
                    valid = this.checkAroundForOtherBoats(posX, posY);
                    posX++;
                }else{
                    valid = false;
                }
            }
        }
        else if(tableOrientation.equals("O")){
            for(int i=0; i<boatSize; i++){
                if( ((posX >= 0) && (posX <= 9) && (posY >= 0) && (posY <= 9)) && valid){
                    valid = this.checkAroundForOtherBoats(posX, posY);
                    posX--;
                }else{
                    valid = false;
                }
            }
        }
        return valid;
    }

    public void placeBoat(String type, String tableColumn, String tableLine, String tableOrientation) {
        Boat boat = this.getLastBoat(type);
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        if(tableOrientation.equals("N")){
            for(int i=0; i<boat.getSize(); i++){
                this.table[posY][posX] = boat;
                boat.addPosition(posX, posY);
                posY--;
            }
        }
        else if(tableOrientation.equals("S")){
            for(int i=0; i<boat.getSize(); i++){
                this.table[posY][posX] = boat;
                boat.addPosition(posX, posY);
                posY++;
            }
        }
        else if(tableOrientation.equals("E")){
            for(int i=0; i<boat.getSize(); i++){
                this.table[posY][posX] = boat;
                boat.addPosition(posX, posY);
                posX++;
            }
        }
        else if(tableOrientation.equals("O")){
            for(int i=0; i<boat.getSize(); i++){
                this.table[posY][posX] = boat;
                boat.addPosition(posX, posY);
                posX--;
            }
        }
        this.removeBoatFromList(type);
    }

    public boolean isBoatInThisPosition(String tableLine, String tableColumn) {
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        if(this.table[posY][posX] != null){
            return true;
        }
        return false;
    }

    public void removeBoat(String tableLine, String tableColumn) {
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        Boat boat = (Boat) this.table[posY][posX];
        List<Position> positions = boat.getPositionsArray();
        for(Position position: positions){
            Integer modPosX = position.getPosX();
            Integer modPosY = position.getPosY();
            this.table[modPosY][modPosX] = null;
            boat.removePosition(modPosX, modPosY);
        }
        this.reAddBoat(boat);
    }

    public boolean isShotAtAlreadyShotSpot(String tableLine, String tableColumn) {
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        return (this.table[posY][posX] instanceof Shot);
    }

    public String getShotAt(String tableLine, String tableColumn) {
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        Boat placeOnTable = (Boat) this.table[posY][posX];
        this.score.addShot();
        if(placeOnTable == null){
            this.table[posY][posX] = new Shot();
            return "Tiro na água.";
        }
        else{
            placeOnTable.removePosition(posX, posY);
            this.table[posY][posX] = new Shot();
            if(placeOnTable.hasPositionsLeft()){
                this.score.addShotOnBoat();
                return "Tiro em navio.";
            }
            else{
                if(this.hasBoatsLeftToPlay()){
                    this.score.addShotOnBoat();
                    this.score.addSunkedShip();
                    return ("Navio " + placeOnTable.getName() + " afundado.");
                } else {
                    this.score.addShotOnBoat();
                    this.score.addSunkedShip();
                    return ("Navio " + placeOnTable.getName() + " afundado. Jogo terminado.");
                }
            }
        }
    }

    public boolean hasBoatsLeftToPlay(){
        for(int y = 0; y<this.table.length; y++){
            for (int x = 0; x<this.table.length; x++){
                if(this.table[y][x] instanceof BoatClass){
                    return true;
                }
            }
        }
        return false;
    }

    public OngoingScore getOngoingScore(Player player) {
        this.score.setPlayer(player);
        return this.score;
    }

    private boolean checkAroundForOtherBoats(Integer posX, Integer posY){
        boolean valid = true;
        for(int y=-1; y<=1; y++){
            for(int x=-1; x<=1; x++){
                if( (posX+x >= 0) && (posX+x <= 9) && (posY+y >= 0) && (posY+y <= 9)){
                    if(this.table[posY+y][posX+x] != null){
                        valid = false;
                    }
                }
            }
        }
        return valid;
    }

    private Integer getBoatSize(String type){
        if(type.equals("L")){
            return new Speedboat().getSize();
        }
        else if(type.equals("S")){
            return new Submarine().getSize();
        }
        else if(type.equals("F")){
            return new Frigate().getSize();
        }
        else if(type.equals("C")){
            return new Cruiser().getSize();
        }
        else if(type.equals("P")){
            return new AircraftCarrier().getSize();
        }
        else{
            return null;
        }
    }

    private Boat getLastBoat(String type){
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

    private void removeBoatFromList(String type){
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

    private void reAddBoat(Boat boat){
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

    private Integer translatePosX(String tableColumn){
        return ((((int)tableColumn.toCharArray()[0])) - 'A');
    }

    private Integer translatePosY(String tableLine){
        return (((Integer.parseInt(tableLine) - 1)));
    }


    /////////////////////////////////////////////

    public void printDebugTable(){
        for(int y = 0; y<this.table.length; y++) {
            System.out.println(" ");
            for (int x = 0; x < this.table.length; x++) {
                if(this.table[y][x] == null){
                    System.out.printf("A ");
                } else {
                    System.out.print(this.table[y][x].getCode() + " ");

                }
            }
        }

        /*for(Boat[] row : this.table){
            System.out.println(Arrays.toString(row));
        }*/
    }

    public void debugSet(String tableColumn, String tableLine){
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        this.table[posY][posX]=new Speedboat();
    }




}