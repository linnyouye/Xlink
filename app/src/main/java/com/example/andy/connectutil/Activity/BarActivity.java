package com.example.andy.connectutil.Activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andy.connectutil.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 95815 .
 * Date:2017/4/4.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 *
 * 侧滑菜单的活动是继承该活动的
 */

public class BarActivity extends BasicActivity implements View.OnClickListener {

    @Bind(R.id.tv_toolbar_others)
    TextView tv_title;
    @Bind(R.id.ibtn_toolbar_backup)
    ImageButton ibtn_backup;


    @Override
    protected void setBefortLayout() {

    }

    @Override
    protected void setActionbar() {
        ButterKnife.bind(this);
        tv_title = (TextView)findViewById (R.id.tv_toolbar_others);
        ibtn_backup = (ImageButton) findViewById (R.id.ibtn_toolbar_backup);
        ibtn_backup.setOnClickListener(this);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.ibtn_toolbar_backup)
    public void onClick(View v) {
        if(v.getId() == R.id.ibtn_toolbar_backup){
            this.finish();
        }
    }

  public TextView getTvTitle(){
        return tv_title;
    }
   public ImageButton getIbtn_backup(){
        return ibtn_backup;
    }

}
