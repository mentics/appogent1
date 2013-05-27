package com.mentics.javafx;

import javafx.beans.property.ObjectPropertyBase;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class KeyEventProperty extends ObjectPropertyBase<KeyCode> {
    private Node n;


    public KeyEventProperty(final Node n) {
        this.n = n;
        if (n instanceof Pane) {
            n.setFocusTraversable(true);
            n.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent arg0) {
                    n.requestFocus();
                }
            });
        }
        EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ev) {
                System.out.println("on key pressed: "+ev.getCode());
                setValue(ev.getCode());
            }
        };
        n.addEventHandler(KeyEvent.KEY_PRESSED, handler);
        n.addEventHandler(KeyEvent.KEY_RELEASED, handler);
    }

    @Override
    public Object getBean() {
        return n;
    }

    @Override
    public String getName() {
        return "keyPressed";
    }
}
