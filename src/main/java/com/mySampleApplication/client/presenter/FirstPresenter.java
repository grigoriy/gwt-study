package com.mySampleApplication.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mySampleApplication.client.MyServiceAsync;
import com.mySampleApplication.client.event.MyEvent;

/**
 *
 */
public class FirstPresenter implements Presenter {

    private final Display view;
    private final HandlerManager eventBus;
    private final MyServiceAsync rpcService;

    public FirstPresenter(Display view, HandlerManager eventBus, MyServiceAsync rpcService) {
        this.view = view;
        this.eventBus = eventBus;
        this.rpcService = rpcService;
    }

    @Override
    public void go(HasWidgets containter) {
        bind();
        containter.clear();
        containter.add(view.asWidget());
    }

    public void bind() {
        view.getMyButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new MyEvent());
            }
        });
    }

    public interface Display {
        Button getMyButton();
        Widget asWidget();
    }
}
