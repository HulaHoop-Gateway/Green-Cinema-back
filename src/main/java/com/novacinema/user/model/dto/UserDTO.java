package com.novacinema.user.model.dto;
public class UserDTO {
    private String memberCode;
    private String memberName;
    private String id;
    private String password;
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
