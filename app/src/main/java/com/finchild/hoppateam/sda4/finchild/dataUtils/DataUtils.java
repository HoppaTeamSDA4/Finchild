package com.finchild.hoppateam.sda4.finchild.dataUtils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class DataUtils {
    public static boolean isEmail(String email, Context context) {
        boolean isEmail = true;
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(context, "Please enter email", Toast.LENGTH_LONG).show();
            isEmail = false;
        } else if (TextUtils.getTrimmedLength(email) > 50) {
            Toast.makeText(context, "Email can not be longer than 50 characters", Toast.LENGTH_LONG).show();
            isEmail = false;
        }
        System.out.println(TextUtils.getTrimmedLength(email));
        return isEmail;
    }
}
