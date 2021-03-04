package ual.models.boats;

import ual.models.tables.*;
import java.util.*;

public class BoatClass implements Boat{
    private String name;
    private String code;
    private Integer size;
    private List<Position> positions;

    public BoatClass(String name, String code, Integer size){
        this.name = name;
        this.code = code;
        this.size = size;
        this.positions = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getSize() {
        return this.size;
    }

    public void addPosition(Integer posX, Integer posY){
        this.positions.add(new Position(posX, posY));
    }

    public boolean hasValue(Integer posX, Integer posY){
        if(!positions.isEmpty()) {
            for (Position position : positions) {
                if ((position.getPosX() == posX) && (position.getPosY() == posY)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removePosition(Integer posX, Integer posY){
        if(!positions.isEmpty()) {
            for (int i=0; i<positions.size(); i++) {
                if ((this.positions.get(i).getPosX() == posX) && (this.positions.get(i).getPosY() == posY)) {
                    this.positions.remove(i);
                }
            }
        }
    }

    public List<Position> getPositionsArray(){
        return new ArrayList<>(this.positions);
    }

    public boolean hasPositionsLeft(){
        return !this.positions.isEmpty();
    }

}
