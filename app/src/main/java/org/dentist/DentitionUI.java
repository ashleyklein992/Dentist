package org.dentist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jahja Trifunovic on 7/16/2018.
 */

public class DentitionUI extends View {
    private static final int[] dentitionIds = new int[]{R.drawable.dent_11, R.drawable.dent_12, R.drawable.dent_13, R.drawable.dent_14, R.drawable.dent_15, R.drawable.dent_16, R.drawable.dent_17, R.drawable.dent_18, R.drawable.dent_41, R.drawable.dent_42, R.drawable.dent_43, R.drawable.dent_44, R.drawable.dent_45, R.drawable.dent_46, R.drawable.dent_47, R.drawable.dent_48, R.drawable.dent_21, R.drawable.dent_22, R.drawable.dent_23, R.drawable.dent_24, R.drawable.dent_25, R.drawable.dent_26, R.drawable.dent_27, R.drawable.dent_28, R.drawable.dent_31, R.drawable.dent_32, R.drawable.dent_33, R.drawable.dent_34, R.drawable.dent_35, R.drawable.dent_36, R.drawable.dent_37, R.drawable.dent_38};
    private static final float[][] dentitionNumberPositions = new float[][]{new float[]{45.31f, 10.84f}, new float[]{38.67f, 12.89f}, new float[]{33.67f, 15.96f}, new float[]{29.08f, 19.86f}, new float[]{26.17f, 24.74f}, new float[]{23.46f, 30.74f}, new float[]{21.79f, 37.57f}, new float[]{21.58f, 45.4f}, new float[]{45.33f, 91.27f}, new float[]{40.25f, 90.43f}, new float[]{36.08f, 87.64f}, new float[]{32.12f, 83.74f}, new float[]{28.17f, 78.44f}, new float[]{24.21f, 71.74f}, new float[]{21.5f, 64.07f}, new float[]{21.29f, 56.26f}, new float[]{54.69f, 10.84f}, new float[]{61.33f, 12.89f}, new float[]{66.33f, 15.96f}, new float[]{70.92f, 19.86f}, new float[]{73.83f, 24.74f}, new float[]{76.54f, 30.74f}, new float[]{78.21f, 37.57f}, new float[]{78.42f, 45.4f}, new float[]{54.67f, 91.27f}, new float[]{59.75f, 90.43f}, new float[]{63.92f, 87.64f}, new float[]{67.88f, 83.74f}, new float[]{71.83f, 78.44f}, new float[]{75.79f, 71.74f}, new float[]{78.5f, 64.07f}, new float[]{78.71f, 56.26f}};
    private static final int[] dentitionNumbers = new int[]{11, 12, 13, 14, 15, 16, 17, 18, 41, 42, 43, 44, 45, 46, 47, 48, 21, 22, 23, 24, 25, 26, 27, 28, 31, 32, 33, 34, 35, 36, 37, 38};
    private static final float[][] dentitions = new float[][]{new float[]{43.96f, 4.27f, 12.08f, 8.53f}, new float[]{33.55f, 6.5f, 11.67f, 7.69f}, new float[]{27.5f, 10.42f, 9.58f, 7.97f}, new float[]{20.92f, 16.29f, 12.29f, 7.97f}, new float[]{16.57f, 22.66f, 13.13f, 8.67f}, new float[]{13.65f, 30.49f, 13.13f, 8.39f}, new float[]{11.15f, 38.11f, 13.54f, 8.53f}, new float[]{10.42f, 46.15f, 13.33f, 9.23f}, new float[]{45.52f, 96.36f, 9.79f, 6.43f}, new float[]{37.4f, 95.73f, 8.96f, 6.29f}, new float[]{30.73f, 92.94f, 10.63f, 6.57f}, new float[]{25.42f, 87.9f, 12.5f, 9.37f}, new float[]{18.96f, 80.91f, 12.5f, 9.37f}, new float[]{14.27f, 73.0f, 13.13f, 10.07f}, new float[]{11.25f, 64.76f, 12.08f, 9.79f}, new float[]{11.25f, 55.52f, 12.5f, 9.51f}, new float[]{56.04f, 4.27f, 12.08f, 8.53f}, new float[]{66.45f, 6.5f, 11.67f, 7.69f}, new float[]{72.5f, 10.42f, 9.58f, 7.97f}, new float[]{79.08f, 16.29f, 12.29f, 7.97f}, new float[]{83.43f, 22.66f, 13.13f, 8.67f}, new float[]{86.35f, 30.49f, 13.13f, 8.39f}, new float[]{88.85f, 38.11f, 13.54f, 8.53f}, new float[]{89.58f, 46.15f, 13.33f, 9.23f}, new float[]{54.48f, 96.36f, 9.79f, 6.43f}, new float[]{62.6f, 95.73f, 8.96f, 6.29f}, new float[]{69.27f, 92.94f, 10.63f, 6.57f}, new float[]{74.58f, 87.9f, 12.5f, 9.37f}, new float[]{81.04f, 80.91f, 12.5f, 9.37f}, new float[]{85.73f, 73.0f, 13.13f, 10.07f}, new float[]{88.75f, 64.76f, 12.08f, 9.79f}, new float[]{88.75f, 55.52f, 12.5f, 9.51f}};
    private SparseIntArray colorCache = new SparseIntArray();
    private SparseIntArray colors;
    private ToothUI currentTooth;
    private Handler handler;
    private Paint linePaint;
    private Paint overallPaint;
    private Resources resources;
    private List<ToothUI> teeth;
    private Rect textBounds = new Rect();

    public interface Handler {
        void onToothClicked(int i);
    }

    public DentitionUI(Context context, AttributeSet set) {
        super(context, set);
        this.resources = context.getResources();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();
        switch (event.getAction()) {
            case 0:
                for (ToothUI tooth : this.teeth) {
                    if (tooth.hitTest(ex, ey)) {
                        tooth.onMousePressed();
                        this.currentTooth = tooth;
                        invalidate();
                        break;
                    }
                }
                invalidate();
            case 1:
                for (ToothUI tooth2 : this.teeth) {
                    if (tooth2.hitTest(ex, ey) && this.handler != null && this.currentTooth == tooth2) {
                        this.handler.onToothClicked(tooth2.getId());
                    }
                    tooth2.onMouseReleased();
                }
                invalidate();
                break;
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        initTeeth();
        this.overallPaint.setColor(getColor(R.color.AppWindowBackground));
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.overallPaint);
        int h = (int) ((50.77d * ((double) getHeight())) / 100.0d);
        canvas.drawLine(0.0f, (float) h, (float) getWidth(), (float) h, this.linePaint);
        canvas.drawLine((float) (getWidth() / 2), 0.0f, (float) (getWidth() / 2), (float) getHeight(), this.linePaint);
        for (ToothUI tooth : this.teeth) {
            if (this.colors != null && this.colors.indexOfKey(tooth.getId()) >= 0) {
                int color = this.colors.get(tooth.getId());
                if (color == 0) {
                    color = getColor(R.color.ToothColorGray);
                } else if (color == 1) {
                    color = getColor(R.color.ToothColorYellow);
                } else if (color == 2) {
                    color = getColor(R.color.ToothColorRed);
                } else if (color == 3) {
                    color = getColor(R.color.ToothColorGreen);
                } else if (color == 4) {
                    color = getColor(R.color.ToothColorBlue);
                } else if (color == 5) {
                    color = getColor(R.color.ToothColorOrange);
                } else if (color == 6) {
                    color = getColor(R.color.ToothColorMarine);
                } else if (color == 7) {
                    color = getColor(R.color.ToothColorPink);
                } else if (color == 8) {
                    color = getColor(R.color.ToothColorPurple);
                } else {
                    color = -1;
                }
                tooth.setColor(color);
            }
            tooth.draw(canvas, this.overallPaint);
        }
        this.overallPaint.setColor(getColor(R.color.dentitionTextColor));
        int parentWidth = getWidth();
        int parentHeight = getHeight();
        for (int i = 0; i < dentitionNumberPositions.length; i++) {
            String text = String.valueOf(dentitionNumbers[i]);
            int x = (int) ((dentitionNumberPositions[i][0] * ((float) parentWidth)) / 100.0f);
            int y = (int) ((dentitionNumberPositions[i][1] * ((float) parentHeight)) / 100.0f);
            this.overallPaint.getTextBounds(text, 0, text.length(), this.textBounds);
            canvas.drawText(text, (float) (x - (this.textBounds.width() / 2)), (float) ((this.textBounds.height() / 2) + y), this.overallPaint);
        }
    }

    private void initPaint() {
        if (this.overallPaint == null) {
            this.overallPaint = new Paint(1);
            this.overallPaint.setAntiAlias(true);
            this.overallPaint.setFilterBitmap(true);
            this.overallPaint.setTextSize(getResources().getDimension(R.dimen.activity_dentition_text_size));
        }
        if (this.linePaint == null) {
            this.linePaint = new Paint(1);
            this.linePaint.setAntiAlias(true);
            this.linePaint.setFilterBitmap(true);
            this.linePaint.setColor(getColor(R.color.dentitionSeparators));
            this.linePaint.setStyle(Paint.Style.STROKE);
            this.linePaint.setPathEffect(new DashPathEffect(new float[]{10.0f, 10.0f}, 0.0f));
        }
    }

    private int getColor(int colorId) {
        if (this.colorCache.get(colorId) == 0) {
            this.colorCache.put(colorId, this.resources.getColor(colorId));
        }
        return this.colorCache.get(colorId);
    }

    private Bitmap getBitmap(int drawableId) {
        return ((BitmapDrawable) this.resources.getDrawable(drawableId)).getBitmap();
    }

    private void initTeeth() {
        int parentWidth = getWidth();
        int parentHeight = getHeight();
        if (this.teeth == null) {
            this.teeth = new ArrayList();
            for (int i = 0; i < dentitionIds.length; i++) {
                int w = (int) ((dentitions[i][2] * ((float) parentWidth)) / 100.0f);
                int h = (int) ((dentitions[i][3] * ((float) parentHeight)) / 100.0f);
                this.teeth.add(new ToothUI(dentitionNumbers[i], Bitmap.createScaledBitmap(getBitmap(dentitionIds[i]), w, h, true), ((int) ((dentitions[i][0] * ((float) parentWidth)) / 100.0f)) - (w / 2), ((int) ((dentitions[i][1] * ((float) parentHeight)) / 100.0f)) - (h / 2)));
            }
        }
    }

    public void setColors(SparseIntArray colors) {
        this.colors = colors;
        postInvalidate();
    }
}
