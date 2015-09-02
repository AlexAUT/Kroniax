package com.alexaut.kroniax.game;

import com.badlogic.gdx.InputAdapter;

public class GameController extends InputAdapter {
    enum State {
        AT_START, RUNNING, CRASHED, FINISHED, PAUSE
    }

    private State mState;

    public GameController() {
        mState = State.AT_START;
    }

    public boolean isRunning() {
        return (mState == State.RUNNING);
    }
    
    public void setState(State newState) {
        mState = newState;
    }
    
    public State getState() {
        return mState;
    }
    
    public void render() {
        
    }
}
