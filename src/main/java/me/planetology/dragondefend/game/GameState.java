package me.planetology.dragondefend.game;

public enum GameState {

    JOIN(true),
    STARTED(false),
    FINISHED(false);

    private boolean join;

    GameState(boolean join) {
        this.join = join;
    }

    public boolean canJoin() {
        return join;
    }
}
