package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class ConfigReader {
    private static ArrayList<ConfigEntry> configEntries;
    private static DatabaseSettings databaseSettings;
    private static String path;

    private static void readConfig() {
        try {
            System.out.println("Reading Config");
            configEntries = new ArrayList<>();
            JSONParser parser = new JSONParser();
            JSONObject loadedJsonFile = (JSONObject) parser.parse(new FileReader(getPath()));
            JSONObject JSONDatabaseSettingObject = (JSONObject) loadedJsonFile.get("databaseSettings");
            JSONArray channels = (JSONArray) loadedJsonFile.get("channels");
            databaseSettings = new DatabaseSettings((String) JSONDatabaseSettingObject.get("hostname"), (String) JSONDatabaseSettingObject.get("apikey"), (String) JSONDatabaseSettingObject.get("org"), (String) JSONDatabaseSettingObject.get("bucket"));
            System.out.println("Loaded Database Settings with values: " + databaseSettings);
            for (Object channel : channels) {
                JSONObject currentJsonObject = (JSONObject) channel;
                System.out.println("Loading Config with ID : " + currentJsonObject.get("id"));
                configEntries.add(new ConfigEntry(
                        (long) currentJsonObject.get("id"),
                        (String) currentJsonObject.get("name"),
                        (long) currentJsonObject.get("unit"),
                        (long) currentJsonObject.get("node")
                ));
            }

            System.out.println("Config Loaded successfully");
        } catch (Exception e) {
            System.out.println("Please make sure you have saved a config file under " + getPath() + " or, if the path is null, define a System-variable PATH_TO_CONFIG_FILE that points to config file");
            System.out.println("Make sure the Datatype in the Config JSON are correct. ID:NUMBER,NAME:STRING,UNIT:NUMBER,NODE:NUMBER");
            throw new RuntimeException(e);
        }


    }

    public static ArrayList<ConfigEntry> getConfig() {
        if (configEntries != null) {
            return configEntries;
        }
        readConfig();
        return getConfig();
    }

    public static DatabaseSettings getDatabaseSettings() {
        if (configEntries != null) {
            return databaseSettings;
        }
        readConfig();
        return getDatabaseSettings();
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        ConfigReader.path = path;
    }
}