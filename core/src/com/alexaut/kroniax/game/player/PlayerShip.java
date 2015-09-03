package com.alexaut.kroniax.game.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class PlayerShip {

    private float[] mPoints;
    private Vector2 mCollisionPoints[];
    private Vector2 mOldPosOfRightPoint;

    public PlayerShip() {
        mPoints = new float[8];
        mCollisionPoints = new Vector2[3];
        mCollisionPoints[0] = new Vector2();
        mCollisionPoints[1] = new Vector2();
        mCollisionPoints[2] = new Vector2();
        mOldPosOfRightPoint = new Vector2();

        mOldPosOfRightPoint.set(mCollisionPoints[0]);
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.triangle(mPoints[0], mPoints[1], mPoints[2], mPoints[3], mPoints[6], mPoints[7]);
        renderer.triangle(mPoints[0], mPoints[1], mPoints[4], mPoints[5], mPoints[6], mPoints[7]);
    }

    public void updatePoints(Player player) {
        final float cos = (float) Math.cos(player.getAngle());
        final float sin = (float) Math.sin(player.getAngle());

        final float halfXSize = player.getSize().x / 2.f;
        final float halfYSize = player.getSize().y / 2.f;

        // Round the position, because we round the camera too
        // Otherwise the ship would jump pixels and not fly smooth
        final float pos_x = Math.round(player.getPosition().x);
        final float pos_y = Math.round(player.getPosition().y);

        // Calculate right point
        mPoints[0] = pos_x + (halfXSize * cos);
        mPoints[1] = pos_y + (halfXSize * sin);

        // Calculate left upper point
        mPoints[2] = pos_x - (halfXSize * cos) - (halfYSize * sin);
        mPoints[3] = pos_y + (halfYSize * cos) - (halfXSize * sin);

        // Calculate left bottom point
        mPoints[4] = pos_x - (halfXSize * cos) + (halfYSize * sin);
        mPoints[5] = pos_y - (halfYSize * cos) - (halfXSize * sin);

        // Optical point middle left
        mPoints[6] = pos_x - (halfXSize * cos / 2.f);
        mPoints[7] = pos_y - (halfXSize * sin / 2.f);

        // Update Collision points
        mOldPosOfRightPoint.set(mCollisionPoints[0]);
        mCollisionPoints[0].set(mPoints[0], mPoints[1]);
        mCollisionPoints[1].set(mPoints[2], mPoints[3]);
        mCollisionPoints[2].set(mPoints[4], mPoints[5]);
    }

    public void resetAfterCheckPoint() {
        mOldPosOfRightPoint.set(mCollisionPoints[0]);
    }

    public Vector2[] getCollisionPoints() {
        return mCollisionPoints;
    }

    public Vector2 getOldRightPosition() {
        return mOldPosOfRightPoint;
    }
}
