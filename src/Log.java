import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Time;
import java.util.Date;

/**
 * Created by gob on 6/16/17.
 */
@XmlRootElement(name = "logs")
public class Log {
    private String name, userid;
    private int routeNumber;
    private Date date;
    private Time time;

    public Log(Date date, String name, Time time, int routeNumber, String userid){
        this.date = date;
        this.name = name;
        this.time = time;
        this.routeNumber = routeNumber;
        this.userid = userid;
    }
    public Log(){
        date = new Date();
        name = "example";
        time = new Time(1234);
        routeNumber = 0;
        userid = "Driver Initials";
    }

    private void setUserid(String userid) {
        this.userid = userid;
    }

    private void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }
    public void setUserAndRoute(String userid, int routeNumber){
            this.setUserid(userid);
            this.setRouteNumber(routeNumber);
    }
}
