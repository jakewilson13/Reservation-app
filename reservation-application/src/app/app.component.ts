import { Component } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Reservation, ReservationRequest, ReservationService} from './reservation.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'reservation-application';
  //referencing our registration.service.ts file and using our RegistrationService in our component to make any http calls we need to our backend rest api
  constructor(private reservationService: ReservationService) {}

 rooms!: Room[];
 roomSearchForm!: FormGroup;
 currentCheckInVal!: string;
 currentCheckOutVal!: string;
 currentPrice!: number;
 currentRoomNumber!: number;
 currentReservations!: Reservation[];

  //gets triggerd when page gets loaded
   ngOnInit() {
   this.roomSearchForm = new FormGroup({
    checkin: new FormControl(''),
    checkout: new FormControl(''),
    roomNumber: new FormControl('')
   });

    //when elements change in the front-end UI, those elements are reflected here
    //the lambda will allow us to preform actions everytime a element changes inside of that form
   this.roomSearchForm.valueChanges.subscribe(form => {
      //this field maps to line 25
      this.currentCheckInVal = form.checkin;
      this.currentCheckOutVal = form.checkout;
    //the value that will come into the backend is a pipe separated string containing the room number and the price
    //if the room number is populated then do that
      if(form.roomNumber) {
      let roomValues: string[] = form.roomNumber.split('|')
      this.currentRoomNumber = Number (roomValues[0]);
      //grabbing the second value from the rooms collection
      this.currentPrice = Number(roomValues[1]);
      }
   });

     this.rooms = [ new Room("127", "127", "150"),
     new Room("138", "138", "180"),
     new Room("254", "254", "200")
     ];

     this.getCurrentReservations();
   }

   getCurrentReservations() {
     this.reservationService.getReservations()
       .subscribe(getResult => {
         console.log(getResult);
         this.currentReservations = getResult;
       })
   }

   createReservation() {
     this.reservationService.createReservation(
       new ReservationRequest(this.currentRoomNumber, this.currentCheckInVal,
         this.currentCheckOutVal, this.currentPrice)).subscribe(postResult => {
         console.log(postResult)
       this.getCurrentReservations();
       }
     );
   }

 }

 export class Room {
   id: string;
   roomNumber: string;
   price: string;

   constructor(id: string, roomNumber: string, price: string) {
     this.id = id;
     this.roomNumber = roomNumber;
     this.price = price;

   }
 }
