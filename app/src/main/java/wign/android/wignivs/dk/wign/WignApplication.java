package wign.android.wignivs.dk.wign;

import android.app.Application;

import im.ene.toro.Toro;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by Troels on 11/04/2017.
 */

public class WignApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        buildDatabase(realmConfiguration); // Resetting the database
        Realm.setDefaultConfiguration(realmConfiguration);

        Toro.init(this);
    }

    public Realm buildDatabase(RealmConfiguration realmConfiguration){
        try {
            return Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e){
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                return Realm.getInstance(realmConfiguration);
            } catch (Exception ex){
                throw ex;
                //No Realm file to remove.
            }
        }
    }
}