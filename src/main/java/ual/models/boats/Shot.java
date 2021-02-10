package ual.models.boats;

import ual.models.tables.Position;

import java.util.List;

public class Shot implements TablePiece{
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

}
