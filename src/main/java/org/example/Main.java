package org.example;

public class Main {
    interface CallbackInterface {

        void onReceive(int sequence, int node, short[] data);
    }

    public static void main(String[] args) {
        try {
            ConfigReader.setPath(args[0]);
            ConfigReader.getConfig();
            new Receiver().run(5441, Main::convertSequenceToID);
        }catch (Exception e){
            System.out.println("Error occurred, have you set the path to the config file?");
            System.out.println("java <filename> <pathToConfigFile>");
            System.out.println(e.getMessage());
            System.exit(1);
        }


    }

    public static void convertSequenceToID(int sequence, int node, short[] data) {
        int channelID = 0;
        for (int i = 1; i <= 4; i++) {
            if (sequence == 1) {
                channelID = i;
            }
            if (sequence >= 2) {
                channelID = ((sequence - 1) * 4) + i;
            }

            System.out.println("ID: " + channelID + " Data:" + data[i - 1]);
            matchDataToConfig(channelID, node, data[i - 1]);
        }
    }

    public static void matchDataToConfig(int id, int node, short value) {
        ConfigReader.getConfig().forEach((configEntry) -> {
            if (configEntry.ID == id && configEntry.node == node) {
                //Creates wrapper Dataobject and scales the datavalue with its corresponding scaling factor.
                ValueObject objectToSend= new ValueObject(configEntry,value * configEntry.unit_factor);
                DatabaseAdapter.writeToDB(objectToSend);
            }

        });
    }
}