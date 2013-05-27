package com.mentics.javafx;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class DraggableItemNature {
    public static void addTo(final Node eventsOn, final Node actOn, final MouseButton button) {
        eventsOn.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                if (ev.getButton() == button) {
                    startPoint = eventsOn.localToParent(ev.getX(), ev.getY());
                }
            }
        });
        eventsOn.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                if (ev.getButton() == button) {
                    Point2D p = eventsOn.localToParent(ev.getX(), ev.getY());
                    double x = p.getX();
                    double y = p.getY();
                    actOn.setLayoutX(actOn.getLayoutX() + (x - startPoint.getX()));
                    actOn.setLayoutY(actOn.getLayoutY() + (y - startPoint.getY()));
                    startPoint = p;
                    ev.consume();
                }
            }
        });
    }


    /**
     * Because there is only one mouse, there can only been one thing dragging, so static is ok for this.
     */
    public static Point2D startPoint;
}
