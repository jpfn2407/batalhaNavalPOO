package ual.models.tables;

public class Position {
    private Integer posX;
    private Integer posY;

    public Position(Integer posX, Integer posY){
        this.posX = posX;
        this.posY = posY;
    }

    public Integer getPosX() {
        return this.posX;
    }

    public Integer getPosY() {
        return this.posY;
    }
}
