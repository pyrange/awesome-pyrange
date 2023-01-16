package com.pyrange.awesome.util;

import com.intellij.notification.*;

public class MessageUtil {

    private static NotificationGroup pyrange_notification_group = new NotificationGroup("Awesome-Pyrange",
            NotificationDisplayType.BALLOON, true);

    public static void showSuccessMsg(String msg) {
        Notification notification = pyrange_notification_group.createNotification("Awesome-Pyrange message", msg, NotificationType.INFORMATION, null);
        Notifications.Bus.notify(notification);
    }

    public static void showErrorMsg(String msg) {
        Notification notification = pyrange_notification_group.createNotification("Awesome-Pyrange Error Message", msg, NotificationType.ERROR, null);
        Notifications.Bus.notify(notification);
    }
}
