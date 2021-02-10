package ual.models.boats;

import ual.models.tables.Position;

import java.util.List;

public class Shot implements Boat{
    private String name;
    private String code;
    private Integer size;
    public Shot(){
        this.name = "Tiro";
        this.code = "T";
        this.size = 1;
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

    public void addPosition(Integer posX, Integer posY) {

    }

    public boolean hasValue(Integer posX, Integer posY) {
        return false;
    }

    public void removePosition(Integer posX, Integer posY) {

    }

    public List<Position> getPositionsArray() {
        return null;
    }

    public boolean hasPositionsLeft() {
        return false;
    }

}
