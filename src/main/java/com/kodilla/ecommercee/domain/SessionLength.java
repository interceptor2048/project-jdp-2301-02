package com.kodilla.ecommercee.domain;

public enum SessionLength {
    ONE_HOUR(6000000L);
    private long sessionLength;
    SessionLength(long sessionLength) {
        this.sessionLength = sessionLength;
    }

    public long getSessionLength() {
        return sessionLength;
    }

}
