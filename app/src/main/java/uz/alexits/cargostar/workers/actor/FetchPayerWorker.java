package uz.alexits.cargostar.workers.actor;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.io.IOException;
import retrofit2.Response;
import uz.alexits.cargostar.api.RetrofitClient;
import uz.alexits.cargostar.database.cache.LocalCache;
import uz.alexits.cargostar.database.cache.SharedPrefs;
import uz.alexits.cargostar.model.actor.AddressBook;
import uz.alexits.cargostar.utils.Constants;

public class FetchPayerWorker extends Worker {
    private final long requestId;
    private final long invoiceId;
    private final String number;
    private final long providerId;
    private final long courierId;
    private final long tariffId;
    private final int deliveryType;
    private final String paymentStatus;
    private final String comment;
    private final double price;
    private final int status;

    private final long createdAtTime;
    private final long updatedAtTime;

    private final long senderId;
    private final long senderUserId;
    private final String senderEmail;
    private final String senderFirstName;
    private final String senderLastName;
    private final String senderMiddleName;
    private final String senderPhone;
    private final String senderAddress;
    private final String senderZip;
    private final long senderCountryId;
    private final String senderCityName;
    private final String senderCargo;
    private final String senderTnt;
    private final String senderFedex;
    private final String senderSignature;
    private final String senderPassport;
    private final String senderInn;
    private final String senderCompany;
    private final String senderPhoto;
    private final int senderDiscount;
    private final int senderType;

    private final long recipientId;
    private final long recipientUserId;
    private final String recipientEmail;
    private final String recipientFirstName;
    private final String recipientLastName;
    private final String recipientMiddleName;
    private final String recipientPhone;
    private final String recipientAddress;
    private final String recipientZip;
    private final long recipientCountryId;
    private final String recipientCityName;
    private final String recipientCargo;
    private final String recipientTnt;
    private final String recipientFedex;
    private final String recipientSignature;
    private final String recipientPassport;
    private final String recipientInn;
    private final String recipientCompany;
    private final int recipientType;

    private final long payerId;

    private final int consignmentQuantity;

    public FetchPayerWorker(@NonNull final Context context, @NonNull final WorkerParameters workerParams) {
        super(context, workerParams);

        this.requestId = getInputData().getLong(Constants.KEY_REQUEST_ID, -1L);
        this.invoiceId = getInputData().getLong(Constants.KEY_INVOICE_ID, -1L);
        this.number = getInputData().getString(Constants.KEY_NUMBER);
        this.providerId = getInputData().getLong(Constants.KEY_PROVIDER_ID, -1L);
        this.courierId = getInputData().getLong(Constants.KEY_COURIER_ID, -1L);
        this.tariffId = getInputData().getLong(Constants.KEY_TARIFF_ID, -1L);
        this.price = getInputData().getDouble(Constants.KEY_PRICE, -1);
        this.deliveryType = getInputData().getInt(Constants.KEY_DELIVERY_TYPE, -1);
        this.paymentStatus = getInputData().getString(Constants.KEY_PAYMENT_STATUS);
        this.comment = getInputData().getString(Constants.KEY_COMMENT);

        this.status = getInputData().getInt(Constants.KEY_STATUS, -1);
        this.createdAtTime = getInputData().getLong(Constants.KEY_CREATED_AT, -1L);
        this.updatedAtTime = getInputData().getLong(Constants.KEY_UPDATED_AT, -1L);

        this.senderId = getInputData().getLong(Constants.KEY_SENDER_ID, -1L);
        this.senderUserId = getInputData().getLong(Constants.KEY_SENDER_USER_ID, -1L);
        this.senderEmail = getInputData().getString(Constants.KEY_SENDER_EMAIL);
        this.senderFirstName = getInputData().getString(Constants.KEY_SENDER_FIRST_NAME);
        this.senderLastName = getInputData().getString(Constants.KEY_SENDER_LAST_NAME);
        this.senderMiddleName = getInputData().getString(Constants.KEY_SENDER_MIDDLE_NAME);
        this.senderPhone = getInputData().getString(Constants.KEY_SENDER_PHONE);
        this.senderAddress = getInputData().getString(Constants.KEY_SENDER_ADDRESS);
        this.senderCountryId = getInputData().getLong(Constants.KEY_SENDER_COUNTRY_ID, -1L);
        this.senderCityName = getInputData().getString(Constants.KEY_SENDER_CITY_NAME);
        this.senderZip = getInputData().getString(Constants.KEY_SENDER_ZIP);
        this.senderCargo = getInputData().getString(Constants.KEY_SENDER_CARGOSTAR);
        this.senderTnt = getInputData().getString(Constants.KEY_SENDER_TNT);
        this.senderFedex = getInputData().getString(Constants.KEY_SENDER_FEDEX);
        this.senderDiscount = getInputData().getInt(Constants.KEY_DISCOUNT, 0);
        this.senderSignature = getInputData().getString(Constants.KEY_SENDER_SIGNATURE);
        this.senderPassport = getInputData().getString(Constants.KEY_SENDER_PASSPORT);
        this.senderInn = getInputData().getString(Constants.KEY_SENDER_INN);
        this.senderCompany = getInputData().getString(Constants.KEY_SENDER_COMPANY_NAME);
        this.senderPhoto = getInputData().getString(Constants.KEY_SENDER_PHOTO);
        this.senderType = getInputData().getInt(Constants.KEY_SENDER_TYPE, 0);

        this.recipientId = getInputData().getLong(Constants.KEY_RECIPIENT_ID, -1L);
        this.recipientUserId = getInputData().getLong(Constants.KEY_RECIPIENT_USER_ID, -1L);
        this.recipientEmail = getInputData().getString(Constants.KEY_RECIPIENT_EMAIL);
        this.recipientFirstName = getInputData().getString(Constants.KEY_RECIPIENT_FIRST_NAME);
        this.recipientLastName = getInputData().getString(Constants.KEY_RECIPIENT_LAST_NAME);
        this.recipientMiddleName = getInputData().getString(Constants.KEY_RECIPIENT_MIDDLE_NAME);
        this.recipientPhone = getInputData().getString(Constants.KEY_RECIPIENT_PHONE);
        this.recipientAddress = getInputData().getString(Constants.KEY_RECIPIENT_ADDRESS);
        this.recipientZip = getInputData().getString(Constants.KEY_RECIPIENT_ZIP);
        this.recipientCountryId = getInputData().getLong(Constants.KEY_RECIPIENT_COUNTRY_ID, -1L);
        this.recipientCityName = getInputData().getString(Constants.KEY_RECIPIENT_CITY_NAME);
        this.recipientCargo = getInputData().getString(Constants.KEY_RECIPIENT_CARGOSTAR);
        this.recipientTnt = getInputData().getString(Constants.KEY_RECIPIENT_TNT);
        this.recipientFedex = getInputData().getString(Constants.KEY_RECIPIENT_FEDEX);
        this.recipientSignature = getInputData().getString(Constants.KEY_RECIPIENT_SIGNATURE);
        this.recipientPassport = getInputData().getString(Constants.KEY_RECIPIENT_PASSPORT);
        this.recipientInn = getInputData().getString(Constants.KEY_RECIPIENT_INN);
        this.recipientCompany = getInputData().getString(Constants.KEY_RECIPIENT_COMPANY_NAME);
        this.recipientType = getInputData().getInt(Constants.KEY_RECIPIENT_TYPE, 0);

        this.payerId = getInputData().getLong(Constants.KEY_PAYER_ID, -1L);

        this.consignmentQuantity = getInputData().getInt(Constants.KEY_CONSIGNMENT_QUANTITY, 0);
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {
        if (invoiceId <= 0) {
            Log.e(TAG, "fetchPayerDataWorker(): invoiceId <= 0");
            return Result.failure();
        }
        if (providerId <= 0) {
            Log.e(TAG, "fetchPayerDataWorker(): providerId <= 0");
            return Result.failure();
        }
        if (requestId <= 0) {
            Log.e(TAG, "fetchPayerDataWorker(): requestId <= 0");
            return Result.failure();
        }
        if (recipientId <= 0) {
            Log.e(TAG, "fetchPayerDataWorker(): recipientId <= 0");
            return Result.failure();
        }
        if (payerId <= 0) {
            Log.e(TAG, "fetchPayerDataWorker(): payerId <= 0");
            return Result.failure();
        }
        try {
            RetrofitClient.getInstance(getApplicationContext())
                    .setServerData(SharedPrefs.getInstance(getApplicationContext()).getString(Constants.KEY_LOGIN),
                            SharedPrefs.getInstance(getApplicationContext()).getString(Constants.KEY_PASSWORD));
            final Response<AddressBook> response = RetrofitClient.getInstance(getApplicationContext()).getAddressBookEntry(payerId);

            if (response.code() == 200) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "fetchPayerDataWorker(): response=" + response.body());
                    final AddressBook payer = response.body();

                    if (payer == null) {
                        Log.e(TAG, "fetchPayerDataWorker(): payer is NULL");
                        return Result.failure();
                    }

                    final long rowInserted = LocalCache.getInstance(getApplicationContext()).invoiceDao().insertAddressBookEntry(payer);

                    if (rowInserted == -1) {
                        Log.e(TAG, "fetchPayerDataWorker(): couldn't insert entry " + payer);
                        return Result.failure();
                    }

                    final Data outputData = new Data.Builder()
                            .putLong(Constants.KEY_REQUEST_ID, requestId)
                            .putLong(Constants.KEY_INVOICE_ID, invoiceId)
                            .putString(Constants.KEY_NUMBER, number)
                            .putLong(Constants.KEY_PROVIDER_ID, providerId)
                            .putLong(Constants.KEY_COURIER_ID, courierId)
                            .putLong(Constants.KEY_TARIFF_ID, tariffId)
                            .putDouble(Constants.KEY_PRICE, price)
                            .putInt(Constants.KEY_DELIVERY_TYPE, deliveryType)
                            .putString(Constants.KEY_PAYMENT_STATUS, paymentStatus)
                            .putString(Constants.KEY_COMMENT, comment)

                            .putLong(Constants.KEY_STATUS, status)
                            .putLong(Constants.KEY_CREATED_AT, createdAtTime)
                            .putLong(Constants.KEY_UPDATED_AT, updatedAtTime)

                            .putLong(Constants.KEY_SENDER_ID, senderId)
                            .putLong(Constants.KEY_SENDER_USER_ID, senderUserId)
                            .putString(Constants.KEY_SENDER_EMAIL, senderEmail)
                            .putString(Constants.KEY_SENDER_SIGNATURE, senderSignature)
                            .putString(Constants.KEY_SENDER_FIRST_NAME, senderFirstName)
                            .putString(Constants.KEY_SENDER_LAST_NAME, senderLastName)
                            .putString(Constants.KEY_SENDER_MIDDLE_NAME, senderMiddleName)
                            .putString(Constants.KEY_SENDER_PHONE, senderPhone)
                            .putString(Constants.KEY_SENDER_ADDRESS, senderAddress)
                            .putString(Constants.KEY_SENDER_ZIP, senderZip)
                            .putLong(Constants.KEY_SENDER_COUNTRY_ID, senderCountryId)
                            .putString(Constants.KEY_SENDER_CITY_NAME, senderCityName)
                            .putString(Constants.KEY_SENDER_CARGOSTAR, senderCargo)
                            .putString(Constants.KEY_SENDER_TNT, senderTnt)
                            .putString(Constants.KEY_SENDER_FEDEX, senderFedex)
                            .putInt(Constants.KEY_DISCOUNT, senderDiscount)
                            .putString(Constants.KEY_SENDER_PASSPORT, senderPassport)
                            .putString(Constants.KEY_SENDER_COMPANY_NAME, senderCompany)
                            .putString(Constants.KEY_SENDER_INN, senderInn)
                            .putString(Constants.KEY_SENDER_PHOTO, senderPhoto)
                            .putInt(Constants.KEY_SENDER_TYPE, senderType)

                            .putLong(Constants.KEY_RECIPIENT_ID, recipientId)
                            .putLong(Constants.KEY_RECIPIENT_USER_ID, recipientUserId)
                            .putString(Constants.KEY_RECIPIENT_EMAIL, recipientEmail)
                            .putString(Constants.KEY_RECIPIENT_FIRST_NAME, recipientFirstName)
                            .putString(Constants.KEY_RECIPIENT_LAST_NAME, recipientLastName)
                            .putString(Constants.KEY_RECIPIENT_MIDDLE_NAME, recipientMiddleName)
                            .putString(Constants.KEY_RECIPIENT_PHONE, recipientPhone)
                            .putString(Constants.KEY_RECIPIENT_ADDRESS, recipientAddress)
                            .putString(Constants.KEY_RECIPIENT_ZIP, recipientZip)
                            .putLong(Constants.KEY_RECIPIENT_COUNTRY_ID, recipientCountryId)
                            .putString(Constants.KEY_RECIPIENT_CITY_NAME, recipientCityName)
                            .putString(Constants.KEY_RECIPIENT_CARGOSTAR, recipientCargo)
                            .putString(Constants.KEY_RECIPIENT_TNT, recipientTnt)
                            .putString(Constants.KEY_RECIPIENT_FEDEX, recipientFedex)
                            .putString(Constants.KEY_RECIPIENT_SIGNATURE, recipientSignature)
                            .putString(Constants.KEY_RECIPIENT_PASSPORT, recipientPassport)
                            .putString(Constants.KEY_RECIPIENT_INN, recipientInn)
                            .putString(Constants.KEY_RECIPIENT_COMPANY_NAME, recipientCompany)
                            .putInt(Constants.KEY_RECIPIENT_TYPE, recipientType)

                            .putLong(Constants.KEY_PAYER_ID, payerId)
                            .putLong(Constants.KEY_PAYER_USER_ID, payer.getUserId())
                            .putString(Constants.KEY_PAYER_EMAIL, payer.getEmail())
                            .putString(Constants.KEY_PAYER_FIRST_NAME, payer.getFirstName())
                            .putString(Constants.KEY_PAYER_LAST_NAME, payer.getLastName())
                            .putString(Constants.KEY_PAYER_MIDDLE_NAME, payer.getMiddleName())
                            .putString(Constants.KEY_PAYER_PHONE, payer.getPhone())
                            .putString(Constants.KEY_PAYER_ADDRESS, payer.getAddress())
                            .putString(Constants.KEY_PAYER_ZIP, payer.getZip())
                            .putLong(Constants.KEY_PAYER_COUNTRY_ID, payer.getCountryId())
                            .putString(Constants.KEY_PAYER_CITY_NAME, payer.getCityName())
                            .putString(Constants.KEY_PAYER_CARGOSTAR, payer.getCargostarAccountNumber())
                            .putString(Constants.KEY_PAYER_TNT, payer.getTntAccountNumber())
                            .putString(Constants.KEY_PAYER_FEDEX, payer.getFedexAccountNumber())
                            .putString(Constants.KEY_PAYER_CONTRACT_NUMBER, payer.getContractNumber())
                            .putString(Constants.KEY_PAYER_PASSPORT, payer.getPassportSerial())
                            .putString(Constants.KEY_PAYER_INN, payer.getInn())
                            .putString(Constants.KEY_PAYER_COMPANY_NAME, payer.getCompany())
                            .putInt(Constants.KEY_PAYER_TYPE, payer.getType())

                            .putInt(Constants.KEY_CONSIGNMENT_QUANTITY, consignmentQuantity)
                            .build();

                    Log.i(TAG, "fetchRecipientDataWorker(): successfully inserted entry " + payer);
                    return Result.success(outputData);
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

    private static final String TAG = FetchPayerWorker.class.toString();
}
