/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mentics.javafx;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

/**
 *
 * @author nordine
 */
public class DraggablePathNature {

    public static void addTo(final Node eventsOn, final Node actOn, final Path path, final int i, final MouseButton button) {
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
                    QuadCurveTo quadCurveTo = (QuadCurveTo) path.getElements().get(i);
                    quadCurveTo.setX(quadCurveTo.getX() + (x - startPoint.getX()));
                    quadCurveTo.setY(quadCurveTo.getY() + (y - startPoint.getY()));
                    quadCurveTo.setControlX(quadCurveTo.getX()+15);
                    quadCurveTo.setControlY(quadCurveTo.getY()+15);
                    startPoint = p;
                    ev.consume();
                }
            }
        });
    }
    /**
     * Because there is only one mouse, there can only been one thing dragging,
     * so static is ok for this.
     */
    public static Point2D startPoint;
}
