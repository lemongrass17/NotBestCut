/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notbestcut;

import java.util.Comparator;

/**
 * Created by Наташа on 12.05.2018.
 */
public class DetailComparator implements Comparator<Detail> {
    @Override
    public int compare(Detail d1, Detail d2) {
        if (d1.getWidth() < d2.getWidth())
            return 1;
        else if (d1.getWidth() == d2.getWidth()) {
            if (d1.getHeight() < d2.getHeight())
                return 1;
            else if (d1.getHeight() == d2.getHeight())
                return 0;
            else
                return -1;
        }
        else
            return -1;
    }
}
