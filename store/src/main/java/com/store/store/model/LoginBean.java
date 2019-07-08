package com.store.store.model;

public class LoginBean {

    private String user;
    private String pssw;
    private String newPssw;

    public String getNewPssw() {
        return newPssw;
    }

    public void setNewPssw(String newPssw) {
        this.newPssw = newPssw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPssw() {
        return pssw;
    }

    public void setPssw(String pssw) {
        this.pssw = pssw;
    }
}
