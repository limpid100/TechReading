package com.dxl.techreading.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dxl
 * @date 2018/11/12 14:22
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {

    public static String formatDateString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
