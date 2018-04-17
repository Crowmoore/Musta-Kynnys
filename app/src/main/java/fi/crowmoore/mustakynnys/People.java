package fi.crowmoore.mustakynnys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class People {

    private List<Person> peopleList;

    public void initializeData(JSONArray people) {
        peopleList = new ArrayList<>();
        for(int i = 0; i < people.length(); i++) {
            addPersonToList(people, i);
        }
    }

    private void addPersonToList(JSONArray people, int id) {
        try {
            JSONObject person = people.getJSONObject(id);
            peopleList.add(new Person (person.getString("firstName"),
                                       person.getString("surname"),
                                       person.getString("imageUrl")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }
}
