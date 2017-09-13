package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by gobov on 6/22/2017.
 */

public final class MacroContract {

    /* an abstract inner class for the created table */
    public static abstract class MacroEntry implements BaseColumns {

        /* table title constant */
        public static final String TABLE_NAME = "macros";

        /* input id constant */
        public static final String _ID = BaseColumns._ID;

        /* column titles constants */
        public static final String COLUMN_DAY = "day";
        public static final String COLUMN_PROTEIN = "protein";
        public static final String COLUMN_FAT = "fat";
        public static final String COLUMN_CARB = "carb";
        public static final String COLUMN_TOTAL = "total";

    }
}
