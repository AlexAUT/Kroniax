package com.alexaut.kroniax.game;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.game.gamecontrollerscenes.CrashedScene;
import com.alexaut.kroniax.game.gamecontrollerscenes.FinishScene;
import com.alexaut.kroniax.game.gamecontrollerscenes.PauseScene;
import com.alexaut.kroniax.game.gamecontrollerscenes.StartScene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameController extends InputAdapter {
    public enum State {
        AT_START, RUNNING, CRASHED, RESET_TO_CHECKPOINT, FINISHED, PAUSE, BACK_TO_MENU, LOAD_NEXT_LEVEL
    }

    private State mState;

    private Matrix4 mIdentityMatrix;

    private Stage mStage;

    private StartScene mStartScene;
    private PauseScene mPauseScene;
    private CrashedScene mCrashedScene;
    private FinishScene mFinishScene;

    public GameController(Application app) {
        mState = State.AT_START;

        mIdentityMatrix = new Matrix4();

        mStage = new Stage(new StretchViewport(1280, 720));

        mStartScene = new StartScene(app);
        mPauseScene = new PauseScene(app);
        mCrashedScene = new CrashedScene(app);
        mFinishScene = new FinishScene(app);

        mStage.addActor(mStartScene);
        mStage.addActor(mPauseScene);
        mStage.addActor(mCrashedScene);
        mStage.addActor(mFinishScene);
    }

    public boolean isRunning() {
        return (mState == State.RUNNING);
    }

    public void setState(State newState) {
        mState = newState;

        if (newState == State.CRASHED)
            mCrashedScene.addAction(Actions.fadeIn(0.5f));

        if (newState == State.FINISHED)
            mFinishScene.addAction(Actions.fadeIn(0.5f));
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

    public void render(ShapeRenderer shapeRenderer) {
        if (mState != State.RUNNING) {
            // Draw grey overlay
            shapeRenderer.setProjectionMatrix(mIdentityMatrix);
            shapeRenderer.begin(ShapeType.Filled);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.setColor(0.1f, 0.1f, 0.1f, 0.9f);
            shapeRenderer.rect(-1, -1, 2, 2);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
        mStage.draw();
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return startButton();
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE)
            return startButton();
        if (keycode == Input.Keys.ESCAPE)
            return escapeKey();
        return false;
    }

    private boolean startButton() {
        if (mState == State.AT_START) {
            mStartScene.addAction(Actions.fadeOut(0.25f));
            Timer.schedule(new Timer.Task() {

                @Override
                public void run() {
                    mState = State.RUNNING;
                }
            }, 0.25f);
        }

        if (mState == State.PAUSE) {
            mPauseScene.addAction(Actions.fadeOut(0.25f));
            Timer.schedule(new Timer.Task() {

                @Override
                public void run() {
                    mState = State.RUNNING;
                }
            }, 0.25f);
        }

        if (mState == State.CRASHED) {
            mCrashedScene.addAction(Actions.fadeOut(0.25f));
            mStartScene.addAction(Actions.fadeIn(0.5f));
            mState = State.RESET_TO_CHECKPOINT;
        }

        if (mState == State.FINISHED) {
            mFinishScene.addAction(Actions.fadeOut(0.5f));
            Timer.schedule(new Timer.Task() {

                @Override
                public void run() {
                    mState = State.LOAD_NEXT_LEVEL;
                }
            }, 0.5f);
        }

        return true;
    }

    private boolean escapeKey() {
        if (mState == State.RUNNING) {
            mState = State.PAUSE;
            mPauseScene.addAction(Actions.fadeIn(0.5f));
        } else {
            mState = State.BACK_TO_MENU;
        }

        return true;
    }
}
