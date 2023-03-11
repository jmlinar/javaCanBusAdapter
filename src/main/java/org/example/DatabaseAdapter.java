package org.example;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

import java.time.Instant;

public class DatabaseAdapter {

    private static final char[] token = ConfigReader.getDatabaseSettings().apikey.toCharArray();
    private static final String org = ConfigReader.getDatabaseSettings().org;
    private static final String bucket = ConfigReader.getDatabaseSettings().bucket;

    public static void writeToDB(ValueObject objectToSend) {
        try {
            InfluxDBClient influxDBClient = InfluxDBClientFactory.create(ConfigReader.getDatabaseSettings().hostname, token, org, bucket);
            WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
            Point point = Point.measurement("CoE")
                    .addTag("CANID", String.valueOf(objectToSend.configEntry.ID))
                    .addTag("Node", String.valueOf(objectToSend.configEntry.node))
                    .addField(objectToSend.configEntry.name, objectToSend.value)
                    .time(Instant.now().toEpochMilli(), WritePrecision.MS);
            writeApi.writePoint(point);
            influxDBClient.close();
        }catch (Exception e){
            System.out.println("Something went wrong while sending");
            System.out.println(e.getMessage());
        }
    }
}