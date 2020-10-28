package uz.alexits.cargostar.workers.requests;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import uz.alexits.cargostar.api.RetrofitClient;
import uz.alexits.cargostar.database.cache.LocalCache;
import uz.alexits.cargostar.model.shipping.Request;

import java.io.IOException;
import java.util.List;
import retrofit2.Response;

public class FetchRequestsWorker extends Worker {
    public FetchRequestsWorker(@NonNull final Context context, @NonNull final WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {
        try {
            final Response<List<Request>> response = RetrofitClient.getInstance(getApplicationContext()).getPublicRequests();

            if (response.code() == 200) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "fetchAllRequests(): response=" + response.body());
                    final List<Request> publicRequestList = response.body();

                    for (final Request request : publicRequestList) {
                        Log.i(TAG, "to be inserted request=" + request);
                        LocalCache.getInstance(getApplicationContext()).requestDao().insertRequest(request);
                    }
//                    LocalCache.getInstance(getApplicationContext()).requestDao().insertRequests(publicRequestList);
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

    private static final String TAG = FetchRequestsWorker.class.toString();
}
