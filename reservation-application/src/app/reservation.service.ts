import { Injectable } from '@angular/core';
import {HttpClient, HttpHandler, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ReservationService {
//using the httpclient inside of reservation service

//HttpClient is used to fetch external data, post to it, etc
  constructor(private http:HttpClient) {}

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.reservationUrl);
  }

  //linking our backend to the front end, baseurl is the localhost:88080
  //then appending our reservation endpoint /room/v1/reservation/
    private baseUrl:string = 'http://localhost:8080';
    private reservationUrl:string = this.baseUrl + '/room/v1/reservation/';


    createReservation(body: ReservationRequest): Observable<Reservation> {
      let httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json'})
      };
      return this.http.post<Reservation>(this.reservationUrl, body, httpOptions);
    }
}


export class ReservationRequest {
  roomNumber: number;
  checkIn: string;
  checkOut: string;
  price: number;

  constructor(roomNumber: number, checkIn: string, checkOut: string, price: number) {
    this.roomNumber = roomNumber;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.price = price;
  }
}

export interface Reservation {
  id: String;
  roomNumber: number;
  checkIn: Date;
  checkOut: Date;
  price: number;
}
