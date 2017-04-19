package wign.android.wignivs.dk.wign;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Troels on 11/04/2017.
 */

public class getAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
    private ApiService apiService;

    public getAllWordsAsyncTask(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Realm realm = null;

        try {
            realm = Realm.getDefaultInstance();
            Call<List<Word>> call = apiService.getAllWords(); // Defining the call to fetch all words from Wign API
            Response<List<Word>> response = call.execute(); // Sync download

            final List<Word> wordList = response.body();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(wordList);
                    System.out.println("API successful. Data stored in realm.");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        return null;
    }

}
