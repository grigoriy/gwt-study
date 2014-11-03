package com.mySampleApplication.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 *
 */
public class MyEvent extends GwtEvent<MyEventHandler> {

    @Override
    public Type<MyEventHandler> getAssociatedType() {
        return null;
    }

    @Override
    protected void dispatch(MyEventHandler handler) {

    }
}
