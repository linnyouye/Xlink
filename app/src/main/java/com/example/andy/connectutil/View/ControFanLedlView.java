package com.example.andy.connectutil.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.andy.connectutil.Helper.PaintHelper;
import com.example.andy.connectutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95815 on 2017/3/14.
 */

public class ControFanLedlView extends View {

    boolean btnState = false;
    int lightNum;
    int outBtnr;


    private int My_h;
    private int My_w;
    private int My_oldh;
    private int My_oldw;


    private Context context;

    private Region globalRegion;

    private Path outCir_p;

    private Region center_re;
    private Path center_p;
    private List<Path> mPathList;
    private List<Region> mRegionList;
    private List<Path> mOutArcList;

    private int geerNum = 4;   //档位的默认个数

    private int innerWidth;
    private float bottomAngle = 30f;
    private float firstAngle;
    private float currentSweepAngle;

    private boolean mPowerOpen = false;   //默认电源开关状态
    private Bitmap mPowerWhite = BitmapFactory.decodeResource(getResources(), R.drawable.power_white);
    private Bitmap mPowerOrange = BitmapFactory.decodeResource(getResources(), R.drawable.power_orange);
    private Bitmap mBottomIcon = BitmapFactory.decodeResource(getResources(), R.drawable.control_bottom_icon);

    private Paint mDeafultPaint;   //默认画笔
    private Paint mCirclePaint;    //中心圆画笔
    private Paint mLinePaint;      //描边画笔
    private Paint mOutArcPaint;   //外圆框画笔
    private Paint textPaint;

    private PaintHelper paintHelper;
    int mStrokeColor = getResources().getColor(R.color.colorStrokeGray);
    int mDefaultColor = getResources().getColor(R.color.colorControlCenterBtn);
    int mTouchedColor = getResources().getColor(R.color.colorControlBtnStart);
    int mCircleColor = getResources().getColor(R.color.colorCenterBtnGreen);
    int mOutArcDefaultColor = getResources().getColor(R.color.colorMainGray);
    int mOutArcFocusColor = Color.parseColor("#FF9F1C");

    int center_x;  //视图的x原点
    int center_y;  //视图的y原点

    //点击区域的标记
    int CENTER = 10;
    int BOTTOM = 0;
    int ONE = 1;
    int TWO = 2;
    int THREE = 3;
    int FOUR = 4;
    int FIVE = 5;
    int SIX = 6;
    int SEVEN = 7;
    int EIGHT = 8;
    int NINE = 9;

    int touch_flag = -1;
    int current_flag = -1;

    private int mArcState = -1;  //标记当前的档位

    OnControlListener mlistener = null;

    public void setArcState(int mArcState) {
        this.mArcState = mArcState;
        invalidate();
    }

    public void setLightNum(int lightNum) {
        this.lightNum = lightNum;
        invalidate();
    }

    public interface OnControlListener {
        void onClickCenter();

        void onClickBottom();

        void onClickOne();

        void onClickTwo();

        void onClickThree();

        void onClickFour();

        void onClickFive();

        void onClickSix();

        void onClickSeven();

        void onClickEight();

        void onClickNine();

    }

    public void setOncontrolListener(OnControlListener listenr) {
        this.mlistener = listenr;
    }

    public ControFanLedlView(Context context) {
        this(context, null);
    }

    public ControFanLedlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        this.context = context;

        mRegionList = new ArrayList<>();
        mPathList = new ArrayList<>();
        mOutArcList = new ArrayList<>();

        outCir_p = new Path();


        //中心圆的region区域
        center_p = new Path();
        center_re = new Region();
        //添加10个region区域用于判断点击区域
        for (int i = 0; i < 10; i++) {
            mRegionList.add(new Region());
        }
        //画笔初始化
        mOutArcPaint = new Paint();
        mOutArcPaint.setColor(getResources().getColor(R.color.colorStrokeGray));
        mOutArcPaint.setStyle(Paint.Style.STROKE);
        mOutArcPaint.setAntiAlias(true);

        mLinePaint = new Paint();
        mLinePaint.setColor(mStrokeColor);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(5f);
        mLinePaint.setAntiAlias(true);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);

        mDeafultPaint = new Paint();
        mDeafultPaint.setColor(mDefaultColor);
        mDeafultPaint.setStyle(Paint.Style.FILL);
        mDeafultPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(getResources().getColor(R.color.colorControlText));
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.My_h = h;
        this.My_w = w;
        this.My_oldh = oldh;
        this.My_oldw = oldw;

        paintHelper = new PaintHelper(context, w, h);
//        center_x = w / 2;  //绘图中心
//        center_y = h / 2;
//
//
//        globalRegion = new Region(-w, -h, w, h);
//        int minwidth = w > h ? h : w;
//        minwidth *= 0.95;
//
//        //内围的圆的宽度，中心圆的宽度是内围圆的一半
//        innerWidth = minwidth;
//        int circleWidth = innerWidth-25;
//        int br = circleWidth / 2;   //中心圆的半径
//        int sr = circleWidth / 4;   //内圆的半径
//
//
//        mOutArcPaint.setStrokeWidth(br / 13);
//
//        currentSweepAngle = (360 - bottomAngle) / (geerNum - 1);  //获得每个扇形扫过的角度
//        firstAngle = -270 + bottomAngle / 2 + currentSweepAngle / 2;      //第一档位开始的中心角度
//        float firstOutArcAngle = -270 + bottomAngle / 2;  //第一档位外围框开始的角度
//
//        outCir_p.addCircle(center_x, center_y, innerWidth/2, Path.Direction.CW);
//        //画圆
//        center_p.addCircle(center_x, center_y, sr, Path.Direction.CW);
//        center_re.setPath(center_p, globalRegion);
//
//        //根据传进来的档位个数画档位扇形
//        for (int i = 0; i < geerNum; i++) {
//            if (i == 0) {
//                mPathList.add(paintHelper.getNumPath(bottomAngle, 90, br, sr, mRegionList.get(i)));
//                mOutArcList.add(paintHelper.getOutArcPath(bottomAngle, 90 - bottomAngle / 2, br));
//
//            } else {
//                Path path = paintHelper.getNumPath(currentSweepAngle, firstAngle + currentSweepAngle * (i - 1), br, sr, mRegionList.get(i));
//                mPathList.add(path);
//                Path path1 = paintHelper.getOutArcPath(currentSweepAngle, firstOutArcAngle + currentSweepAngle * (i - 1), br);
//                mOutArcList.add(path1);
//            }
//
//        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initPath(My_w, My_h, My_oldw, My_oldh);


//画圆
        canvas.drawPath(center_p, mCirclePaint);
        canvas.drawPath(center_p, mLinePaint);

//画圆外围的线框
        canvas.drawPath(outCir_p, mOutArcPaint);

//        if (mOutArcList.size() > 0) {
//            for (Path path : mOutArcList) {
//                canvas.drawPath(path, mOutArcPaint);
//            }
//        }
        mOutArcPaint.setColor(mOutArcFocusColor);
        if (mArcState > 0) {
            canvas.drawPath(mOutArcList.get(mArcState), mOutArcPaint);
        }

//画档位扇形
        if (mPathList.size() > 0) {

            for (Path path : mPathList) {
                canvas.drawPath(path, mDeafultPaint);
                canvas.drawPath(path, mLinePaint);
            }
        }
        //画档位数字
        textPaint.setTextSize(innerWidth / 9);
        for (int i = 0; i < geerNum; i++) {

            String a = String.valueOf(i);
            float strwidth = textPaint.measureText(a);  // 获取到文字的宽度
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();  //获取到文字的高度
            float strheight = Math.abs(-fontMetrics.descent + (fontMetrics.bottom - fontMetrics.top) / 2);

            float xx = (float) (innerWidth * 0.75 * 0.5 * Math.cos((firstAngle + currentSweepAngle * (i - 1)) / 180 * Math.PI));
            float yy = (float) (innerWidth * 0.75 * 0.5 * Math.sin((firstAngle + currentSweepAngle * (i - 1)) / 180 * Math.PI));
            if (mArcState >= 0 && i == mArcState) {

                textPaint.setColor(mOutArcFocusColor);
                if (mArcState == 0) {
                    //          canvas.drawText(a, center_x - strwidth / 2, (float) (center_y + innerWidth * 0.75 * 0.5) + strheight , textPaint);
                } else {
                    canvas.drawText(a, center_x + xx - strwidth / 2, center_y + yy + strheight, textPaint);
                }

                textPaint.setColor(Color.parseColor("#cdcdcd"));
           } else if (i == 0) {
                //         if(bottomAngle!=0){canvas.drawText(a, center_x - strwidth / 2, (float) (center_y + innerWidth * 0.75 * 0.5) + strheight, textPaint);}

//                canvas.save();
//                canvas.translate(center_x,center_y+innerWidth/3+20);
//              Rect rect = new Rect(-innerWidth/15,-innerWidth/24,innerWidth/15,innerWidth/24);
//                canvas.drawBitmap(mBottomIcon,null,rect,textPaint);
//               canvas.restore();

      } else {

                canvas.drawText(a, center_x + xx - strwidth / 2, center_y + yy + strheight, textPaint);
           }

        }

        mDeafultPaint.setColor(mTouchedColor);
        mCirclePaint.setColor(mTouchedColor);

        if (current_flag > 0 && current_flag < mPathList.size()) {
            canvas.drawPath(mPathList.get(current_flag), mDeafultPaint);

        } else if (current_flag == CENTER) {
            Toast.makeText(getContext(),"画",Toast.LENGTH_SHORT).show();
            canvas.drawPath(center_p, mCirclePaint);
        }


        mDeafultPaint.setColor(mDefaultColor);
        mCirclePaint.setColor(mCircleColor);
        mOutArcPaint.setColor(getResources().getColor(R.color.colorInnerGray));
        Rect rect1 = new Rect(center_x - innerWidth / 9, center_y - innerWidth / 9, center_x + innerWidth / 9, center_y + innerWidth / 9);
        if (mPowerOpen) {
            canvas.drawBitmap(mPowerOrange, null, rect1, mLinePaint);
        } else {
            canvas.drawBitmap(mPowerWhite, null, rect1, mLinePaint);
        }

        canvas.save();
        canvas.translate(center_x, center_y + innerWidth / 3 + 20);
        Rect rect = new Rect(-innerWidth / 15, -innerWidth / 24, innerWidth / 15, innerWidth / 24);
        canvas.drawBitmap(mBottomIcon, null, rect, textPaint);
        canvas.restore();

    }

    private void initPath(int w, int h, int oldw, int oldh) {


        center_x = w / 2;  //绘图中心
        center_y = h / 2;


        globalRegion = new Region(-w, -h, w, h);
        int minwidth = w > h ? h : w;
        minwidth *= 0.95;

        //内围的圆的宽度，中心圆的宽度是内围圆的一半
        innerWidth = minwidth;
        int circleWidth = innerWidth - 25;
        int br = circleWidth / 2;   //中心圆的半径
        int sr = circleWidth / 4;   //内圆的半径


        mOutArcPaint.setStrokeWidth(br / 13);

        currentSweepAngle = (360 - bottomAngle) / (geerNum - 1);  //获得每个扇形扫过的角度
        firstAngle = -270 + bottomAngle / 2 + currentSweepAngle / 2;      //第一档位开始的中心角度
        float firstOutArcAngle = -270 + bottomAngle / 2;  //第一档位外围框开始的角度

        outCir_p.addCircle(center_x, center_y, innerWidth / 2, Path.Direction.CW);
        //画圆
        center_p.addCircle(center_x, center_y, sr, Path.Direction.CW);
        center_re.setPath(center_p, globalRegion);

        //根据传进来的档位个数画档位扇形
        for (int i = 0; i < geerNum; i++) {
            if (i == 0) {
                mPathList.add(paintHelper.getNumPath(bottomAngle, 90, br, sr, mRegionList.get(i)));
                mOutArcList.add(paintHelper.getOutArcPath(bottomAngle, 90 - bottomAngle / 2, br));

            } else {
                Path path = paintHelper.getNumPath(currentSweepAngle, firstAngle + currentSweepAngle * (i - 1), br, sr, mRegionList.get(i));
                mPathList.add(path);
                Path path1 = paintHelper.getOutArcPath(currentSweepAngle, firstOutArcAngle + currentSweepAngle * (i - 1), br);
                mOutArcList.add(path1);
            }

        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //   return super.onTouchEvent(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        double distance = Math.sqrt(Math.pow(x - getWidth() / 2, 2) + Math.pow(y - getHeight() / 2, 2));
        if (distance > innerWidth / 2) {
            current_flag = touch_flag = -1;
            invalidate();
            return false;
        } else {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    touch_flag = getTouchPath(x, y);
                    current_flag = touch_flag;
                    break;
                case MotionEvent.ACTION_UP:
                    if (current_flag == touch_flag && current_flag != -1 && mlistener != null) {
                        if (current_flag == CENTER) {
                            mlistener.onClickCenter();
                        } else if (current_flag == ONE) {
                            mlistener.onClickOne();
                        } else if (current_flag == TWO) {
                            mlistener.onClickTwo();
                        } else if (current_flag == THREE) {
                            mlistener.onClickThree();
                        } else if (current_flag == FOUR) {
                            mlistener.onClickFour();
                        } else if (current_flag == FIVE) {
                            mlistener.onClickFive();
                        } else if (current_flag == SIX) {
                            mlistener.onClickSix();
                        } else if (current_flag == SEVEN) {
                            mlistener.onClickSeven();
                        } else if (current_flag == EIGHT) {
                            mlistener.onClickEight();
                        } else if (current_flag == NINE) {
                            mlistener.onClickNine();
                        }
                    }
                    touch_flag = current_flag = -1;
                    break;
                case MotionEvent.ACTION_CANCEL:
                    touch_flag = current_flag = -1;
                    break;
            }
            invalidate();
            return true;
        }

    }

    //返回当前触摸的区域标志
    private int getTouchPath(int x, int y) {
        if (center_re.contains(x, y)) {
            return CENTER;
        } else {
            for (int i = 0; i < mRegionList.size(); i++) {

               if (mRegionList.get(i).contains(x, y)) {return i;}
            }
        }
        return -1;
    }

    public void setPowerState(boolean b) {
        this.mPowerOpen = b;
    }

    //默认是30度
    public void setBottomAngle(float bottomAngle) {

        if (bottomAngle >= 0) {
            this.bottomAngle = bottomAngle;
        } else {
            this.bottomAngle = 30f;
        }
        invalidate();
    }

    public void setGeerNum(int Num) {
        if (Num <= 10 && Num >= 4) {
            this.geerNum = Num;
        } else {
            this.geerNum = 4;
        }

        invalidate();
    }

}
