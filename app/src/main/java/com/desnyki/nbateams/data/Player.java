package com.desnyki.nbateams.data;

import com.google.gson.annotations.SerializedName;

public class Player {
    int id;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    String position;
    int number;

    public Player(int id, String firstName, String lastName, String position, int number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", number=" + number +
                '}';
    }
}
