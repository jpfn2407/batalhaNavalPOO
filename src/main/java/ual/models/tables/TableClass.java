package ual.models.tables;

import ual.models.boats.*;
import ual.models.players.Player;

import java.util.*;
import java.util.Arrays;

public class TableClass implements Table{
    private Boat[][] table;
    private String[][] hiddenTable;
    private BoatList boatList;
    private OngoingScore score;

    public TableClass(){
        // Matriz [y][x] de String
        this.table = new Boat[10][10];
        this.hiddenTable = new String[10][10];
        this.boatList = new BoatList();
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
        return this.boatList.hasBoatsLeftToPlace(type);
    }

    public boolean hasAnyBoatsLeftToPlace() {
        return this.boatList.hasAnyBoatsLeftToPlace();
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
        Boat boat = this.boatList.getLastBoat(type);
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
        this.boatList.removeBoatFromList(type);
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
        Boat boat = this.table[posY][posX];
        List<Position> positions = boat.getPositionsArray();
        for(Position position: positions){
            Integer modPosX = position.getPosX();
            Integer modPosY = position.getPosY();
            this.table[modPosY][modPosX] = null;
            boat.removePosition(modPosX, modPosY);
        }
        this.boatList.reAddBoat(boat);
    }

    public boolean isShotAtAlreadyShotSpot(String tableLine, String tableColumn) {
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        return (this.table[posY][posX] instanceof Shot);
    }

    public String getShotAt(String tableLine, String tableColumn) {
        Integer posX = this.translatePosX(tableColumn);
        Integer posY = this.translatePosY(tableLine);
        Boat placeOnTable = this.table[posY][posX];
        this.score.addShot();
        if(placeOnTable == null){
            this.table[posY][posX] = new Shot();
            this.hiddenTable[posY][posX] = "T";
            return "Tiro na água.";
        }
        else{
            placeOnTable.removePosition(posX, posY);
            this.table[posY][posX] = new Shot();
            this.hiddenTable[posY][posX] = "N";
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

    private Integer translatePosX(String tableColumn){
        return ((((int)tableColumn.toCharArray()[0])) - 'A');
    }

    private Integer translatePosY(String tableLine){
        return (((Integer.parseInt(tableLine) - 1)));
    }

    public void printTable() {
        for (int y = 0; y < this.table.length; y++) {
            System.out.println(" ");
            for (int x = 0; x < this.table.length; x++) {
                if (this.table[y][x] == null) {
                    System.out.printf("A ");
                } else {
                    System.out.print(this.table[y][x].getCode() + " ");

                }
            }
        }
        System.out.println(" ");
    }

    public void printHiddenTable() {
        for (int y = 0; y < this.hiddenTable.length; y++) {
            System.out.println(" ");
            for (int x = 0; x < this.hiddenTable.length; x++) {
                if (this.hiddenTable[y][x] == null) {
                    System.out.printf("A ");
                } else {
                    System.out.print(this.hiddenTable[y][x] + " ");

                }
            }
        }
        System.out.println(" ");
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