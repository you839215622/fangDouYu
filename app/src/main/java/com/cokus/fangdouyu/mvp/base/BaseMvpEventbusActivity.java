package com.cokus.fangdouyu.mvp.base;

import android.os.Bundle;

import com.cokus.fangdouyu.mvp.BaseModel;
import com.cokus.fangdouyu.mvp.BasePresenter;
import com.cokus.fangdouyu.mvp.BaseView;
import com.cokus.fangdouyu.mvp.util.TUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;


/**
 *
 * @param <P>
 * @param <M>
 */

public abstract class BaseMvpEventbusActivity<P extends BasePresenter, M extends BaseModel> extends com.cokus.fangdouyu.base.BaseMvpActivity implements BaseView {

    public P mPresenter;
    public M mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) {
            mPresenter.setVM(this, mModel);
        }
        EventBus.getDefault().register(this);
        initView();
        loadData();
    }

    protected abstract void loadData();
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.onDestroy();
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }



    @Override
    public void onInternetError() {
//        showShortToast("网络异常");
    }

    @Override
    public void onRequestError(String msg) {
//        showShortToast(msg);

        Logger.e("REQUEST_ERROR ==== ", msg);
    }
}
