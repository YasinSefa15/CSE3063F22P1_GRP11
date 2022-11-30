package iteration2.Models;

import java.util.Date;
import org.json.JSONObject;


public abstract class Model {
    private Date created_at;
    private Date updated_at;

    public abstract JSONObject toJson();

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
