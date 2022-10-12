package com.shj.as_base_eoe_icontextview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import com.shj.as_base_eoe_icontextview.R;


@SuppressLint("AppCompatCustomView")
public class IconTextView extends TextView {
    //命名空间的值
    private final String namespace = "http://cn.eoe.icon.textview";
    //里面设置无所谓的，只是字符串，不过再后来的控件调用中，需要绑定命名空间

    //图像资源ID
    private int resourceId = 0;
    private Bitmap bitmap;

    public IconTextView (Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.IconTextView);
        resourceId = typedArray.getResourceId(R.styleable.IconTextView_iconSrc,0);
        //设置了左边的图像资源
        resourceId = attributeSet.getAttributeResourceValue(namespace,"iconSrc",0);
        if (resourceId > 0)
            //装载图像
            bitmap = BitmapFactory.decodeResource(getResources(),resourceId);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null)
        {
            //从原图上截取图像的区域，在本例中为整个图像
            Rect src = new Rect();
            //将截取的图像复制到bitmap上的目标区域中，在本例中与复制区域相同；
            Rect target = new Rect();
            src.left = 0;
            src.top = 0;
            src.right = bitmap.getWidth();
            src.bottom = bitmap.getHeight();

            int textHeight = (int)getTextSize();
            target.left = 0;
            //计算图像复制到目录区域的纵坐标。由于TextView中文本不是 从最顶端开始绘制，需要重新计算
            target.top = (int)((getMeasuredHeight() - getTextSize()) / 2)+ 1;
            target.bottom = target.top + textHeight;
            //为了保证图像不变形， 需要根据 图像高度重新计算 图像的宽度；
            target.right = (int)(textHeight * (bitmap.getWidth()/(float)bitmap
                    .getHeight()));

            //开始绘制图像
            canvas.drawBitmap(bitmap,src,target,getPaint());
            //将 TextView 中的文本向右 移动一定的距离， 在本例中 ， 图像宽度加  2个像素点的位置

           canvas.translate(target.right + 2,0);

        }
        super.onDraw(canvas);
    }
}

