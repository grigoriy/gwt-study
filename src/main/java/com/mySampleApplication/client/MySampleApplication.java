package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sencha.gxt.theme.blue.client.window.BlueWindowAppearance;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.form.FormPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Style.Unit.PX);
        HTML headerHtml = new HTML(
                "<div id='my-main-header' style='background-color: dodgerblue; height: 50px'>" +
                        "<h1>HEADER</h1>" +
                        "</div>");
        VerticalPanel headerPanel = new VerticalPanel();
        headerPanel.setHeight("60px");
        headerPanel.setWidth("1000px");
        dockLayoutPanel.addNorth(headerHtml, 50);
        dockLayoutPanel.addSouth(new HTML(
                "<div id='my-main-footer' style='background-color: dodgerblue; height: 50px'>" +
                        "<h1>FOOTER</h1>" +
                        "</div>"), 50);

        TabPanel tabPanel = new TabPanel();
        tabPanel.setTabScroll(true);
        tabPanel.setAnimScroll(true);

        final Window fileUploadWindow = new Window(new BlueWindowAppearance());
        fileUploadWindow.setHeadingText("File Upload Window");

        final com.sencha.gxt.widget.core.client.form.FormPanel fileUploadForm = new FormPanel();
        VerticalPanel fileUploadFormVerticalPanel = new VerticalPanel();
        fileUploadForm.setWidget(fileUploadFormVerticalPanel);

        final FileUpload fileUploadField = new FileUpload();
        fileUploadField.setName("uploadFormElement");
        fileUploadFormVerticalPanel.add(fileUploadField);
        fileUploadFormVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        fileUploadFormVerticalPanel.add(new Button("Submit", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fileUploadWindow.hide();
            }
        }));
        fileUploadWindow.add(fileUploadForm);

        final Label label = new Label();
        final Button firstTabButton = new Button("Hello, World!");
        firstTabButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
                    MySampleApplicationService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
                } else {
                    label.setText("");
                }
            }
        });
        final Button fileUploadButton = new Button("Upload file", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fileUploadWindow.center();
                fileUploadWindow.show();
            }
        });

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(firstTabButton);
        verticalPanel.add(label);
        verticalPanel.add(fileUploadButton);
        tabPanel.add(verticalPanel, new TabItemConfig("Button Tab", false));
        tabPanel.add(new Label("Tab 2 Content"), new TabItemConfig("Content Tab", true));

        dockLayoutPanel.add(tabPanel);

        Viewport viewport = new Viewport();
        viewport.setWidget(dockLayoutPanel.asWidget());

        // Attach the LayoutPanel to the RootLayoutPanel. The latter will listen for
        // resize events on the window to ensure that its children are informed of
        // possible size changes.
        RootPanel.get().add(viewport);
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
