package Modules;

import java.time.LocalDateTime;

public class Sesion {
    private boolean loggedIn;
    private LocalDateTime date;

    public Sesion() {
        this.loggedIn = false;
        this.date = LocalDateTime.now();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login() {
        this.loggedIn = true;
    }

    public void logout() {
        this.loggedIn = false;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

