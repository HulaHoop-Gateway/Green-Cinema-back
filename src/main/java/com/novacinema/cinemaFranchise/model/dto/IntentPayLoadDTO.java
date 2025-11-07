package com.novacinema.cinemaFranchise.model.dto;

import java.util.Map;

public class IntentPayLoadDTO {
    private String intent;
    private Map<String, Object> data;

    public IntentPayLoadDTO(String intent, Map<String, Object> data) {
        this.intent = intent;
        this.data = data;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IntentPayLoadDTO{" +
                "intent='" + intent + '\'' +
                ", data=" + data +
                '}';
    }
}
