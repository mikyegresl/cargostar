package uz.alexits.cargostar.workers.packaging;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import uz.alexits.cargostar.api.RetrofitClient;
import uz.alexits.cargostar.model.packaging.Provider;

import java.io.IOException;
import java.util.List;
import retrofit2.Response;

public class FetchProvidersWorker extends Worker {
    private static final String TAG = FetchProvidersWorker.class.toString();

    public FetchProvidersWorker(@NonNull final Context context, @NonNull final WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {
        try {
            final Response<List<Provider>> response = RetrofitClient.getInstance(getApplicationContext()).getProviders();

            if (response.code() == 200) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "fetchAllProviders(): response=" + response.body());
                    final List<Provider> providerList = response.body();
//                    LocalCache.getInstance(getApplicationContext()).packagingDao().insertProviders(providerList);
                    return ListenableWorker.Result.success();
                }
            }
            else {
                Log.e(TAG, "doWork(): " + response.errorBody());
            }
            return ListenableWorker.Result.failure();
        }
        catch (IOException e) {
            Log.e(TAG, "doWork(): ", e);
            return ListenableWorker.Result.failure();
        }
    }
}
