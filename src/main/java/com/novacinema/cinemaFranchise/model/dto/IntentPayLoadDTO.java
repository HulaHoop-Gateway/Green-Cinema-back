package com.novacinema.cinemaFranchise.model.dto;

import java.util.Map;

// 외부 챗봇(또는 클라이언트)으로부터 전달받은 사용자 의도(Intent)와 데이터를 담는 전송 객체
// 서버에서 요청의 목적을 파악하고 적절한 서비스 로직으로 라우팅하기 위해 사용된다
public class IntentPayLoadDTO {
    // 사용자의 구체적인 요청 의도 (예: "movie_booking_step1", "movie_cancel")
    private String intent;
    
    // 해당 의도를 처리하는 데 필요한 부가적인 파라미터나 상태 데이터를 담는 맵
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
