package com.springangular.springangular.controller;

import com.springangular.springangular.model.Reservation;
import com.springangular.springangular.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
//annotation is necessary because our angular app and spring app run on different ports(technically they are different origins)
//helps us run our angular app and spring-app together locally (can be a security issue in production)
@CrossOrigin
public class ReservationResource {

    //constant string available for use when we need it
    public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation/";

    private final ReservationService reservationService;

    @Autowired
    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> getReservationById(@PathVariable String id) {
        return reservationService.getReservation(id);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Reservation> getAllReservations() {
        return reservationService.listAllReservations();
    }

    //this endpoint will use the base string request-mapping, will produce a json response, also expects a json requestbody
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    //requestbody will deserialize a JSON requestbody into a reservation object
    public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservation) {

        return reservationService.createReservation(reservation);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> updatePrice(@PathVariable String id,
            @RequestBody Mono<Reservation> reservation) {

        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping(value = "{id}")
    public Mono<Boolean> deleteReservation(@PathVariable String id) {

        return reservationService.deleteReservation(id);
    }
}
