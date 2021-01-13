package eg.edu.alexu.csd.oop.mail;

import java.awt.*;
import java.util.ArrayList;
public class Machine implements Observable{
   private   Color defaultColor=Color.green;
   private   Product currentProduct;
   private Color currentColor=defaultColor;
   private  ArrayList<Observer> qin=new ArrayList<Observer>();
   private   LinkedBasedQ qout=new LinkedBasedQ();
   private  volatile Boolean state=false; //The state of the machine (True for Working and false for Waiting)
   private  int workingTime; //random int from 500 to 5000 milliseconds
    private volatile Graphics2D GUIMachine=null;
    private App app;
    //Constructor
    public Machine(){

        workingTime=(int)((Math.random()*((3000-1000+1)))+1000);//Set the working time randomly


    }


    //Setters And Getters
    public void setAppFrame(App app){
        this.app=app;
    }
    public Graphics2D getGUIMachine() {
        return GUIMachine;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void setGUIMachine(Graphics2D GUIMachine) {
        this.GUIMachine = GUIMachine;
    }
    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    public  Product getCurrentProduct() {
        return currentProduct;
    }

    public  synchronized void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
        if(currentProduct!=null){
            this.setState(true);
        }
        else
        this.setState(false);
    }

    public ArrayList<Observer> getQin() {
        return qin;
    }

    public void setQin(ArrayList<Observer> qin) {
        this.qin = qin;
    }

    public LinkedBasedQ getQout() {
        return qout;
    }

    public void setQout(LinkedBasedQ qout) {
        this.qout = qout;
    }

    public  Boolean getState() {

        return state;

    }

    public  void  setState(Boolean state) {
        this.state = state;
        notifyObservers();
        notifyApp();
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }



    @Override
    public void attachObserver(Observer q) {
        this.qin.add(q);
    }

    @Override
    public void detachObserver(Observer q) {
        this.qin.remove(q);
    }
    @Override
    public void notifyObservers() {
        for (Observer o : this.qin){
            o.update(this,this.getState());
        }
    }
    @Override
    public void notifyApp() {
        synchronized (this.app) {
            this.app.update(this, this.state);

        }
    }
}
