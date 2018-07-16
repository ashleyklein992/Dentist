package org.dentist;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;

/**
 * Created by Jahja Trifunovic on 7/16/2018.
 */

public class ToothUI {
    private int color;
    private boolean isPressed = false;
    private Bitmap normal;
    private Bitmap original;
    private Bitmap pressed;
    private int toothId;
    private int f53x;
    private int f54y;

    public ToothUI(int toothId, Bitmap normal, int x, int y) {
        this.toothId = toothId;
        this.original = normal;
        this.normal = cloneTooth(-1);
        this.pressed = cloneTooth(Color.rgb(54, 158, 185));
        this.f53x = x;
        this.f54y = y;
    }

    public void setColor(int color) {
        if (this.color != color) {
            this.normal = cloneTooth(color);
        }
        this.color = color;
    }

    private Bitmap cloneTooth(int color) {
        Bitmap bitmap = this.original.copy(this.original.getConfig(), true);
        int[] pixels = new int[(bitmap.getHeight() * bitmap.getWidth())];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i = 0; i < pixels.length; i++) {
            if (Color.alpha(pixels[i]) > 0) {
                int r = Color.red(pixels[i]);
                int g = Color.green(pixels[i]);
                int b = Color.blue(pixels[i]);
                if (r >= 51 || g >= 51 || b >= 51) {
                    pixels[i] = color;
                } else {
                    pixels[i] = ViewCompat.MEASURED_STATE_MASK;
                }
            }
        }
        bitmap.setPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return bitmap;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.isPressed ? this.pressed : this.normal, (float) this.f53x, (float) this.f54y, paint);
    }

    public boolean hitTest(float x, float y) {
        return x >= ((float) this.f53x) && y >= ((float) this.f54y) && x <= ((float) (this.f53x + this.normal.getWidth())) && y <= ((float) (this.f54y + this.normal.getHeight()));
    }

    public void onMousePressed() {
        this.isPressed = true;
    }

    public void onMouseReleased() {
        this.isPressed = false;
    }

    public int getId() {
        return this.toothId;
    }
}

