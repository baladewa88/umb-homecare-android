package umbandung.com.digitalhomecare.Rest;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arkhan on 9/8/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://167.205.7.227:9028";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(final String token) {
        if (retrofit==null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request().newBuilder().addHeader("Authorization", token).build();
                    Log.e("LOG WOLOLO", token);
                    return chain.proceed(original);
                }
            });
            OkHttpClient client = httpClient.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
