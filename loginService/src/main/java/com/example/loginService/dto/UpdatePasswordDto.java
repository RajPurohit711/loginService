package com.example.loginService.dto;

public class UpdatePasswordDto {
    private String enOtp;
    private Long otp;
    private String Password;
    private String confirmPassword;
    private String email;

    public String getEnOtp() {
        return enOtp;
    }

    public void setEnOtp(String enOtp) {
        this.enOtp = enOtp;
    }

    public Long getOtp() {
        return otp;
    }

    public void setOtp(Long otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
