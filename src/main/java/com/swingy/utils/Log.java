package com.swingy.utils;

import static com.swingy.utils.StaticGlobal.CONSOLE_MODE;

public class Log {
    public static void log(String message) {
        if (CONSOLE_MODE == true) {
            System.out.println(message);
        }
    }
}
