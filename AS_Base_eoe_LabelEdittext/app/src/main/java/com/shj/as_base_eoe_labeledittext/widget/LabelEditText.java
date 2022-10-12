package com.shj.as_base_eoe_labeledittext.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shj.as_base_eoe_labeledittext.R;

import java.util.jar.Attributes;

public class LabelEditText extends LinearLayout {
    private TextView textView;
    private String labelText;
    private int labelFontSize;
    private String labelPosition;
    //context 所有控件类都会带的参数，AttributeSet 用来存储属性名和值
    public LabelEditText(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        //读取labelText属性的资源ID
        int resourceId = attributeSet.getAttributeResourceValue(null,"labelText",0);
        //未获得资源ID, 继续读取属性值
        if (resourceId==0)
            labelText = attributeSet.getAttributeValue(null,"labelText");
        else
            labelText = getResources().getString(resourceId);
        //如果按照两种方式都未获得labelText的属性值，表示未设置该属性，抛出异常
        if (labelText==null)
        {
            throw new RuntimeException("必须设置labelText属性值");
        }
        //获得labelFontSize属性的资源ID
        resourceId = attributeSet.getAttributeResourceValue(null,"labelFontSize",0);
        //继续读取labelFontSize属性的值，若未设置，将属性值设为14
        if (resourceId==0)
            labelFontSize = attributeSet.getAttributeIntValue(null,"labelFontSize",14);
        //从资源文件中获取labelFontSize的属性值
        else
            labelFontSize=getResources().getInteger(resourceId);
        //获得labelPosition属性的资源ID
        resourceId = attributeSet.getAttributeResourceValue(null,"labelPosition",0);
        //继续读取labelPosition属性的值
        if (resourceId==0)
            labelPosition = attributeSet.getAttributeValue(null,"labelPosition");
        //从资源文件中获得labelPosition属性的值
        else
            labelPosition = getResources().getString(resourceId);
        //如果未设置labelPosition属性值，将该属性值设为left
        if (labelPosition==null)
            labelPosition = "left";

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;  //通过这个来装载布局
        //获得LAYOUT_INFLATER_SERVICE服务
        layoutInflater = (LayoutInflater)context.getSystemService(infService);
        LinearLayout linearLayout = null;
        //根据labelPosition属性的值装载不同的布局文件；
        if ("left".equals(labelPosition))
            linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.labeledittext_horizontal,this);
        else if ("top".equals(labelPosition))
            linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.labeledittext_vertical,this);
        else
            throw new RuntimeException("labelPosition属性的值只能是left或者top");

        //下面的代码 从相应的布局文件中获得TextView对象，并根据labelTextView的属性值设置TextView的属性
        textView = (TextView)findViewById(R.id.textview);
        textView.setTextSize(labelFontSize);
        textView.setText(labelText);
    }
}
