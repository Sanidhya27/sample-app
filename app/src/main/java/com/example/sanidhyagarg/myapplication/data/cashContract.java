package com.example.sanidhyagarg.myapplication.data;

import android.provider.BaseColumns;

/**
 * Created by Sanidhya Garg on 01-07-2017.
 */

public final class cashContract {

private cashContract()
{

}
    public static final class addentry
    {
        public final static String TABLE_NAME="addmoney";
        public final static String _ID = BaseColumns._ID;
        public final static String TYPE="type";
        public final static String DATE="date";
        public final static String MONEY_ADDED ="amount";
        public final static String DESCRIPTION = "description";

    }
    public static final class spententry
    {
        public final static String TABLE_NAME="spentmoney";
        public final static String _ID = BaseColumns._ID;
        public final static String TYPE="type";
        public final static String DATE="date";
        public final static String MONEY_SPENT ="amount";
        public final static String DESCRIPTION = "description";

    }
}
