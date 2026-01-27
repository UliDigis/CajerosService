package com.Banco.CajerosService.DTO;

public class LoginResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private long expiresInSeconds;

    private Long userId;
    private Long cuentaId; 
    private String role;

    public LoginResponse() {
    }

    public LoginResponse(String accessToken, long expiresInSeconds, Long userId, Long cuentaId, String role) {
        this.accessToken = accessToken;
        this.expiresInSeconds = expiresInSeconds;
        this.userId = userId;
        this.cuentaId = cuentaId;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresInSeconds() {
        return expiresInSeconds;
    }

    public void setExpiresInSeconds(long expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
