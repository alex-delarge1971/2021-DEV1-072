package com.example.bnp.tennis.model;

public class Player {

    private String name;
    private int point = 0;

    public Player(String name){
        System.out.println(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void scorePoint(){
        point++;
    }

    public String printPoint(){
        switch(point) {
            case 0:
                return "love";
            case 1:
                return "fifteen";
            case 2:
                return "thirty";
            case 3:
                return "forty";

        }
        throw new IllegalArgumentException("The point should be between 0 and 3");

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
