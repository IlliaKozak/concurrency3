package nl.saxion.concurrency;

import akka.actor.ActorRef;

public class CreateReservation {

    ActorRef booker;
    String roomNr;
    public CreateReservation(ActorRef booker,String roomNr) {
        this.booker = booker;
        this.roomNr = roomNr;
    }
}
