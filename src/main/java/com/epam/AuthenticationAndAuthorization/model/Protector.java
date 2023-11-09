package com.epam.AuthenticationAndAuthorization.model;

import java.time.LocalDateTime;

public class Protector {

    private int attempts;
    private LocalDateTime blockedTimestamp;

    public Protector() {
    }

    public Protector(int attempts, LocalDateTime blockedTimestamp) {
        this.attempts = attempts;
        this.blockedTimestamp = blockedTimestamp;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public LocalDateTime getBlockedTimestamp() {
        return blockedTimestamp;
    }

    public void setBlockedTimestamp(LocalDateTime blockedTimestamp) {
        this.blockedTimestamp = blockedTimestamp;
    }

    @Override
    public String toString() {
        return "Protector{" +
                "attempts=" + attempts +
                ", blockedTimestamp=" + blockedTimestamp +
                '}';
    }
}
