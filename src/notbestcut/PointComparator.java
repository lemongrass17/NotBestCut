/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notbestcut;

import java.awt.*;
import java.util.Comparator;

/**
 * Created by Наташа on 07.05.2018.
 */
public class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
        if (p1.getX() > p2.getX())
            return 1;
        else if (p1.getX() == p2.getX()) {
            if (p1.getY() > p2.getY())
                return 1;
            else
                return -1;
        }
        else
            return -1;
    }
}
