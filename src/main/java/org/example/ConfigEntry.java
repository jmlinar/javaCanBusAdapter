package org.example;

import java.util.Map;

import static java.util.Map.entry;

public class ConfigEntry {


    public ConfigEntry(long ID, String name, long unit, long node) {
        this.ID = ID;
        this.name = name;
        this.unit = unit;
        this.node = node;
        this.unit_factor = unitFaktors.get((int) unit);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUnit() {
        return unit;
    }

    public void setUnit(long unit) {
        this.unit = unit;
    }

    public double getUnit_factor() {
        return unit_factor;
    }

    public void setUnit_factor(double unit_factor) {
        this.unit_factor = unit_factor;
    }

    public long getNode() {
        return node;
    }

    public void setNode(long node) {
        this.node = node;
    }

    long ID;
    String name;
    long unit;
    double unit_factor;
    long node;


    static Map<Integer, Double> unitFaktors = Map.ofEntries(
            entry(0, 1.0),
            entry(1, 0.1), //temperature
            entry(2, 1.0), //Todo check other scaling factors
            entry(3, 1.0), // l/h
            entry(4, 1.0), //Todo check other scaling factors
            entry(5, 1.0), //Todo check other scaling factors
            entry(6, 1.0), //Todo check other scaling factors
            entry(7, 1.0), //Todo check other scaling factors
            entry(8, 1.0), //Todo check other scaling factors
            entry(9, 0.1), // kW
            entry(10, 1.0), //Todo check other scaling factors
            entry(11, 1.0),  //Todo check other scaling factors
            entry(12, 1.0),
            entry(13, 1.0), //Todo check other scaling factors
            entry(14, 1.0), //Todo check other scaling factors
            entry(15, 1.0), //Todo check other scaling factors
            entry(16, 1.0), //Todo check other scaling factors
            entry(17, 1.0), //Todo check other scaling factors
            entry(18, 1.0), //Todo check other scaling factors
            entry(19, 1.0), //Todo check other scaling factors
            entry(20, 1.0), //Todo check other scaling factors
            entry(21, 0.01),
            entry(22, 0.01), //Netzpumpenspannung
            entry(23, 1.0),  //Digital
            entry(24, 0.1),
            entry(25, 1.0),
            entry(26, 1.0),
            entry(27, 1.0),
            entry(28, 1.0),
            entry(29, 1.0)

    );

}
