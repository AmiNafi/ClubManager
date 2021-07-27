package sample;

import java.io.Serializable;
import java.util.Locale;

public class Player implements Serializable {
    private String name, country, club, position, profilePicSrc;
    private int age, number, market;
    private double height, weeklySalary, price;
    public Player () {
        name = club = position = "";
        age = number = 0;
        height = weeklySalary = 0.0;
    }
    public Player (String name, String country, int age, double height, String club, String  position, int number, double weeklySalary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.weeklySalary = weeklySalary;
        //System.out.println(" Thiiiiiiiiiiiiis here " + this.profilePicSrc);
    }
    public void setName(String name) {

        this.name = name;
        profilePicSrc = "/img/" + name.replaceAll("\\s+","").toLowerCase() + ".jpg";
    }

    public int getMarket() {
        return market;
    }

    public void setMarket(int market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getClub() {
        return club;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public String getProfilePicSrc() {
        return profilePicSrc;
    }

    public void setProfilePicSrc(String profilePicSrc) {
        this.profilePicSrc = profilePicSrc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
/*
    public void show() {
        System.out.println("Player Info");
        System.out.println("Name : " + name);
        System.out.println("Country : " + country);
        System.out.println("Age : " + age);
        System.out.println("Height : " + height);
        System.out.println("Club : " + club);
        System.out.println("Position : " + position);
        System.out.println("Number : " + number);
        System.out.println("Weekly Salary : " + weeklySalary);
    }
*/
}
