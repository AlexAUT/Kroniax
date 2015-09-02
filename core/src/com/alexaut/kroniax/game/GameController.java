package com.alexaut.kroniax.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameController extends InputAdapter {
    enum State {
        AT_START, RUNNING, CRASHED, FINISHED, PAUSE
    }

    private State mState;
    
    private Stage mStage;

    public GameController() {
        mState = State.AT_START;
        
        mStage = new Stage(new StretchViewport(1280, 720));
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
    
    public void updateViewport(int width, int height) {
        mStage.getViewport().update(width, height);
    }
    
    public void update(float deltaTime) {
        mStage.act(deltaTime);
    }
    
    public void render() {
        mStage.draw();
    }
    
    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        if(mState == State.AT_START)
            mState = State.RUNNING;
        System.out.println("Here");
        return false;
    }
    
    @Override
    public boolean keyUp (int keycode) {
        System.out.println("KEY");
        return false;
     }
}
