package Objects;

import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gob on 6/16/17.
 */

@XmlRootElement()
@XmlType(propOrder = {"name", "address", "note", "routeNumber", "stopNumber", "xCordinate", "yCordinate"})
public class Kitchen implements Serializable{
    private String name, address, note;
    private int routeNumber, stopNumber;
    private double xCordinate, yCordinate;


    public Kitchen(){}

    public Kitchen(Object[] objects){
        name = objects[0].toString();
        address = objects[1].toString() + ", " + objects[2].toString() + ", " + objects[3].toString();
        routeNumber = Integer.parseInt(objects[4].toString());
        stopNumber = Integer.parseInt(objects[5].toString());
        xCordinate = Double.parseDouble(objects[6].toString());
        yCordinate = Double.parseDouble(objects[7].toString());
    }
    public Kitchen(List<Object[]> objectList){
        for(Object[] objects : objectList){
            name = objects[1].toString();
            address = objects[2].toString() + ", " + objects[3].toString() + ", " + objects[4].toString();
            routeNumber = Integer.parseInt(objects[5].toString());
            stopNumber = Integer.parseInt(objects[6].toString());
            xCordinate = Double.parseDouble(objects[7].toString());
            yCordinate = Double.parseDouble(objects[8].toString());
        }
    }

    public Kitchen(String name, String address, @Nullable String note, int routeNumber,
                   int stopNumber, double xCordinate, double yCordinate){
        this.name = name;
        this.address = address;
        this.note = note;
        this.routeNumber = routeNumber;
        this.stopNumber = stopNumber;
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }
    public Kitchen(String name, String address){
        this.name = name;
        this.address = address;
    }
    //@XmlElement(name = "name")
    public String getName() {
        return name;
    }
    //@XmlElement(name = "address")
    public String getAddress() {
        return address;
    }
    //@XmlElement(name = "note")
    public String getNote() {
        return note;
    }
    //@XmlElement(name = "routenumber")
    public int getRouteNumber() {
        return routeNumber;
    }
    //@XmlElement(name = "stopnumber")
    public int getStopNumber() {
        return stopNumber;
    }
    //@XmlElement(name = "xCordinate")
    public double getxCordinate() {
        return xCordinate;
    }
    //@XmlElement(name = "yCordinate")
    public double getyCordinate() {
        return yCordinate;
    }
    //@XmlElement
    public void setNote(String note) {
        this.note = note;
    }
    //@XmlElement
    public void setxCordinate(double xCordinate) {
        this.xCordinate = xCordinate;
    }
    //@XmlElement
    public void setyCordinate(double yCordinate) {
        this.yCordinate = yCordinate;
    }
    //@XmlElement
    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }
    //@XmlElement
    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }
    //@XmlElement
    public void setName(String name) {
        this.name = name;
    }
    //@XmlElement
    public void setAddress(String address) {
        this.address = address;
    }
}
