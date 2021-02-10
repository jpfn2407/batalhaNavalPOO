package ual.models.boats;

import ual.models.tables.Position;

import java.util.List;

public interface Boat {

    String getName();
    String getCode();
    Integer getSize();
    void addPosition(Integer posX, Integer posY);
    boolean hasValue(Integer posX, Integer posY);
    void removePosition(Integer posX, Integer posY);
    List<Position> getPositionsArray();
    boolean hasPositionsLeft();

}
