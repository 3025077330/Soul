package com.example.fromwork.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.example.fromwork.R;

public class BottomBar extends LinearLayout {

    //Bommtom各个下标
    public static final int STAR_INDEX = 0;
    public static final int SQUARE_INDEX = 1;
    public static final int MOMENT_INDEX = 2;
    public static final int CHAT_INDEX = 3;
    public static final int MINE_INDEX = 4;

    //BottomBar文字颜色
    private int selectColor;
    private int unselectColor;
    private String[] tabTitles;


    private IBottomBarSelectListener iBottomBarSelectListener;

    public BottomBar(Context context) {
        super(context);
        init(context, null, 0);
    }


    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initBottomBarAttrs(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bottombar_layout, this);
        RadioGroup radioGroup = view.findViewById(R.id.bottomGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.bu_star) {
                    //星球
                    selectStar();
                } else if (checkedId == R.id.bu_square) {
                    //广场
                    selectSquare();
                } else if (checkedId == R.id.bu_moment) {
                    //发布瞬间
                    selectMoent();
                } else if (checkedId == R.id.bu_chat) {
                    //聊天
                    selectChat();
                } else if (checkedId == R.id.bu_mine) {
                    //我的
                    selectMine();
                }
            }

        });
        selectStar();//默认显示星球页面
    }

    private void initBottomBarAttrs(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomBar);
        selectColor = typedArray.getColor(R.styleable.BottomBar_select_textcolor, Color.RED);
        unselectColor = typedArray.getColor(R.styleable.BottomBar_unselect_textcolor, Color.BLACK);
    }


    public void setTabTitles(String[] tabTitles) {
        this.tabTitles = tabTitles;
        RadioButton starButton = findViewById(R.id.bu_star);
        starButton.setText(this.tabTitles[0]);
        RadioButton squareButton = findViewById(R.id.bu_square);
        squareButton.setText(this.tabTitles[1]);
        RadioButton momentButton = findViewById(R.id.bu_moment);
        momentButton.setText(this.tabTitles[2]);
        RadioButton chatButton = findViewById(R.id.bu_chat);
        chatButton.setText(this.tabTitles[3]);
        RadioButton mineButton = findViewById(R.id.bu_mine);
        mineButton.setText(this.tabTitles[4]);
    }


    public void FindIndex(int postion) {
        switch (postion) {
            case STAR_INDEX:
                selectStar();
                break;
            case SQUARE_INDEX:
                selectSquare();
                break;
            case MOMENT_INDEX:
                selectMoent();
                break;
            case CHAT_INDEX:
                selectChat();
                break;
            case MINE_INDEX:
                selectMine();
                break;
            default:
                selectStar();
        }
    }


    private void selectMine() {
        RadioButton starButton = findViewById(R.id.bu_star);
        starButton.setTextColor(unselectColor);
        RadioButton squareButton = findViewById(R.id.bu_square);
        squareButton.setTextColor(unselectColor);
        RadioButton momentButton = findViewById(R.id.bu_moment);
        momentButton.setTextColor(unselectColor);
        RadioButton chatButton = findViewById(R.id.bu_chat);
        chatButton.setTextColor(unselectColor);
        RadioButton mineButton = findViewById(R.id.bu_mine);
        mineButton.setChecked(true);
        mineButton.setTextColor(selectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(MINE_INDEX);
        }
    }

    private void selectChat() {
        RadioButton starButton = findViewById(R.id.bu_star);
        starButton.setTextColor(unselectColor);
        RadioButton squareButton = findViewById(R.id.bu_square);
        squareButton.setTextColor(unselectColor);
        RadioButton momentButton = findViewById(R.id.bu_moment);
        momentButton.setTextColor(unselectColor);
        RadioButton chatButton = findViewById(R.id.bu_chat);
        chatButton.setChecked(true);
        chatButton.setTextColor(selectColor);
        RadioButton mineButton = findViewById(R.id.bu_mine);
        mineButton.setTextColor(unselectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(CHAT_INDEX);
        }
    }

    private void selectMoent() {
        RadioButton starButton = findViewById(R.id.bu_star);
        starButton.setTextColor(unselectColor);
        RadioButton squareButton = findViewById(R.id.bu_square);
        squareButton.setTextColor(unselectColor);
        RadioButton momentButton = findViewById(R.id.bu_moment);
        momentButton.setChecked(true);
        momentButton.setTextColor(selectColor);
        RadioButton chatButton = findViewById(R.id.bu_chat);
        chatButton.setTextColor(unselectColor);
        RadioButton mineButton = findViewById(R.id.bu_mine);
        mineButton.setTextColor(unselectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(MOMENT_INDEX);
        }
    }

    private void selectSquare() {
        RadioButton starButton = findViewById(R.id.bu_star);
        starButton.setTextColor(unselectColor);
        RadioButton squareButton = findViewById(R.id.bu_square);
        squareButton.setChecked(true);
        squareButton.setTextColor(selectColor);
        RadioButton momentButton = findViewById(R.id.bu_moment);
        momentButton.setTextColor(unselectColor);
        RadioButton chatButton = findViewById(R.id.bu_chat);
        chatButton.setTextColor(unselectColor);
        RadioButton mineButton = findViewById(R.id.bu_mine);
        mineButton.setTextColor(unselectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(SQUARE_INDEX);
        }

    }

    //选择了星期
    private void selectStar() {
        RadioButton starButton = findViewById(R.id.bu_star);
        starButton.setChecked(true);
        starButton.setTextColor(selectColor);
        RadioButton squareButton = findViewById(R.id.bu_square);
        squareButton.setTextColor(unselectColor);
        RadioButton momentButton = findViewById(R.id.bu_moment);
        momentButton.setTextColor(unselectColor);
        RadioButton chatButton = findViewById(R.id.bu_chat);
        chatButton.setTextColor(unselectColor);
        RadioButton mineButton = findViewById(R.id.bu_mine);
        mineButton.setTextColor(unselectColor);
        if (iBottomBarSelectListener != null) {
            iBottomBarSelectListener.onBottomBarSelected(STAR_INDEX);
        }

    }


    public void setBottomBarSelectListener(IBottomBarSelectListener listener) {
        this.iBottomBarSelectListener = listener;
    }


    //定义一个接口，这个接口，Activity或者Fragment实现这个接口，通过这个接口达到自定义view和Activity或者Fragment之间的通信
    public interface IBottomBarSelectListener {
        void onBottomBarSelected(int selectIndex);

        void onBottomForPagerSelected(int selectindex);
    }


}
