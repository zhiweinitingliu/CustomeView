package com.dukang.customeview.ui.activity;

import android.view.View;
import android.widget.Button;

import com.dukang.customeview.R;
import com.dukang.customeview.base.BaseActivity;
import com.dukang.customeview.ui.view.GraphView;
import com.dukang.customeview.ui.view.PaintTextView;
import com.dukang.customeview.ui.view.WaveView;

/**
 * @Description : 基础图形绘制
 * @Author : wdk
 * @CretaTime : 2019/1/26 17:24
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/26 17:24
 * @LastCheckBy :wdk
 */
public class GraphViewActivity extends BaseActivity implements View.OnClickListener {

    private GraphView graphView;
    private Button btn_reset;
    private WaveView waveView;
    private PaintTextView paintTextView;

    @Override
    public int getLayout() {
        return R.layout.activity_graph_view;
    }

    @Override
    public void initView() {
        graphView = findViewById(R.id.graphView);
        btn_reset = findViewById(R.id.btn_reset);
        waveView = findViewById(R.id.waveView);
        paintTextView = findViewById(R.id.paintTextView);
    }

    @Override
    public void initListener() {
        btn_reset.setOnClickListener(this);
        paintTextView.setOnClickListener(this);
    }

    @Override
    public void initData() {
        waveView.startAnim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset:
                graphView.reset();
                break;
            case R.id.paintTextView:
                paintTextView.startAnim();
                break;
        }
    }
}
