package com.alexaut.kroniax.game;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.game.gamecontrollerscenes.CrashedScene;
import com.alexaut.kroniax.game.gamecontrollerscenes.FinishScene;
import com.alexaut.kroniax.game.gamecontrollerscenes.ModalScene;
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
        AT_START, RUNNING, CRASHED, RESET_TO_CHECKPOINT, FINISHED, PAUSE, BACK_TO_MENU, LOAD_NEXT_LEVEL, SHOW_MODAL
    }

    private State mState;

    private boolean mIgnoreInput;

    final private Matrix4 mIdentityMatrix;

    private Stage mStage;

    private StartScene mStartScene;
    private PauseScene mPauseScene;
    private CrashedScene mCrashedScene;
    private FinishScene mFinishScene;
    private ModalScene mModalScene;

    public GameController(Application app) {
        mState = State.AT_START;

        mIgnoreInput = false;

        mIdentityMatrix = new Matrix4();

        mStage = new Stage(new StretchViewport(1280, 720));

        mStartScene = new StartScene(app);
        mPauseScene = new PauseScene(app);
        mCrashedScene = new CrashedScene(app);
        mFinishScene = new FinishScene(app);
        mModalScene = new ModalScene(app);

        mStage.addActor(mStartScene);
        mStage.addActor(mPauseScene);
        mStage.addActor(mCrashedScene);
        mStage.addActor(mFinishScene);
        mStage.addActor(mModalScene);
    }

    public boolean isRunning() {
        return (mState == State.RUNNING);
    }

    public void setState(State newState) {
        mState = newState;

        if (newState == State.CRASHED) {
            mCrashedScene.clearActions();
            mCrashedScene.addAction(Actions.fadeIn(0.3f));
            ignoreInput(0.3f);
        }

        if (newState == State.FINISHED) {
            mFinishScene.addAction(Actions.fadeIn(0.5f));
            ignoreInput(0.35f);
        }

        if (newState == State.PAUSE) {
            mPauseScene.setColor(1, 1, 1, 1);
        }

        if (newState == State.SHOW_MODAL) {
            ignoreInput(0.2f);
            mModalScene.addAction(Actions.fadeIn(0.3f));
        }
    }

    private void setStateWithDelay(final State newState, float delay) {
        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                mState = newState;
            }
        }, delay);
    }

    public void ignoreInput(float duration) {
        mIgnoreInput = true;

        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                mIgnoreInput = false;
            }
        }, duration);
    }

    public void setModalText(String newText) {
        mModalScene.setText(newText);
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
            shapeRenderer.setColor(0.0f, 0.0f, 0.0f, 0.9f);
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
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK)
            return escapeKey();
        return false;
    }

    private boolean startButton() {
        if (mIgnoreInput)
            return true;

        if (mState == State.AT_START) {
            mStartScene.clearActions();
            mStartScene.addAction(Actions.fadeOut(0.25f));
            setStateWithDelay(State.RUNNING, 0.25f);
        }

        if (mState == State.PAUSE) {
            mPauseScene.addAction(Actions.fadeOut(0.25f));
            setStateWithDelay(State.RUNNING, 0.25f);
        }

        if (mState == State.CRASHED) {
            mCrashedScene.clearActions();
            mCrashedScene.addAction(Actions.fadeOut(0.25f));
            mStartScene.clearActions();
            mStartScene.addAction(Actions.fadeIn(0.5f));
            mState = State.RESET_TO_CHECKPOINT;
        }

        if (mState == State.FINISHED) {
            mFinishScene.clearActions();
            mFinishScene.addAction(Actions.fadeOut(0.5f));
            setStateWithDelay(State.LOAD_NEXT_LEVEL, 0.5f);
        }

        if (mState == State.SHOW_MODAL) {
            mModalScene.clearActions();
            mModalScene.addAction(Actions.fadeOut(0.5f));
            setStateWithDelay(State.RUNNING, 0.5f);
        }

        return true;
    }

    private boolean escapeKey() {
        if (mIgnoreInput)
            return true;

        if (mState == State.RUNNING) {
            mState = State.PAUSE;
            mPauseScene.clearActions();
            mPauseScene.addAction(Actions.fadeIn(0.5f));
        } else {
            mState = State.BACK_TO_MENU;
        }

        return true;
    }
}
