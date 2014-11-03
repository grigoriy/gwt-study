package com.mySampleApplication.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mySampleApplication.client.presenter.FirstPresenter;

/**
 *
 */
public class FirstView extends Composite implements FirstPresenter.Display {

    Button myButton;

    public FirstView() {
        VerticalPanel verticalPanel = new VerticalPanel();
        initWidget(verticalPanel);

        myButton = new Button("My Button");
        verticalPanel.add(myButton);
    }

    @Override
    public Button getMyButton() {
        return myButton;
    }
}
