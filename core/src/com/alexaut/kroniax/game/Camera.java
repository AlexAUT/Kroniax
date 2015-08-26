package com.alexaut.kroniax.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public class Camera {
    private OrthographicCamera mCamera;

    Vector2 mCameraOffset;

    public Camera() {
        mCamera = new OrthographicCamera();
        mCameraOffset = new Vector2(300, 0);
    }

    public void update(Vector2 mPlayerPosition) {
        mCamera.position.x = Math.round(mPlayerPosition.x + mCameraOffset.x);
        mCamera.position.y = Math.round(mPlayerPosition.y + mCameraOffset.y);
        mCamera.update();
    }

    public void updateViewport(int width, int height) {
        mCamera.setToOrtho(false, width, height);
        mCamera.update();
    }

    public OrthographicCamera getOrthoCamera() {
        return mCamera;
    }

    public Matrix4 getTransform() {
        return mCamera.combined;
    }
}
