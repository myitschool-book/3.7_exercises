package ru.samsung.itschool.funnybirds;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10.11.2015.
 */
public class Sprite {
    private Bitmap bitmap;

    private List<Rect> frames;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame;
    private double frameTime;
    private double timeForCurrentFrame;

    private double x;
    private double y;

    private double velocityX;
    private double velocityY;

    private int padding;

    public Sprite(double x,
                  double y,
                  double velocityX,
                  double velocityY,
                  Rect initialFrame,
                  Bitmap bitmap)     {

        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

        this.bitmap = bitmap;

        this.frames = new ArrayList<Rect>();
        this.frames.add(initialFrame);

        this.bitmap = bitmap;

        this.timeForCurrentFrame = 0.0;
        this.frameTime = 25;
        this.currentFrame = 0;

        this.frameWidth = initialFrame.width();
        this.frameHeight = initialFrame.height();

        this.padding = 20;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public double getVx() {
        return velocityX;
    }

    public void setVx(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVy() {
        return velocityY;
    }

    public void setVy(double velocityY) {
        this.velocityY = velocityY;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame%frames.size();
    }

    public double getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(double frameTime) {
        this.frameTime = Math.abs(frameTime);;
    }

    public double getTimeForCurrentFrame() {
        return timeForCurrentFrame;
    }

    public void setTimeForCurrentFrame(double timeForCurrentFrame) {
        this.timeForCurrentFrame = Math.abs(timeForCurrentFrame);
    }

    public void addFrame (Rect frame) {
        frames.add(frame);
    }

    public int getFramesCount () {
        return frames.size();
    }


    public void update (int ms) {
        timeForCurrentFrame += ms;

        if (timeForCurrentFrame >= frameTime) {
            currentFrame = (currentFrame + 1) % frames.size();
            timeForCurrentFrame = timeForCurrentFrame - frameTime;
        }

        x = x + velocityX * ms/1000.0;
        y = y + velocityY * ms/1000.0;
    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();

        Rect destination = new Rect((int)x, (int)y, (int)(x + frameWidth), (int)(y + frameHeight));
        canvas.drawBitmap(bitmap, frames.get(currentFrame), destination,  p);
    }

    public Rect getBoundingBoxRect () {
        return new Rect((int)x+padding,
                (int)y+padding,
                (int)(x + frameWidth - 2 *padding),
                (int)(y + frameHeight - 2* padding));
    }


    public boolean intersect (Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }

}
