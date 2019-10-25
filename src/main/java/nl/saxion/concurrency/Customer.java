package nl.saxion.concurrency;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Customer extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    ActorSystem system;
    private ActorRef brokerCenter = null;

    Customer (ActorSystem system){

    }

    public Customer() {
    }

    @Override
    public void preStart() throws Exception {
        getContext().getSystem().getEventStream().publish(new GetBroker(getSelf()));

    }

    ActorRef broker = null;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, msg -> {
            log.debug(msg);
        }).match(ActorRef.class, actorRef -> {
            if(broker == null){
                broker = actorRef;

                for (int i = 0; i <1 ; i++) {
                    broker.tell("I want to reserve a room", getSelf());

                }
            }
        })
                .build();
    }
}
