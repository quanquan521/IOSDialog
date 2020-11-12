package yzq.com.library;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author：Administrator on 2016/12/16 16:01
 * description:自定义dialog
 * version:版本
 */

public class CommonViewDialog extends Dialog {

    /**
     * 显示的标题
     */
    private TextView titleTv ;
    /**
     * 确认和取消按钮
     */
    private Button negtiveBn ,positiveBn;
    /**
     * 按钮之间的分割线
     */
    private View columnLineView ;

    /*
    * 顶部分割线
    * */
    private View topDevider ;
    /*
    * 取消按钮颜色
    * */
    private int  cancelButtonColor=-1;
    /*
    * 确定按钮颜色
    * */
    private  int sureButtonColor=-1;


    public CommonViewDialog(Context context) {
        super(context, R.style.CustomDialog);
    }


    private String title,positive,negtive,message;
    private  OnPositiveClickListener onPositiveClickListener;
    private  OnNegtiveClickListener onNegtiveClickListener;

    public interface OnPositiveClickListener {
        void onClick();
    }
    public interface OnNegtiveClickListener {
        void onClick();
    }
    /**
     * 底部是否只有一个按钮
     */
    private Bottom style = Bottom.TWO;
    public enum Bottom{
        SINGLE,TWO,GONE
    }


    /*
    * 添加布局view
    * */
    private LinearLayout containner;
    private View view;

    /*
    * message
    * */
    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commonviewdialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        positiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onPositiveClick();
                }
                if (onPositiveClickListener!=null){
                    onPositiveClickListener.onClick();
                    dismiss();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        negtiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onNegtiveClick();
                }
                if (onNegtiveClickListener!=null){
                    onNegtiveClickListener.onClick();
                    dismiss();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    public void refreshView()   {
        //如果用户自定了title和message
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
        }else {
            titleTv.setVisibility(View.GONE);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(positive)) {
            positiveBn.setText(positive);
        }else {
            positiveBn.setText("确定");
        }
        if (!TextUtils.isEmpty(negtive)) {
            negtiveBn.setText(negtive);
        }else {
            negtiveBn.setText("取消");
        }

        /**
         * 只显示一个按钮的时候隐藏取消按钮，回掉只执行确定的事件
         */
        if (style== Bottom.SINGLE){
            columnLineView.setVisibility(View.GONE);
            negtiveBn.setVisibility(View.GONE);

        }else if (style== Bottom.TWO){
            negtiveBn.setVisibility(View.VISIBLE);
            columnLineView.setVisibility(View.VISIBLE);
        }else {
            topDevider.setVisibility(View.INVISIBLE);
            columnLineView.setVisibility(View.GONE);
            negtiveBn.setVisibility(View.GONE);
            positiveBn.setVisibility(View.GONE);
        }
        if (view!=null){
            containner.removeAllViews();
            containner.addView(view);
        }
        if (!TextUtils.isEmpty(message)){
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText(message);
        }else {
            tv_message.setVisibility(View.GONE);
        }
        if (cancelButtonColor!=-1){
            negtiveBn.setTextColor(cancelButtonColor);
        }
        if (sureButtonColor!=-1){
            positiveBn.setTextColor(sureButtonColor);
        }
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        getWindow().setBackgroundDrawableResource(R.color.photo_pick_transparent);
        negtiveBn = (Button) findViewById(R.id.negtive);
        positiveBn = (Button) findViewById(R.id.positive);
        titleTv = (TextView) findViewById(R.id.title);
        columnLineView = findViewById(R.id.column_line);
        containner=findViewById(R.id.containner);
        topDevider=findViewById(R.id.topDevider);
        tv_message=findViewById(R.id.message);
    }

    /**
     * 设置确定取消按钮的回调
     */
    public OnClickBottomListener onClickBottomListener;
    public CommonViewDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }
    public interface OnClickBottomListener{
        /**
         * 点击确定按钮事件
         */
        public void onPositiveClick();
        /**
         * 点击取消按钮事件
         */
        public void onNegtiveClick();
    }

    public CommonViewDialog setOnPositiveClickListener(final OnPositiveClickListener listener) {
       this.onPositiveClickListener=listener;
       return this;
    }
    public CommonViewDialog setOnNegtiveClickListener(final OnNegtiveClickListener listener) {
        this.onNegtiveClickListener=listener;
        return this;
    }
    public String getTitle() {
        return title;
    }

    public CommonViewDialog setTitle(String title) {
        this.title = title;
        return this ;
    }

    public String getPositive() {
        return positive;
    }

    public CommonViewDialog setPositive(String positive) {
        this.positive = positive;
        return this ;
    }

    public String getNegtive() {
        return negtive;
    }

    public CommonViewDialog setNegtive(String negtive) {
        this.negtive = negtive;
        return this ;
    }

    public View getView() {
        return view;
    }

    public Bottom isSingle() {
        return style;
    }


    public CommonViewDialog setSingle(Bottom b) {
        style = b;
        return this ;
    }

    public CommonViewDialog setView(View view) {
        this.view=view;
        return this ;
    }
    public CommonViewDialog setCancelButtonColor(int color) {
        this.cancelButtonColor=color;
        return this ;
    }
    public CommonViewDialog setSureButtonColor(int color) {
        this.sureButtonColor=color;
        return this ;
    }

    public String getMessage() {
        return message;
    }

    public CommonViewDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public static CommonViewDialog getInstance(Context context){
         CommonViewDialog dialog=new CommonViewDialog(context);
        return dialog;
     }

}