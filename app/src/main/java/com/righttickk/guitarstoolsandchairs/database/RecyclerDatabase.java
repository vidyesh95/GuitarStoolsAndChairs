package com.righttickk.guitarstoolsandchairs.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.righttickk.guitarstoolsandchairs.R;
import com.righttickk.guitarstoolsandchairs.dao.RecyclerDao;
import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

@Database(entities = {RecyclerViewItem.class}, version = 1)
public abstract class RecyclerDatabase extends RoomDatabase {

    private static RecyclerDatabase instance;

    public abstract RecyclerDao recyclerDao();

    public static synchronized RecyclerDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecyclerDatabase.class, "recycler_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private RecyclerDao recyclerDao;

        public PopulateDbAsyncTask(RecyclerDatabase db) {
            recyclerDao = db.recyclerDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_1,
                    "On-Stage DT7500 Guitarist Stool With Foot Rest - Black",
                    "₹2,699", "bajaao.com"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_2,
                    "lbanez IMC50FS Chair Guitar Stand",
                    "₹2,662", "Bajaao.com"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_3,
                    "GTB RED Furniture - Adjustable Stool-Doctor/Kitchen Stool/Office Stool/" +
                            "Chair/Cafeteria Stool/Bar Stool",
                    "₹1,199", "Amazon.com"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_4,
                    "MBTC Essilor PU 360° Height Adjustable Kitchen/Office/Bar Stool in White",
                    "₹2,199", "Amazon.com"));
            return null;
        }
    }
}
