package com.zhoumushui.zygotebubblebadge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.zhoumushui.zygotebubblebadge.util.BadgeUtil;
import com.zhoumushui.zygotebubblebadge.util.ToastUtil;
import com.zhoumushui.zygotebubblebadge.widget.BadgeFloatingActionButton;

import cn.bingoogolapple.badgeview.BGABadgeFrameLayout;
import cn.bingoogolapple.badgeview.BGABadgeImageView;
import cn.bingoogolapple.badgeview.BGABadgeLinearLayout;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;
import cn.bingoogolapple.badgeview.BGABadgeRelativeLayout;
import cn.bingoogolapple.badgeview.BGABadgeTextView;
import cn.bingoogolapple.badgeview.BGABadgeView;
import cn.bingoogolapple.badgeview.BGABadgeable;
import cn.bingoogolapple.badgeview.BGADragDismissDelegate;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private int unreadNumber;

    private BGABadgeView mTestBv;

    private BGABadgeTextView mTestBtv;

    private BGABadgeImageView mNormalBiv;
    private BGABadgeImageView mRoundedBiv;
    private BGABadgeImageView mCircleBiv;

    private BGABadgeLinearLayout mTestBll;
    private BGABadgeRelativeLayout mTestBrl;
    private BGABadgeFrameLayout mTestBfl;

//    private MessageAdapter mMessageAdapter;

    private RadioGroup mTabRg;
    private BGABadgeRadioButton mHomeBrb;
    private BGABadgeRadioButton mMessageBrb;
    private BGABadgeRadioButton mDiscoverBrb;
    private BGABadgeRadioButton mMeBrb;

    private BadgeFloatingActionButton mChatBfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);

        ToastUtil.init(this);

        initView();
        testBadgeView();
        testRadioButton();

        unreadNumber = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.unread_plus:
                BadgeUtil.sendToSamsumg(context, ++unreadNumber);
                return true;

            case R.id.unread_minus:
                --unreadNumber;
                if (unreadNumber < 0)
                    unreadNumber = 0;
                BadgeUtil.sendToSamsumg(context, unreadNumber);
                return true;

            case R.id.unread_reset:
                BadgeUtil.sendToSamsumg(context, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initView() {
        mTestBv = getViewById(R.id.bv_main_test);

        mTestBtv = getViewById(R.id.btv_main_test);

        mNormalBiv = getViewById(R.id.biv_main_normal);
        mRoundedBiv = getViewById(R.id.biv_main_rounded);
        mCircleBiv = getViewById(R.id.biv_main_circle);

        mTestBll = getViewById(R.id.bll_main_test);
        mTestBrl = getViewById(R.id.brl_main_test);
        mTestBfl = getViewById(R.id.bfl_main_test);

        mTabRg = getViewById(R.id.rg_main_tab);
        mHomeBrb = getViewById(R.id.brb_main_home);
        mMessageBrb = getViewById(R.id.brb_main_message);
        mDiscoverBrb = getViewById(R.id.brb_main_discover);
        mMeBrb = getViewById(R.id.brb_main_me);

        mChatBfab = getViewById(R.id.bfab_main_chat);
    }

    private void testBadgeView() {
        mTestBv.showTextBadge("9");
        mTestBv.getBadgeViewHelper().setBadgeTextSizeSp(15);
        mTestBv.getBadgeViewHelper().setBadgePaddingDp(8);
        mTestBv.getBadgeViewHelper().setBadgeTextColorInt(Color.parseColor("#FF0000"));
        mTestBv.getBadgeViewHelper().setBadgeBgColorInt(Color.parseColor("#00FF00"));
        mTestBv.getBadgeViewHelper().setDragable(true);
        mTestBv.getBadgeViewHelper().setBadgePaddingDp(7);
        mTestBv.getBadgeViewHelper().setBadgeBorderWidthDp(2);
        mTestBv.getBadgeViewHelper().setBadgeBorderColorInt(Color.parseColor("#0000FF"));

        mTestBtv.showCirclePointBadge();

        mNormalBiv.showCirclePointBadge();

        Bitmap avatarBadgeBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.avatar_vip);

        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        roundedDrawable.getPaint().setAntiAlias(true);
        roundedDrawable.setCornerRadius(30);
        mRoundedBiv.setImageDrawable(roundedDrawable);
        mRoundedBiv.showDrawableBadge(avatarBadgeBitmap);

        Bitmap avatarBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                avatarBitmap);
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCornerRadius(Math.max(avatarBitmap.getWidth(),
                avatarBitmap.getHeight()) / 2.0f);
        mCircleBiv.setImageDrawable(circleDrawable);
        mCircleBiv.showDrawableBadge(avatarBadgeBitmap);

        mTestBll.showDrawableBadge(avatarBadgeBitmap);
        mTestBrl.showTextBadge("LoveAndroid");
        mTestBfl.showTextBadge("8");

        mChatBfab.showTextBadge("8");
        mChatBfab.setDragDismissDelegage(new BGADragDismissDelegate() {
            @Override
            public void onDismiss(BGABadgeable badgeable) {
                ToastUtil.show("清空聊天消息");
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRoundedBiv.hiddenBadge();
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRoundedBiv.showCirclePointBadge();
            }
        }, 6000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRoundedBiv.showDrawableBadge(BitmapFactory.decodeResource(getResources(),
                        R.mipmap.avatar_vip));
            }
        }, 9000);
    }

    private void testRadioButton() {
        mHomeBrb.showTextBadge("10");
        mMessageBrb.showTextBadge("1");
        mDiscoverBrb.showTextBadge("...");
        mMeBrb.showDrawableBadge(BitmapFactory.decodeResource(getResources(), R.mipmap.avatar_vip));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHomeBrb.showTextBadge("1");
            }
        }, 5000);

        mHomeBrb.setDragDismissDelegage(new BGADragDismissDelegate() {
            @Override
            public void onDismiss(BGABadgeable badgeable) {
                ToastUtil.show("消息单选按钮徽章拖动消失");
            }
        });

        mTabRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.brb_main_home:
                        ToastUtil.show("首页");
                        break;
                    case R.id.brb_main_message:
                        ToastUtil.show("消息");
                        break;
                    case R.id.brb_main_discover:
                        ToastUtil.show("发现");
                        break;
                    case R.id.brb_main_me:
                        ToastUtil.show("我");
                        break;
                }
            }
        });
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }
}
