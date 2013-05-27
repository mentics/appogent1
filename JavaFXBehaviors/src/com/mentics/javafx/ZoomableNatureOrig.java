package com.mentics.javafx;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;


public class ZoomableNatureOrig {
    private static double prevDragX;
    private static double prevDragY;


    public static void addTo(final Node n) {
        final Scene scene = n.getScene();
        scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent ev) {
                double prevScale = n.getScaleX();
                double newScale = prevScale * 1.0 + ev.getDeltaY() / 1000.0;
                zoom(n, ev.getSceneX() - scene.getWidth() / 2, ev.getSceneY() - scene.getHeight() / 2, prevScale,
                        newScale);
            }
        });
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                if (ev.getButton() == MouseButton.SECONDARY) {
                    prevDragX = ev.getSceneX();
                    prevDragY = ev.getSceneY();
                }
            }
        });
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                if (ev.getButton() == MouseButton.SECONDARY) {
                    double x = ev.getSceneX();
                    double y = ev.getSceneY();
                    n.setLayoutX(n.getLayoutX() + (x - prevDragX));
                    n.setLayoutY(n.getLayoutY() + (y - prevDragY));
                    prevDragX = x;
                    prevDragY = y;
                }
            }
        });
    }

    public static void zoom(Node n, double pivX, double pivY, double prevScale, double newScale) {
        double offX = n.getLayoutX();
        double offY = n.getLayoutY();

        double fx = (pivX - offX) / prevScale;
        double fy = (pivY - offY) / prevScale;

        double newOffX = pivX - fx * newScale;
        double newOffY = pivY - fy * newScale;

        n.setLayoutX(newOffX);
        n.setLayoutY(newOffY);
        n.setScaleX(newScale);
        n.setScaleY(newScale);
    }
}
