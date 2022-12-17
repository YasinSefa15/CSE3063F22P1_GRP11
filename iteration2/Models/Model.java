package iteration2.Models;

import java.util.Date;
import org.json.JSONObject;


public abstract class Model {
    private Date created_at;
    private Date updated_at;

    public abstract JSONObject toJson();

}
