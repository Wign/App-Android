package wign.android.wignivs.dk.wign;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Troels on 05/04/2017.
 */

public class ServiceGenerator {

    private static final String WIGN_API_URL = "http://api.wign.dk/";

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(WIGN_API_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}