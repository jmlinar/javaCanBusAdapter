package org.example;

public class ValueObject {
    ConfigEntry configEntry;
    double value;
    public ValueObject(ConfigEntry configEntry, double value) {
        this.configEntry = configEntry;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueObject{" +
                "configEntry=" + configEntry.toString() +
                ", value=" + value +
                '}';
    }
}
