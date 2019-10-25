package nl.saxion.concurrency;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;

public class Broker extends AbstractActor {

    ActorSystem system;
    @Override
    public void preStart() throws Exception {
        getContext().getSystem().getEventStream().subscribe(getSelf(),GetBroker.class);

    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, msg -> {
            getSender().tell("Ok - Answer by" + getSelf().path().name(),getSelf());
        }).match(GetBroker.class, msg -> {
            msg.customer.tell(getSelf(), getSelf());
        })


                .build();
    }
}


