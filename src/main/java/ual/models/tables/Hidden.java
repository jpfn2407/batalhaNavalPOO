package ual.models.tables;

import ual.models.boats.Boat;

public class Hidden {
    private String[][] hiddenTable;
    public Hidden(){
        this.hiddenTable = new String[10][10];
    }

    public String getShotAt(String tableLine, String tableColumn) {
        return "";
    }

    private Integer translatePosX(String tableColumn){
        return ((((int)tableColumn.toCharArray()[0])) - 'A');
    }

    private Integer translatePosY(String tableLine){
        return (((Integer.parseInt(tableLine) - 1)));
    }
}
