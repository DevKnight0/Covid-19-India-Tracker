package com.aankik.covid19indiastatus;

import java.io.Serializable;

class StateDataModel implements Serializable {
    private String state;
    private String confirmed;
    private String active;
    private String deceased;
    private String newConfirmed;
    private String newRecovered;
    private String newDeceased;
    private String lastupdated;
    private String recovered;

    public StateDataModel(String state, String confirmed, String active, String deceased, String newConfirmed, String newRecovered, String newDeceased, String lastupdate, String recovered) {
        this.state = state;
        this.confirmed = confirmed;
        this.active = active;
        this.deceased = deceased;
        this.newConfirmed = newConfirmed;
        this.newRecovered = newRecovered;
        this.newDeceased = newDeceased;
        this.lastupdated = lastupdate;
        this.recovered = recovered;
    }

    public String getState() {
        return state;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getActive() {
        return active;
    }

    public String getDeceased() {
        return deceased;
    }

    public String getNewConfirmed() {
        return newConfirmed;
    }

    public String getNewRecovered() {
        return newRecovered;
    }

    public String getNewDeceased() {
        return newDeceased;
    }

    public String getLastupdate() {
        return lastupdated;
    }

    public String getRecovered() {
        return recovered;
    }

    @Override
    public String toString() {
        return "StateDataModel{" +
                "state='" + state + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", active='" + active + '\'' +
                ", deceased='" + deceased + '\'' +
                ", newConfirmed='" + newConfirmed + '\'' +
                ", newRecovered='" + newRecovered + '\'' +
                ", newDeceased='" + newDeceased + '\'' +
                ", lastupdated='" + lastupdated + '\'' +
                ", recovered='" + recovered + '\'' +
                '}';
    }
}
