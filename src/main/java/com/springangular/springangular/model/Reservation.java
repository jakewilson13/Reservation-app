package com.springangular.springangular.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//equivelant to entity, will identify this class as a domain object to be persisted in mongodb
@Document
public class Reservation {
    //de-normalized data format, in normalized SQL database you may be storing a room id
    //since using a NOSQL database we are going to store information as we retrieve it
    private Long roomNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkIn;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkOut;
    private Integer price;

    //this id will be an auto-generated HASH from mongodb (primary key)
    @Id
    private String id;

    public Reservation() {
    }

    public Reservation(Long roomNumber, LocalDate checkIn, LocalDate checkOut, Integer price) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "roomNumber=" + roomNumber +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", price=" + price +
                ", id='" + id + '\'' +
                '}';
    }
}