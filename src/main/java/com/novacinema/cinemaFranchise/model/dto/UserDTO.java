package com.novacinema.cinemaFranchise.model.dto;

public class UserDTO {
    private int userCode;       // 회원코드
    private String name;        // 회원명
    private String id;          // 아이디
    private String password;    // 비밀번호
    private String phone;       // 전화번호
 public UserDTO(){};

 public UserDTO(int userCode, String name, String id, String password, String phone) {
        this.userCode = userCode;
        this.name = name;
        this.id = id;
        this.password = password;
        this.phone = phone;
 }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userCode=" + userCode +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
