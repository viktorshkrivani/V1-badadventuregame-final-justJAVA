package com.example.badadventuregame;

public class NCPMonster {
    private int hitPoints;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int attack;

    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }

    public NCPMonster(){
        hitPoints = 10;
        strength = rollDice() * 2;
        dexterity = rollDice() * 2;
        intelligence = rollDice() * 2;
        this.attack = 1;
    }

    public int getHitPoints(){
        return hitPoints;
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }

    public int getStrength(){
        return strength;
    }


    public int getDexterity(){
        return dexterity;
    }


    public int getIntelligence(){
        return intelligence;
    }


    private int rollDice(){
        int die1 = (int)(Math.random()*6 +1);
        int die2 = (int)(Math.random()*6 +1);
        int total = die1+die2;

        return total;
    }
}
