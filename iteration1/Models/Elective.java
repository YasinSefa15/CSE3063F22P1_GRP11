package iteration1.Models;

import org.json.JSONObject;

public class Elective extends Course{
    private ElectiveType type;

    public ElectiveType getType() {
        return type;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
