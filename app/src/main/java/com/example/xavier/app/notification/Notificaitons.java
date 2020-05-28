package com.example.xavier.app.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.xavier.MainActivity;
import com.example.xavier.R;

import static com.example.xavier.app.ConstantPool.ACTION_ACTION;
import static com.example.xavier.app.ConstantPool.ACTION_ANSWER;
import static com.example.xavier.app.ConstantPool.ACTION_BIG_PICTURE_STYLE;
import static com.example.xavier.app.ConstantPool.ACTION_BIG_TEXT_STYLE;
import static com.example.xavier.app.ConstantPool.ACTION_CUSTOM_HEADS_UP_VIEW;
import static com.example.xavier.app.ConstantPool.ACTION_CUSTOM_VIEW_OPTIONS_CANCEL;
import static com.example.xavier.app.ConstantPool.ACTION_CUSTOM_VIEW_OPTIONS_LOVE;
import static com.example.xavier.app.ConstantPool.ACTION_CUSTOM_VIEW_OPTIONS_LYRICS;
import static com.example.xavier.app.ConstantPool.ACTION_CUSTOM_VIEW_OPTIONS_NEXT;
import static com.example.xavier.app.ConstantPool.ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE;
import static com.example.xavier.app.ConstantPool.ACTION_CUSTOM_VIEW_OPTIONS_PRE;
import static com.example.xavier.app.ConstantPool.ACTION_DELETE;
import static com.example.xavier.app.ConstantPool.ACTION_INBOX_STYLE;
import static com.example.xavier.app.ConstantPool.ACTION_MEDIA_STYLE;
import static com.example.xavier.app.ConstantPool.ACTION_MESSAGING_STYLE;
import static com.example.xavier.app.ConstantPool.ACTION_NO;
import static com.example.xavier.app.ConstantPool.ACTION_PROGRESS;
import static com.example.xavier.app.ConstantPool.ACTION_REJECT;
import static com.example.xavier.app.ConstantPool.ACTION_REMOTE_INPUT;
import static com.example.xavier.app.ConstantPool.ACTION_REPLY;
import static com.example.xavier.app.ConstantPool.ACTION_SIMPLE;
import static com.example.xavier.app.ConstantPool.ACTION_YES;
import static com.example.xavier.app.ConstantPool.EXTRA_OPTIONS;
import static com.example.xavier.app.ConstantPool.MEDIA_STYLE_ACTION_DELETE;
import static com.example.xavier.app.ConstantPool.MEDIA_STYLE_ACTION_NEXT;
import static com.example.xavier.app.ConstantPool.MEDIA_STYLE_ACTION_PAUSE;
import static com.example.xavier.app.ConstantPool.MEDIA_STYLE_ACTION_PLAY;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_ACTION;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_BIG_PICTURE_STYLE;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_BIG_TEXT_STYLE;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_CUSTOM;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_CUSTOM_HEADS_UP;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_INBOX_STYLE;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_MEDIA_STYLE;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_MESSAGING_STYLE;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_PROGRESS;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_REMOTE_INPUT;
import static com.example.xavier.app.ConstantPool.NOTIFICATION_SAMPLE;
import static com.example.xavier.app.ConstantPool.REMOTE_INPUT_RESULT_KEY;
import static com.example.xavier.app.ConstantPool.XAVIER;

public class Notificaitons {

    private static volatile Notificaitons sInstance = null;

    private Notificaitons() {
    }

    public static Notificaitons getInstance() {
        if (sInstance == null) {
            synchronized (Notificaitons.class) {
                if (sInstance == null) {
                    sInstance = new Notificaitons();
                }
            }
        }
        return sInstance;
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    //  xin-xavier 自定义
    //
    ///////////////////////////////////////////////////////////////////////////
    public void sendMessageNotification(Context context, String message) {
        // 设置通知渠道的id
        String id=XAVIER;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "notification channel";
            // 用户可以看到的通知渠道的描述
            String description = "notification description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            channel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 100, 300});
            //最后在notificationmanager中创建该通知渠道
            if (nm != null) {
                nm.createNotificationChannel(channel);
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
                Notification.Builder nb = new Notification.Builder(context, id)
                        //设置通知左侧的小图标
                        .setSmallIcon(R.mipmap.ic_notification)
                        //设置通知标题
                        .setContentTitle("ChatExample")
                        //设置通知内容
                        .setContentText(message)
                        //设置点击通知后自动删除通知
                        .setAutoCancel(true)
                        //设置显示通知时间
                        .setShowWhen(true)
                        //设置点击通知时的响应事件
                        .setContentIntent(pi);
                //发送通知
                nm.notify(NOTIFICATION_ACTION, nb.build());
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(context, id)
                    .setAutoCancel(true)
                    // 设置该通知优先级
                    .setPriority(Notification.PRIORITY_MAX)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("ChatExample")
                    .setContentText(message)
                    .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                    .setWhen(System.currentTimeMillis())
                    // 向通知添加声音、闪灯和振动效果
                    .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
                    .setContentIntent(pendingIntent)
                    .build();
            // 开启震动
            //VibrateUtil.vibrate(getBaseContext(), new long[]{100, 200, 100, 200}, 2);
            //VibrateUtil.virateCancle(getBaseContext());
            if (notifyManager != null) {
                notifyManager.notify(NOTIFICATION_ACTION, notification);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //  Other 其他方法
    ///////////////////////////////////////////////////////////////////////////
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendSimpleNotification(Context context, NotificationManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_SIMPLE);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建删除通知时发送的广播
        Intent deleteIntent = new Intent(context, NotificationService.class);
        deleteIntent.setAction(ACTION_DELETE);
        PendingIntent deletePendingIntent = PendingIntent.getService(context, 0, deleteIntent, 0);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.LOW)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Simple notification")
                //设置通知内容
                .setContentText("Demo for simple notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置通知右侧的大图标
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_notifiation_big))
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置删除通知时的响应事件
                .setDeleteIntent(deletePendingIntent);
        //发送通知
        nm.notify(NOTIFICATION_SAMPLE, nb.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendActionNotification(Context context, NotificationManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_ACTION);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.DEFAULT)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Action notification")
                //设置通知内容
                .setContentText("Demo for action notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi);
        //创建点击通知 YES 按钮时发送的广播
        Intent yesIntent = new Intent(context, NotificationService.class);
        yesIntent.setAction(ACTION_YES);
        PendingIntent yesPendingIntent = PendingIntent.getService(context, 0, yesIntent, 0);
        Notification.Action yesActionBuilder = new Notification.Action.Builder(
                Icon.createWithResource("", R.mipmap.ic_yes),
                "YES",
                yesPendingIntent)
                .build();
        //创建点击通知 NO 按钮时发送的广播
        Intent noIntent = new Intent(context, NotificationService.class);
        noIntent.setAction(ACTION_NO);
        PendingIntent noPendingIntent = PendingIntent.getService(context, 0, noIntent, 0);
        Notification.Action noActionBuilder = new Notification.Action.Builder(
                Icon.createWithResource("", R.mipmap.ic_no),
                "NO",
                noPendingIntent)
                .build();
        //为通知添加按钮
        nb.setActions(yesActionBuilder, noActionBuilder);
        //发送通知
        nm.notify(NOTIFICATION_ACTION, nb.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendRemoteInputNotification(Context context, NotificationManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_REMOTE_INPUT);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.IMPORTANCE)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Remote input notification")
                //设置通知内容
                .setContentText("Demo for remote input notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi);
        //创建带输入框的按钮
        RemoteInput remoteInput = new RemoteInput.Builder(REMOTE_INPUT_RESULT_KEY)
                .setLabel("Reply").build();
        Intent remoteInputIntent = new Intent(context, NotificationService.class);
        remoteInputIntent.setAction(ACTION_REPLY);
        PendingIntent replyPendingIntent = PendingIntent.getService(context, 2, remoteInputIntent, 0);
        Notification.Action replyAction = new Notification.Action.Builder(
                Icon.createWithResource("", R.mipmap.ic_reply),
                "Reply",
                replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build();
        //为通知添加按钮
        nb.setActions(replyAction);
        //发送通知
        nm.notify(NOTIFICATION_REMOTE_INPUT, nb.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendBigPictureStyleNotification(Context context, NotificationManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_BIG_PICTURE_STYLE);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建大视图样式
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .setBigContentTitle("Big picture style notification ~ Expand title")
                .setSummaryText("Demo for big picture style notification ! ~ Expand summery")
                .bigPicture(BitmapFactory.decodeResource(context.getResources(), R.mipmap.big_style_picture));
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.DEFAULT)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Big picture style notification")
                //设置通知内容
                .setContentText("Demo for big picture style notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置大视图样式通知
                .setStyle(bigPictureStyle);
        //发送通知
        nm.notify(NOTIFICATION_BIG_PICTURE_STYLE, nb.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendBigTextStyleNotification(Context context, NotificationManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_BIG_TEXT_STYLE);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建大文字样式
        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle()
                .setBigContentTitle("Big text style notification ~ Expand title")
                .setSummaryText("Demo for big text style notification ! ~ Expand summery")
                .bigText("We are the champions   \n" +
                        "We are the champions   \n" +
                        "No time for losers   \n" +
                        "Cause we are the champions of the World");
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.DEFAULT)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Big text style notification")
                //设置通知内容
                .setContentText("Demo for big text style notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置大文字样式通知
                .setStyle(bigTextStyle);
        //发送通知
        nm.notify(NOTIFICATION_BIG_TEXT_STYLE, nb.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendInboxStyleNotification(Context context, NotificationManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_INBOX_STYLE);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建信箱样式
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .setBigContentTitle("Inbox style notification ~ Expand title")
                .setSummaryText("Demo for inbox style notification ! ~ Expand summery")
                //最多六行
                .addLine("1. I am email content.")
                .addLine("2. I am email content.")
                .addLine("3. I am email content.")
                .addLine("4. I am email content.")
                .addLine("5. I am email content.")
                .addLine("6. I am email content.");
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.DEFAULT)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Inbox style notification")
                //设置通知内容
                .setContentText("Demo for inbox style notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置信箱样式通知
                .setStyle(inboxStyle);
        //发送通知
        nm.notify(NOTIFICATION_INBOX_STYLE, nb.build());
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.M)
    void sendMediaStyleNotification(Context context, NotificationManager nm, boolean isPlaying) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_MEDIA_STYLE);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建Action按钮
        Intent playOrPauseIntent = new Intent(context, NotificationService.class);
        playOrPauseIntent.setAction(ACTION_MEDIA_STYLE);
        playOrPauseIntent.putExtra(EXTRA_OPTIONS, isPlaying ? MEDIA_STYLE_ACTION_PAUSE : MEDIA_STYLE_ACTION_PLAY);
        PendingIntent playOrPausePendingIntent = PendingIntent.getService(context, 0, playOrPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Action playOrPauseAction = new Notification.Action.Builder(
                Icon.createWithResource(context, isPlaying ? R.mipmap.ic_pause : R.mipmap.ic_play),
                isPlaying ? "PAUSE" : "PLAY",
                playOrPausePendingIntent)
                .build();
        Intent nextIntent = new Intent(context, NotificationService.class);
        nextIntent.setAction(ACTION_MEDIA_STYLE);
        nextIntent.putExtra(EXTRA_OPTIONS, MEDIA_STYLE_ACTION_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getService(context, 1, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Action nextAction = new Notification.Action.Builder(
                Icon.createWithResource(context, R.mipmap.ic_next),
                "Next",
                nextPendingIntent)
                .build();
        Intent deleteIntent = new Intent(context, NotificationService.class);
        deleteIntent.setAction(ACTION_MEDIA_STYLE);
        deleteIntent.putExtra(EXTRA_OPTIONS, MEDIA_STYLE_ACTION_DELETE);
        PendingIntent deletePendingIntent = PendingIntent.getService(context, 2, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Action deleteAction = new Notification.Action.Builder(
                Icon.createWithResource(context, R.mipmap.ic_delete),
                "Delete",
                deletePendingIntent)
                .build();
        //创建媒体样式
        Notification.MediaStyle mediaStyle = new Notification.MediaStyle()
                //最多三个Action
                .setShowActionsInCompactView(0, 1, 2);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.MEDIA)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Media style notification")
                //设置通知内容
                .setContentText("Demo for media style notification !")
                //设置通知不可删除
                .setOngoing(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置Action按钮
                .setActions(playOrPauseAction, nextAction, deleteAction)
                //设置信箱样式通知
                .setStyle(mediaStyle);
        //发送通知
        nm.notify(NOTIFICATION_MEDIA_STYLE, nb.build());
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendMessagingStyleNotification(Context context, NotificationManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_MESSAGING_STYLE);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建信息样式
        Notification.MessagingStyle messagingStyle = new Notification.MessagingStyle("peter")
                .setConversationTitle("Messaging style notification")
                .addMessage("This is a message for you", System.currentTimeMillis(), "peter");
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.DEFAULT)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Messaging style notification")
                //设置通知内容
                .setContentText("Demo for messaging style notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置信箱样式通知
                .setStyle(messagingStyle);
        //发送通知
        nm.notify(NOTIFICATION_MESSAGING_STYLE, nb.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void sendProgressViewNotification(Context context, NotificationManager nm, int progress) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_PROGRESS);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, 0);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.LOW)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Downloading...")
                //设置通知内容
                .setContentText(String.valueOf(progress) + "%")
                //设置通知不可删除
                .setOngoing(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                .setProgress(100, progress, false);
        //发送通知
        nm.notify(NOTIFICATION_PROGRESS, nb.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendCustomHeadsUpViewNotification(Context context, NotificationManager nm) {
        //创建点击通知时发送的广播
        //  Intent intent = new Intent(context,LaunchActivity.class);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
        //创建自定义顶部提醒视图
        Intent answerIntent = new Intent(context, NotificationService.class);
        answerIntent.setAction(ACTION_CUSTOM_HEADS_UP_VIEW);
        answerIntent.putExtra(EXTRA_OPTIONS, ACTION_ANSWER);
        PendingIntent answerPendingIntent = PendingIntent.getService(context, 0, answerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent rejectIntent = new Intent(context, NotificationService.class);
        rejectIntent.setAction(ACTION_CUSTOM_HEADS_UP_VIEW);
        rejectIntent.putExtra(EXTRA_OPTIONS, ACTION_REJECT);
        PendingIntent rejectPendingIntent = PendingIntent.getService(context, 1, rejectIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews headsUpView = new RemoteViews(context.getPackageName(), R.layout.custom_heads_up_layout);
        headsUpView.setOnClickPendingIntent(R.id.iv_answer, answerPendingIntent);
        headsUpView.setOnClickPendingIntent(R.id.iv_reject, rejectPendingIntent);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.CRITICAL)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Custom heads up notification")
                //设置通知内容
                .setContentText("Demo for custom heads up notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置全屏响应事件;
                .setFullScreenIntent(pi, true)
                //设置自定义顶部提醒视图
                .setCustomHeadsUpContentView(headsUpView);
        //发送通知
        nm.notify(NOTIFICATION_CUSTOM_HEADS_UP, nb.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void sendCustomViewNotification(Context context, NotificationManager nm, NotificationContentWrapper content, Boolean isLoved, Boolean isPlaying) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
        //创建各个按钮的点击响应广播
        Intent intentLove = new Intent(context, NotificationService.class);
        intentLove.setAction(ACTION_CUSTOM_VIEW_OPTIONS_LOVE);
        PendingIntent piLove = PendingIntent.getService(context, 0, intentLove, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentPre = new Intent(context, NotificationService.class);
        intentPre.setAction(ACTION_CUSTOM_VIEW_OPTIONS_PRE);
        PendingIntent piPre = PendingIntent.getService(context, 0, intentPre, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentPlayOrPause = new Intent(context, NotificationService.class);
        intentPlayOrPause.setAction(ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE);
        PendingIntent piPlayOrPause = PendingIntent.getService(context, 0, intentPlayOrPause, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentNext = new Intent(context, NotificationService.class);
        intentNext.setAction(ACTION_CUSTOM_VIEW_OPTIONS_NEXT);
        PendingIntent piNext = PendingIntent.getService(context, 0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentLyrics = new Intent(context, NotificationService.class);
        intentLyrics.setAction(ACTION_CUSTOM_VIEW_OPTIONS_LYRICS);
        PendingIntent piLyrics = PendingIntent.getService(context, 0, intentLyrics, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentCancel = new Intent(context, NotificationService.class);
        intentCancel.setAction(ACTION_CUSTOM_VIEW_OPTIONS_CANCEL);
        PendingIntent piCancel = PendingIntent.getService(context, 0, intentCancel, PendingIntent.FLAG_UPDATE_CURRENT);
        //创建自定义小视图
        RemoteViews customView = new RemoteViews(context.getPackageName(), R.layout.custom_view_layout);
        customView.setImageViewBitmap(R.id.iv_content, content.bitmap);
        customView.setTextViewText(R.id.tv_title, content.title);
        customView.setTextViewText(R.id.tv_summery, content.summery);
        customView.setImageViewBitmap(R.id.iv_play_or_pause, BitmapFactory.decodeResource(context.getResources(),
                isPlaying ? R.mipmap.ic_pause : R.mipmap.ic_play));
        customView.setOnClickPendingIntent(R.id.iv_play_or_pause, piPlayOrPause);
        customView.setOnClickPendingIntent(R.id.iv_next, piNext);
        customView.setOnClickPendingIntent(R.id.iv_lyrics, piLyrics);
        customView.setOnClickPendingIntent(R.id.iv_cancel, piCancel);
        //创建自定义大视图
        RemoteViews customBigView = new RemoteViews(context.getPackageName(), R.layout.custom_big_view_layout);
        customBigView.setImageViewBitmap(R.id.iv_content_big, content.bitmap);
        customBigView.setTextViewText(R.id.tv_title_big, content.title);
        customBigView.setTextViewText(R.id.tv_summery_big, content.summery);
        customBigView.setImageViewBitmap(R.id.iv_love_big, BitmapFactory.decodeResource(context.getResources(),
                isLoved ? R.mipmap.ic_loved : R.mipmap.ic_love));
        customBigView.setImageViewBitmap(R.id.iv_play_or_pause_big, BitmapFactory.decodeResource(context.getResources(),
                isPlaying ? R.mipmap.ic_pause : R.mipmap.ic_play));
        customBigView.setOnClickPendingIntent(R.id.iv_love_big, piLove);
        customBigView.setOnClickPendingIntent(R.id.iv_pre_big, piPre);
        customBigView.setOnClickPendingIntent(R.id.iv_play_or_pause_big, piPlayOrPause);
        customBigView.setOnClickPendingIntent(R.id.iv_next_big, piNext);
        customBigView.setOnClickPendingIntent(R.id.iv_lyrics_big, piLyrics);
        customBigView.setOnClickPendingIntent(R.id.iv_cancel_big, piCancel);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context, NotificationChannels.MEDIA)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_notification)
                //设置通知标题
                .setContentTitle("Custom notification")
                //设置通知内容
                .setContentText("Demo for custom notification !")
                //设置通知不可删除
                .setOngoing(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置自定义小视图
                .setCustomContentView(customView)
                //设置自定义大视图
                .setCustomBigContentView(customBigView);
        //发送通知
        nm.notify(NOTIFICATION_CUSTOM, nb.build());
    }

    public void clearAllNotification(NotificationManager nm) {
        nm.cancelAll();
    }
}
