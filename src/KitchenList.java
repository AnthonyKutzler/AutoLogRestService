import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gob on 6/18/17.
 */
//@XmlRootElement
@XmlSeeAlso(Kitchen.class)
public class KitchenList implements Serializable{
    private List<Kitchen> kitchens;

    public KitchenList(){
    }
    public KitchenList(@Nullable String searchBy, @Nullable String whereEquals){
        if(searchBy != null && whereEquals != null){
            try {
                kitchens = new DatabaseHandler().getKitchensInformationWhere(searchBy, whereEquals);
            }catch(SQLException e){
                e.printStackTrace();
                }
        }else{
            try {
                kitchens = new DatabaseHandler().getKitchensInformation();
            }catch(SQLException e){
                e.printStackTrace();
                }
        }
    }

    //@XmlElement
    public List<Kitchen> getKitchens(){
        return kitchens;
    }

    public void addKitchen(Kitchen kitchen){
        kitchens.add(kitchen);
    }
    public void clear(){
        kitchens.clear();
    }

}
