package com.example.to.data;

import android.provider.BaseColumns;

/**
 * Created by lenovo on 2/20/2017.
 */

public final class Contract {
    private Contract() {
    }

    public static final class ToDoEntry implements BaseColumns {

        public final static String TABLE_NAME = "todo";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TITLE = "title";
        public final static String COLUMN_DESCRIPTION = "description";

    }

}