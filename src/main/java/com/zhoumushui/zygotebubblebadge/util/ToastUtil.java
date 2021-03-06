package com.zhoumushui.zygotebubblebadge.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.zhoumushui.zygotebubblebadge.R;

import cn.bingoogolapple.badgeview.BGABadgeViewUtil;

public class ToastUtil {
    private static Toast sToast;
    private static TextView sMsgTv;

    private ToastUtil() {
    }

    public static void init(Context context) {
        sToast = new Toast(context);
        sToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0,
                BGABadgeViewUtil.dp2px(context, 70));
        sMsgTv = (TextView) LayoutInflater.from(context).inflate(R.layout.view_toast, null);
        sToast.setView(sMsgTv);
        sToast.setDuration(Toast.LENGTH_SHORT);
    }

    public static void show(CharSequence text) {
        sMsgTv.setText(text);
        sToast.show();
    }
}