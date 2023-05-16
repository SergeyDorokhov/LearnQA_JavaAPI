package lib;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class DataGenerator {
    public static String getRandomEmail() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        return "lernqa" + timeStamp + "@example.com";
    }

    public static Map<String, String> getRegistrationData() {
        Map<String, String> data = new HashMap<>();
        data.put("email", getRandomEmail());
        data.put("password", "1234");
        data.put("username", "learnqa");
        data.put("firstName", "learnqa");
        data.put("lastName", "learnqa");
        return data;
    }

    public static Map<String, String> getRegistrationData(Map<String, String> nonDefaultData) {
        Map<String, String> defaultData = getRegistrationData();
        Map<String, String> userData = new HashMap<>();
        String[] keys = {"email", "password", "username", "firstName", "lastName"};
        for (String key : keys) {
            if (nonDefaultData.containsKey(key)) {
                userData.put(key, nonDefaultData.get(key));
            } else {
                userData.put(key, defaultData.get(key));
            }
        }
        return userData;
    }
}
