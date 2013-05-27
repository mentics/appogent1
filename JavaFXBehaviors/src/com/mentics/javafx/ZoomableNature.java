package com.mentics.javafx;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;


public class ZoomableNature {
    public static void addTo(final Node actOn) {
//        final Scene scene = n.getScene();
        Parent eventsOn = actOn.getParent();
        eventsOn.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent ev) {
                final double prevScaleX = actOn.getScaleX();
                final double newScaleX = prevScaleX * (1.0 + ev.getDeltaY() / 1000.0);
                final double prevScaleY = actOn.getScaleY();
                final double newScaleY = prevScaleY * (1.0 + ev.getDeltaY() / 1000.0);
                zoom(actOn, ev.getSceneX(), ev.getSceneY(), prevScaleX, newScaleX, prevScaleY, newScaleY);
//                Natures.stayInView(actOn);
            }
        });
        DraggableItemNature.addTo(actOn.getParent(), actOn, MouseButton.SECONDARY);
//        eventsOn.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent arg0) {
//                Natures.stayInView(actOn);
//            }
//        });
        // scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
        // @Override
        // public void handle(MouseEvent ev) {
        // if (ev.getButton() == MouseButton.SECONDARY) {
        // System.out.println("zoomable.mousepressed");
        // Natures.startDragX = ev.getSceneX();
        // Natures.startDragY = ev.getSceneY();
        // }
        // }
        // });
        // scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
        // @Override
        // public void handle(MouseEvent ev) {
        // if (ev.getButton() == MouseButton.SECONDARY) {
        // double x = ev.getSceneX();
        // double y = ev.getSceneY();
        // n.setLayoutX(n.getLayoutX() + (x - Natures.startDragX));
        // n.setLayoutY(n.getLayoutY() + (y - Natures.startDragY));
        // System.out.println("zoomable.mousedragged");
        // Natures.startDragX = x;
        // Natures.startDragY = y;
        // Natures.stayInView(n);
        // }
        // }
        // });
    }

    public static void zoom(Node n, double fixedScreenX, double fixedScreenY, double prevScaleX, double newScaleX, double prevScaleY, double newScaleY) {
        Point2D localEvent = n.sceneToLocal(fixedScreenX, fixedScreenY);

        n.setScaleX(newScaleX);
        n.setScaleY(newScaleY);

        Point2D scaledSceneEvent = n.localToScene(localEvent);

        double newX = n.getLayoutX() - (scaledSceneEvent.getX() - fixedScreenX);
        double newY = n.getLayoutY() - (scaledSceneEvent.getY() - fixedScreenY);

        n.setLayoutX(newX);
        n.setLayoutY(newY);
    }
}
