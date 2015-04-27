package ru.nnsu.pacman.client;

class Player {
    private int x;
    private int y;
    private int score;

    public Player() {
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getScore() {
        return this.score;
    }

    void addScore() {
        this.score += 1;
    }

}
