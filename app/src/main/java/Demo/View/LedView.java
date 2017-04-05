package Demo.View;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import Demo.PaintHelper;


/**
 * Created by 95815 .
 * Date:2017/3/30.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class LedView extends View {


    Handler mHandler;

    Path path1;
    Path path2;
    Path path3;
    Path out_path;

    Region region1;
    Region region2;
    Region region3;
    Region center_region;
    Context mContext;

    Path center_Path;
    Paint mDeafaultPaint;
    Paint mLinePaint;
    Paint mOutLinePaint;


    float lineSize;
    int width;
    int center_x;
    int center_y;


    private Context context;
    private PaintHelper paintHelper;
    private Region globalRegion;

    int mDefaultColor = Color.parseColor("#343f45");
    int mLineDefaultColor = Color.parseColor("#292a2c");
    int mLineFoucsColor = Color.parseColor("#FFA630");


    public LedView(Context context) {
        this(context, null);
    }

    /**
     * @param context 初始化布局文件
     * @param attrs
     */
    public LedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w < h ? w : h;
        center_x = w/2;
        center_y = h/2;
        width *= 0.95;
        lineSize = width / 20;
        paintHelper = new PaintHelper(context, w, h);

        int innerWidth = (int) (width * 0.8);

        int br = innerWidth / 2;   //中心圆的半径
        int sr = innerWidth / 4;   //内圆的半径
        //画外围的线框
        out_path.addCircle(center_x, center_y, br, Path.Direction.CW);
        //画圆
        center_Path.addCircle(center_x, center_y, sr, Path.Direction.CW);
        center_region.setPath(center_Path, globalRegion);
        //画三个灯光档位扇形
        path1 = paintHelper.getNumPath(120f, 30f, br, sr, region1);
        path2 = paintHelper.getNumPath(120f, 150f, br, sr, region2);
        path2 = paintHelper.getNumPath(120f, 270f, br, sr, region3);


    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //在线程中绘图

                //绘制中心圆
                canvas.drawPath(center_Path, mDeafaultPaint);
                mLinePaint.setStrokeWidth(20f);
                mLinePaint.setColor(mLineFoucsColor);
                canvas.drawPath(center_Path, mLinePaint);
                //绘制三个灯档位
                canvas.drawPath(path1, mDeafaultPaint);
                canvas.drawPath(path2, mDeafaultPaint);
                canvas.drawPath(path3, mDeafaultPaint);

                mLinePaint.setStrokeWidth(lineSize);
               mLinePaint.setColor(mDefaultColor);
            //    canvas.drawPath(out_path,mLinePaint);


    }

    private void init() {
        path1 = new Path();
        path2 = new Path();
        path3 = new Path();
        center_Path = new Path();
        out_path = new Path();

        region1 = new Region();
        region2 = new Region();
        region3 = new Region();
        globalRegion = new Region();
        center_region = new Region();
         mHandler = new Handler();


        mDeafaultPaint = new Paint();
        mDeafaultPaint.setAntiAlias(true);
        mDeafaultPaint.setStyle(Paint.Style.FILL);
        mDeafaultPaint.setColor(mDefaultColor);

        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(100f);

        mOutLinePaint = new Paint();
        mOutLinePaint.setStrokeWidth(10f);
        mOutLinePaint.setStyle(Paint.Style.STROKE);
        mOutLinePaint.setAntiAlias(true);


    }


}
