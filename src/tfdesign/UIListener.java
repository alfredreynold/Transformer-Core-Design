/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tfdesign;

import java.util.HashMap;

/**
 *
 * @author Isaac Sudhan
 */
public interface UIListener {
    public void setReqArea(double area);
    public HashMap<Integer,Double> getLengthMap();
    public HashMap<Integer,Double> getBreadthMap();
    public void startDesign();
}
