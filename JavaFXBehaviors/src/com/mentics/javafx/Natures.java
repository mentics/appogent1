package com.mentics.javafx;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Natures {
    public static void stayInView(Node n) {
        Scene scene = n.getScene();
        double sceneWidth = scene.getWidth();
        double sceneHeight = scene.getHeight();
    
        Bounds bounds = n.getLayoutBounds();
        double left = bounds.getMinX();
        double top = bounds.getMinY();
        double right = bounds.getMaxX();
        double bottom = bounds.getMaxY();
    
        double curScaleX = n.getScaleX();
        double curScaleY = n.getScaleY();
    
        double minScaleX = sceneWidth / ((right - left));
        double minScaleY = sceneHeight / ((bottom - top));
    
        if (curScaleX < minScaleX) {
            n.setScaleX(minScaleX);
            curScaleX = minScaleX;
        }
        if (curScaleY < minScaleY) {
            n.setScaleY(minScaleY);
            curScaleY = minScaleY;
        }
    
        Point2D topLeft = n.localToScene(left, top);
        double screenLeft = topLeft.getX();
        if (screenLeft > 0) {
            n.setLayoutX(n.getLayoutX() - screenLeft);
        }
    
        double screenTop = topLeft.getY();
        if (screenTop > 0) {
            n.setLayoutY(n.getLayoutY() - screenTop);
        }
    
        Point2D bottomRight = n.localToScene(right, bottom);
        double screenRight = bottomRight.getX();
        if (screenRight < sceneWidth) {
            n.setLayoutX(n.getLayoutX() + (sceneWidth - screenRight));
        }
    
        double screenBottom = bottomRight.getY();
        if (screenBottom < sceneHeight) {
            n.setLayoutY(n.getLayoutY() + (sceneHeight - screenBottom));
        }
    }

    public static void makeMouseFocusable(final Pane pane) {
        pane.setFocusTraversable(true);
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                pane.requestFocus();
            }
        });
    }
}
