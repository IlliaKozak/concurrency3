package nl.saxion.concurrency;

import akka.actor.ActorRef;

public class GetBroker {
    ActorRef customer;

    GetBroker(ActorRef ref){
        this.customer = ref;
    }
}
