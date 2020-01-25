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
                    "₹2,699", "bajaao.com",
                    "https://www.bajaao.com/products/on-stage-dt7500-guitarist-stool-with-foot-rest-black?variant=12963493707848&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8xSvKRC7j5tt-2OWuHOF6h6pE_kCDneMNvzvJYC5glJiQ9ZMSd5FBMaAtwEEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_2,
                    "lbanez IMC50FS Chair Guitar Stand",
                    "₹2,662", "Bajaao.com",
                    "https://www.bajaao.com/products/ibanez-imc50fs-chair-guitar-stand?variant=6442051331&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8wmzlyAIUM_DqveTr_xA5UIt1wBKkDO7HYcH494Dw8PAi-41GGoRL8aAq4dEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_3,
                    "GTB RED Furniture - Adjustable Stool-Doctor/Kitchen Stool/Office Stool/" +
                            "Chair/Cafeteria Stool/Bar Stool",
                    "₹1,199", "Amazon.com",
                    "https://www.amazon.in/GTB-RED-Furniture-Adjustable-Stool-Doctor/dp/B07M73FGKP"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_4,
                    "MBTC Essilor PU 360° Height Adjustable Kitchen/Office/Bar Stool in White",
                    "₹2,199", "Amazon.com",
                    "https://www.amazon.in/MBTC-Magma-Stool-Chair-Black/dp/B01FW26BBW/ref=sr_1_5?adgrpid=58097826919&ext_vrnc=hi&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8zQ07TPOJzj1zAeIeRCsNV44qhKkbyKJKDseQbZMvl8ZATtxKUX7VUaAsBZEALw_wcB&hvadid=381515821116&hvdev=c&hvlocphy=9040242&hvnetw=g&hvpos=1t1&hvqmt=b&hvrand=3460305249859134899&hvtargid=kwd-428148509037&hydadcr=10513_1908233&keywords=mbtc+bar+stool&qid=1579933714&smid=AT95IG9ONZD7S&sr=8-5"));
            // Extra for testing
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_1,
                    "On-Stage DT7500 Guitarist Stool With Foot Rest - Black",
                    "₹2,699", "bajaao.com",
                    "https://www.bajaao.com/products/on-stage-dt7500-guitarist-stool-with-foot-rest-black?variant=12963493707848&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8xSvKRC7j5tt-2OWuHOF6h6pE_kCDneMNvzvJYC5glJiQ9ZMSd5FBMaAtwEEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_2,
                    "lbanez IMC50FS Chair Guitar Stand",
                    "₹2,662", "Bajaao.com",
                    "https://www.bajaao.com/products/ibanez-imc50fs-chair-guitar-stand?variant=6442051331&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8wmzlyAIUM_DqveTr_xA5UIt1wBKkDO7HYcH494Dw8PAi-41GGoRL8aAq4dEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_3,
                    "GTB RED Furniture - Adjustable Stool-Doctor/Kitchen Stool/Office Stool/" +
                            "Chair/Cafeteria Stool/Bar Stool",
                    "₹1,199", "Amazon.com",
                    "https://www.amazon.in/GTB-RED-Furniture-Adjustable-Stool-Doctor/dp/B07M73FGKP"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_4,
                    "MBTC Essilor PU 360° Height Adjustable Kitchen/Office/Bar Stool in White",
                    "₹2,199", "Amazon.com",
                    "https://www.amazon.in/MBTC-Magma-Stool-Chair-Black/dp/B01FW26BBW/ref=sr_1_5?adgrpid=58097826919&ext_vrnc=hi&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8zQ07TPOJzj1zAeIeRCsNV44qhKkbyKJKDseQbZMvl8ZATtxKUX7VUaAsBZEALw_wcB&hvadid=381515821116&hvdev=c&hvlocphy=9040242&hvnetw=g&hvpos=1t1&hvqmt=b&hvrand=3460305249859134899&hvtargid=kwd-428148509037&hydadcr=10513_1908233&keywords=mbtc+bar+stool&qid=1579933714&smid=AT95IG9ONZD7S&sr=8-5"));

            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_1,
                    "On-Stage DT7500 Guitarist Stool With Foot Rest - Black",
                    "₹2,699", "bajaao.com",
                    "https://www.bajaao.com/products/on-stage-dt7500-guitarist-stool-with-foot-rest-black?variant=12963493707848&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8xSvKRC7j5tt-2OWuHOF6h6pE_kCDneMNvzvJYC5glJiQ9ZMSd5FBMaAtwEEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_2,
                    "lbanez IMC50FS Chair Guitar Stand",
                    "₹2,662", "Bajaao.com",
                    "https://www.bajaao.com/products/ibanez-imc50fs-chair-guitar-stand?variant=6442051331&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8wmzlyAIUM_DqveTr_xA5UIt1wBKkDO7HYcH494Dw8PAi-41GGoRL8aAq4dEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_3,
                    "GTB RED Furniture - Adjustable Stool-Doctor/Kitchen Stool/Office Stool/" +
                            "Chair/Cafeteria Stool/Bar Stool",
                    "₹1,199", "Amazon.com",
                    "https://www.amazon.in/GTB-RED-Furniture-Adjustable-Stool-Doctor/dp/B07M73FGKP"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_4,
                    "MBTC Essilor PU 360° Height Adjustable Kitchen/Office/Bar Stool in White",
                    "₹2,199", "Amazon.com",
                    "https://www.amazon.in/MBTC-Magma-Stool-Chair-Black/dp/B01FW26BBW/ref=sr_1_5?adgrpid=58097826919&ext_vrnc=hi&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8zQ07TPOJzj1zAeIeRCsNV44qhKkbyKJKDseQbZMvl8ZATtxKUX7VUaAsBZEALw_wcB&hvadid=381515821116&hvdev=c&hvlocphy=9040242&hvnetw=g&hvpos=1t1&hvqmt=b&hvrand=3460305249859134899&hvtargid=kwd-428148509037&hydadcr=10513_1908233&keywords=mbtc+bar+stool&qid=1579933714&smid=AT95IG9ONZD7S&sr=8-5"));

            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_1,
                    "On-Stage DT7500 Guitarist Stool With Foot Rest - Black",
                    "₹2,699", "bajaao.com",
                    "https://www.bajaao.com/products/on-stage-dt7500-guitarist-stool-with-foot-rest-black?variant=12963493707848&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8xSvKRC7j5tt-2OWuHOF6h6pE_kCDneMNvzvJYC5glJiQ9ZMSd5FBMaAtwEEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_2,
                    "lbanez IMC50FS Chair Guitar Stand",
                    "₹2,662", "Bajaao.com",
                    "https://www.bajaao.com/products/ibanez-imc50fs-chair-guitar-stand?variant=6442051331&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8wmzlyAIUM_DqveTr_xA5UIt1wBKkDO7HYcH494Dw8PAi-41GGoRL8aAq4dEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_3,
                    "GTB RED Furniture - Adjustable Stool-Doctor/Kitchen Stool/Office Stool/" +
                            "Chair/Cafeteria Stool/Bar Stool",
                    "₹1,199", "Amazon.com",
                    "https://www.amazon.in/GTB-RED-Furniture-Adjustable-Stool-Doctor/dp/B07M73FGKP"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_4,
                    "MBTC Essilor PU 360° Height Adjustable Kitchen/Office/Bar Stool in White",
                    "₹2,199", "Amazon.com",
                    "https://www.amazon.in/MBTC-Magma-Stool-Chair-Black/dp/B01FW26BBW/ref=sr_1_5?adgrpid=58097826919&ext_vrnc=hi&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8zQ07TPOJzj1zAeIeRCsNV44qhKkbyKJKDseQbZMvl8ZATtxKUX7VUaAsBZEALw_wcB&hvadid=381515821116&hvdev=c&hvlocphy=9040242&hvnetw=g&hvpos=1t1&hvqmt=b&hvrand=3460305249859134899&hvtargid=kwd-428148509037&hydadcr=10513_1908233&keywords=mbtc+bar+stool&qid=1579933714&smid=AT95IG9ONZD7S&sr=8-5"));

            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_1,
                    "On-Stage DT7500 Guitarist Stool With Foot Rest - Black",
                    "₹2,699", "bajaao.com",
                    "https://www.bajaao.com/products/on-stage-dt7500-guitarist-stool-with-foot-rest-black?variant=12963493707848&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8xSvKRC7j5tt-2OWuHOF6h6pE_kCDneMNvzvJYC5glJiQ9ZMSd5FBMaAtwEEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_2,
                    "lbanez IMC50FS Chair Guitar Stand",
                    "₹2,662", "Bajaao.com",
                    "https://www.bajaao.com/products/ibanez-imc50fs-chair-guitar-stand?variant=6442051331&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8wmzlyAIUM_DqveTr_xA5UIt1wBKkDO7HYcH494Dw8PAi-41GGoRL8aAq4dEALw_wcB"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_3,
                    "GTB RED Furniture - Adjustable Stool-Doctor/Kitchen Stool/Office Stool/" +
                            "Chair/Cafeteria Stool/Bar Stool",
                    "₹1,199", "Amazon.com",
                    "https://www.amazon.in/GTB-RED-Furniture-Adjustable-Stool-Doctor/dp/B07M73FGKP"));
            recyclerDao.insert(new RecyclerViewItem(R.drawable.guitar_item_4,
                    "MBTC Essilor PU 360° Height Adjustable Kitchen/Office/Bar Stool in White",
                    "₹2,199", "Amazon.com",
                    "https://www.amazon.in/MBTC-Magma-Stool-Chair-Black/dp/B01FW26BBW/ref=sr_1_5?adgrpid=58097826919&ext_vrnc=hi&gclid=Cj0KCQiAyKrxBRDHARIsAKCzn8zQ07TPOJzj1zAeIeRCsNV44qhKkbyKJKDseQbZMvl8ZATtxKUX7VUaAsBZEALw_wcB&hvadid=381515821116&hvdev=c&hvlocphy=9040242&hvnetw=g&hvpos=1t1&hvqmt=b&hvrand=3460305249859134899&hvtargid=kwd-428148509037&hydadcr=10513_1908233&keywords=mbtc+bar+stool&qid=1579933714&smid=AT95IG9ONZD7S&sr=8-5"));
            return null;
        }
    }
}
