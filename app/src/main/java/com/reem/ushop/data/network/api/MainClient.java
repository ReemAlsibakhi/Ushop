package com.reem.ushop.data.network.api;

import android.util.ArrayMap;
import android.util.Log;
import com.reem.ushop.pojo.Category;
import com.reem.ushop.pojo.ResponseGet;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainClient {

    private MainRetrofit mainRetrofit;
    private static MainClient mainClient;

    public MainClient() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.readTimeout(90, TimeUnit.SECONDS);
        httpBuilder.connectTimeout(90, TimeUnit.SECONDS);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpBuilder.addInterceptor(interceptor);
        final ConnectionSpec.Builder connectionSpec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
        connectionSpec.tlsVersions(TlsVersion.TLS_1_2).build();
        TLSSocketFactory tlsSocketFactory;
        try {
            tlsSocketFactory = new TLSSocketFactory();
            httpBuilder.sslSocketFactory(tlsSocketFactory, tlsSocketFactory.systemDefaultTrustManager());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            Log.d("TAG", "Failed to create Socket connection ");
            e.printStackTrace();
        }
        final OkHttpClient client = httpBuilder
                .addNetworkInterceptor(interceptor)
                .connectionSpecs(Collections.singletonList(connectionSpec.build()))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mainRetrofit = retrofit.create(MainRetrofit.class);
    }

    public static MainClient getInstance() {
        if (mainClient == null) {
            mainClient = new MainClient();
        }
        return mainClient;
    }

    public Call<ResponseGet<ArrayList<Category>>> getCats(ArrayMap<String, Object> map) {
        return mainRetrofit.getCats(map);
    }

}
