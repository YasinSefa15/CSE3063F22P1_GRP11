package iteration2.Models;

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
