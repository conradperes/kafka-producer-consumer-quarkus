package org.acme.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    public String id;//uui
    public String currentstep;
    public Card payload;
    public String sagastatus;
    public String stepstatus;
    public String type;
    public String version;



    public Transaction() {
    }
    @JsonCreator
    public Transaction(
            @JsonProperty("id")String id,
            @JsonProperty("currentStep")String currentstep,
            @JsonProperty("payload")Card payload,
            @JsonProperty("sagaStatus")String sagastatus,
            @JsonProperty("stepStatus")String stepstatus,
            @JsonProperty("type")String type,
            @JsonProperty("version")String version) {
        this.id = id;
        this.currentstep = currentstep;
        this.payload = payload;
        this.sagastatus = sagastatus;
        this.stepstatus = stepstatus;
        this.type = type;
        this.version = version;
    }


    public Transaction update(Transaction transaction){
        if(transaction.id.equals(this.id) && transaction.stepstatus.equals(this.stepstatus) && transaction.sagastatus.equals(this.sagastatus)){
            return transaction;
        }else{
            this.id = transaction.id;
            this.stepstatus = transaction.stepstatus;
            this.sagastatus = transaction.sagastatus;
        }
        return transaction;

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", currentstep='" + currentstep + '\'' +
                ", payload='" + payload + '\'' +
                ", sagastatus='" + sagastatus + '\'' +
                ", stepstatus='" + stepstatus + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}