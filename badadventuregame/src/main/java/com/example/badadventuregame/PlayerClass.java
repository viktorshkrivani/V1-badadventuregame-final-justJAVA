package com.example.badadventuregame;

public class PlayerClass {
    private int hitPoints;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int totalGold;

    public PlayerClass() {
        hitPoints = 20;
        strength = rollDice();
        dexterity = rollDice();
        intelligence = rollDice();
        totalGold = 0;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getTotalGold() {
        return totalGold;
    }
    public void addGold(int gold) {
        this.totalGold += gold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int rollDice() {
        int die1 = (int) (Math.random() * 6 + 1);
        int die2 = (int) (Math.random() * 6 + 1);
        int die3 = (int) (Math.random() * 6 + 1);
        int total = die1 + die2 + die3;
        return total;
    }
}