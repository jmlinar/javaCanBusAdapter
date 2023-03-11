package org.example;

public class DatabaseSettings {
    String hostname;
    String apikey;
    String org;
    String bucket;

    public DatabaseSettings(String hostname, String apikey, String org, String bucket) {
        this.hostname = hostname;
        this.apikey = apikey;
        this.org = org;
        this.bucket = bucket;
    }

    @Override
    public String toString() {
        return "databaseSeeings{" +
                "hostname='" + hostname + '\'' +
                ", apikey='" + apikey + '\'' +
                ", org='" + org + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
