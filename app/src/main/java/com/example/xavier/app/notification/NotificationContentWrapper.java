package com.example.xavier.app.notification;

import android.graphics.Bitmap;

/**
 *
 * @author peter
 * @date 2018/7/4
 */

class NotificationContentWrapper {
    Bitmap bitmap;
    String title;
    String summery;

    NotificationContentWrapper(Bitmap bitmap, String title, String summery) {
        this.bitmap = bitmap;
        this.title = title;
        this.summery = summery;
    }
}
