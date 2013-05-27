package com.mentics.model;

import com.mentics.fpj.E1;
import org.agilewiki.jactor.concurrent.ThreadManager;
import org.agilewiki.jactor.events.JAEventQueue;
import sodium.Behavior;
import sodium.BehaviorSink;
import sodium.Event;
import sodium.EventSink;


public class ModelManager<A> {
    private JAEventQueue<Modify<A>> q;

    private final BehaviorSink<A> _value;

    private final EventSink<Modification<A>> _updates;

    public final Behavior<A> value;

    public final Event<Modification<A>> updates;
    
    public ModelManager(A model, ThreadManager threads) {
        this.value = this._value = new BehaviorSink<>(model);
        this.updates = this._updates = new EventSink<>();
        q = new JAEventQueue<>(threads, true);
        q.setActiveEventProcessor(new BasicEventProcessor<>(q, handler));
    }
    public void send(Modify<A> change) {
        q.putEvent(change);
    }

    private E1<Modify<A>> handler = new E1<Modify<A>>() {
        @Override
        public void e(Modify<A> x) {
            System.out.println("test: " + x);
            Modification<A> mod = x.apply(_value.sample());
            _value.send(mod.newValue());
            _updates.send(mod);
        }
    };
}
