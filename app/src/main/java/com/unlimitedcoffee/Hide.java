package com.unlimitedcoffee;

import android.util.Base64;

public class Hide {
        private static byte [] eKey = Base64.decode("dDNzdDFuZzE=", Base64.DEFAULT);
        private static byte[] databaseName = Base64.decode("VXNlck1hbmFnZW1lbnQuZGI=", Base64.DEFAULT);

        static byte[] getKey() {
            return eKey;
        }
        static byte[] getDatabaseName() {return databaseName;}
    }

