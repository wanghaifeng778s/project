package com.onemena.widght;

/**
 * Created by 张玉水 on 2016/7/26.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundRectangleImageView extends ImageView {

    private Boolean mIsRound=true;

    public RoundRectangleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundRectangleImageView(Context context) {
        super(context);
        init();
    }

    private final RectF roundRect = new RectF();
    private float rect_adius =  8;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();

    private void init() {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(
                android.graphics.PorterDuff.Mode.SRC_IN));

        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);

        float density = getResources().getDisplayMetrics().density;
        rect_adius = rect_adius * density;

    }

    public void setRectAdius(float adius) {
        rect_adius = adius;
        invalidate();
    }

    public void setRoundImage(Boolean isRoundImage) {
        mIsRound = isRoundImage;

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        if (mIsRound){
            rect_adius=w/2;
        }
        roundRect.set( 0,  0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, rect_adius, rect_adius, zonePaint);
        //
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }

}
