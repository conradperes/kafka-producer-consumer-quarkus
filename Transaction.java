package org.acme;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.Serializable;

public class Transaction {

    public String id;//uui
    public String currentstep;
    public String payload;
    public String sagastatus;
    public String stepstatus;
    public String type;
    public String version;

    public Transaction(String id, String currentstep, String payload, String sagastatus, String stepstatus, String type, String version) {
        this.id = id;
        this.currentstep = currentstep;
        this.payload = payload;
        this.sagastatus = sagastatus;
        this.stepstatus = stepstatus;
        this.type = type;
        this.version = version;
    }

    public Transaction() {
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", currentstep='" + currentstep + '\'' +
                ", oayload='" + payload + '\'' +
                ", sagastatus='" + sagastatus + '\'' +
                ", stepstatus='" + stepstatus + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}
