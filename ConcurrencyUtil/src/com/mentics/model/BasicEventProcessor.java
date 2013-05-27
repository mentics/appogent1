package com.mentics.model;

import com.mentics.fpj.E1;
import org.agilewiki.jactor.events.EventProcessor;
import org.agilewiki.jactor.events.JAEventQueue;


final class BasicEventProcessor<A> implements EventProcessor<A> {
    private final JAEventQueue<A> q;
    private E1<A> proc;


    public BasicEventProcessor(JAEventQueue<A> q, E1<A> proc) {
        this.q = q;
        this.proc = proc;
    }

    @Override
    public void processEvent(A event) {
        proc.e(event);
    }

    @Override
    public void haveEvents() {
        q.dispatchEvents();
    }
}