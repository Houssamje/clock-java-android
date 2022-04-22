package com.example.horslog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class clock extends View {
    private float centerX;
    private float centerY;
    private float radius;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public clock(Context context) {
        super(context);
    }

    public clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onSizeChanged(int with,int height,int oldW,int oldH){
        centerX =with/2;
        centerY = height/2;
        radius =Math.min(with*0.45f , height*0.45f );
    }
    @Override
    protected void onDraw(Canvas canvas){
        //tracer du disk
        LinearGradient linearGradient =new LinearGradient(
                0,centerY-radius,0,centerY+radius,
        new int[]{0xffe0e0e0,0xff6e774,0xffBa0e0a,0xffBaB8B9},
        new float[]{0,0.49f,0.5f,1},
                Shader.TileMode.REPEAT
        );
        paint.setShader(linearGradient);
        canvas.drawCircle(centerX,centerY,radius,paint);
        paint.setShader(null);
        paint.setColor(0xff212121);
        canvas.drawCircle(centerX,centerY,radius*0.95f,paint);
        paint.setColor(0xffffffff);
        paint.setStrokeWidth(3f);
        paint.setTextSize(radius/8f);
        int  hour=12;
        for(float angle=0;angle<=360;angle+=6f){
            float radianAngle=(float) -(Math.PI/2-(Math.PI*angle/180));
            if(angle%30!=0){
                canvas.drawLine(
                        (float) (centerX+Math.cos(radianAngle)*radius*0.85),
                        (float) (centerX+Math.cos(radianAngle)*radius*0.85),
                        (float) (centerX+Math.cos(radianAngle)*radius*0.85),
                );

            }
        }
    }
}
