/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tfdesign;

import java.awt.EventQueue;
import java.util.HashMap;

/**
 *
 * @author Isaac Sudhan
 */
public class TFDesign implements UIListener{
    private static HashMap<Integer, Double> pm;
    private static HashMap<Integer, Double> qm;
    private static HashMap<Integer, Double> fulQ;
    private static double reqArea;
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //reqArea=34567.0;
        TFDesign tf=new TFDesign();
        //tf.myStart();
        tf.show();
               
    }
    private double foundArea;
    
    private void show(){
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JDFrame.lookNFeel();
                JDFrame f=new JDFrame(TFDesign.this);
                f.setVisible(true);
                
            }
        });
        
    }
    
    @Override
    public void startDesign(){
        double tmpArea=1;
        double dia=0;
        boolean loop=true;
        pm=new HashMap<>();
        qm=new HashMap<>();
        fulQ=new HashMap<>();
        
        loops:
        while(loop){
            for(int t=0;t<90;t++){
                if(tmpArea<reqArea){
                    foundArea=tmpArea;
                    tmpArea=calc(dia, t);
                    
                }else{break loops;}
            }
            dia=dia+0.1;
            
        }
        
        dia=finalD;
        tmpArea=0;
        loops1:
        while(loop){
            for(double t=0;t<90;t=t+0.1){
                if(tmpArea<reqArea){
                    foundArea=tmpArea;
                    tmpArea=calc(dia, t);
                    
                }else{break loops1;}
            }
            dia=dia+1;
            
        }
        
        System.out.println("ReqArea: "+reqArea);
        System.out.println("FinalArea: "+foundArea);
        System.out.println("Final Dia: "+finalD);
        System.out.println("Final Degree: "+finalTeta);
        for(int k=0;k<pm.size();k++){
            System.out.println("Len: "+k+" : "+pm.get(k));
            System.out.println("Bre: "+k+" : "+qm.get(k));
        }
        
                
    }
    
    private double finalD;
    private double finalTeta;
    private double tmp;
    
    private double calc(double d,double deg){
        double rad=Math.toRadians(deg);
        double a=d*Math.cos(rad);
        //a=Math.round(a);
        double b=d*Math.sin(rad);
        //b=Math.round(b);
        double tmpArea=a*b;
        pm.put(0, a);
        qm.put(0, b);
        fulQ.put(0,b);
        double p,q;
        int i=1;
        boolean loop=true;
        loop1:
        while(loop){
            p=a-(i*20);
            //System.out.println("D: "+d+" deg: "+deg+" p: "+p);
            if(p>20){
                double newRad=Math.acos(p/d);
                q=d*Math.sin(newRad);
                //q=Math.round(q);
                fulQ.put(i, q);
                q=q-fulQ.get(i-1);
                if(q<=5){break loop1;}
                pm.put(i, p);
                qm.put(i, q);
                tmpArea=tmpArea+(p*q);
                i++;
            }else{break;}
        }
        finalD=d;
        finalTeta=deg;
        return tmpArea;
    }

    @Override
    public void setReqArea(double area) {
        TFDesign.reqArea=area;
    }

    @Override
    public HashMap<Integer, Double> getLengthMap() {
        return pm;
    }

    @Override
    public HashMap<Integer, Double> getBreadthMap() {
        return qm;
    }
    
    
}