package com.meritbank.v1.meritBankV1.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;

  private String username;
    private String password;

	/*
	 * { "token":
	 * "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmFkaSIsImV4cCI6MTYyOTQzMTczOCwiaWF0IjoxNjI4ODI2OTM4fQ.HA09w3K5riSutcCA9XXKcLZtr9vE6pySMBVeDYOu6Alv362uiH37v10MyHdqEJiO_gp2_QTx8txDElvlFpdopA"
	 * }
	 */
    
    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

