// Creates objects that can be converted to JSON objects.

package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
