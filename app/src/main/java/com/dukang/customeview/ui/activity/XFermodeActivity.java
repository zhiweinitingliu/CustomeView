package com.dukang.customeview.ui.activity;

import android.graphics.PorterDuff;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dukang.customeview.R;
import com.dukang.customeview.base.BaseActivity;
import com.dukang.customeview.ui.view.XFermodeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :paint 方法 xFermode
 * @Author : wdk
 * @CretaTime : 2019/2/17 10:44
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/2/17 10:44
 * @LastCheckBy :wdk
 */
public class XFermodeActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spinner;
    private XFermodeView xFermodeView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_xfermode;
    }

    @Override
    public void initView() {
        spinner = findViewById(R.id.spinner);
        xFermodeView = findViewById(R.id.xFermodeView);

        data.add("SRC_IN");
        modeMap.put("SRC_IN", PorterDuff.Mode.SRC_IN);
        data.add("ADD");
        modeMap.put("ADD", PorterDuff.Mode.ADD);
        data.add("LIGHTEN");
        modeMap.put("LIGHTEN", PorterDuff.Mode.LIGHTEN);
        data.add("DARKEN");
        modeMap.put("DARKEN", PorterDuff.Mode.DARKEN);
        data.add("MULTIPLY");
        modeMap.put("MULTIPLY", PorterDuff.Mode.MULTIPLY);
        data.add("OVERLAY");
        modeMap.put("OVERLAY", PorterDuff.Mode.OVERLAY);
        data.add("SCREEN");
        modeMap.put("SCREEN", PorterDuff.Mode.SCREEN);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void initListener() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String modeName = data.get(position);
                PorterDuff.Mode mode = modeMap.get(modeName);
                xFermodeView.setPorterDuffMode(mode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    List<String> data = new ArrayList<>();
    Map<String, PorterDuff.Mode> modeMap = new HashMap<>();

    @Override
    public void initData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
