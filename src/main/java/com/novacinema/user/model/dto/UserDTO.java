package com.novacinema.user.model.dto;
// 사용자(회원) 정보를 담아 계층 간 데이터를 전달하는 데이터 전송 객체
// 데이터베이스의 T_Member 테이블 구조와 매핑되어 로그인 및 사용자 조회 시 활용된다
public class UserDTO {
    // 내부 시스템에서 사용자를 식별하는 고유 코드
    private String memberCode;
    
    // 회원 이름
    private String memberName;
    
    // 로그인 아이디
    private String id;
    
    // 비밀번호
    private String password;
    
    // 핸드폰 번호
    private String phoneNumber;

    public UserDTO() {}

    public UserDTO(String memberCode, String memberName, String id, String password, String phoneNumber) {
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "MemberInfoDTO{" +
                "memberCode=" + memberCode +
                ", memberName='" + memberName + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
