package uz.alexits.cargostar.model.calculation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

@Entity(tableName = "packaging",
        foreignKeys = {
                @ForeignKey(entity = Provider.class, parentColumns = "id", childColumns = "provider_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value = "provider_id")})
public class Packaging {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    @SerializedName("provider_id")
    @Expose
    @ColumnInfo(name = "provider_id")
    private long providerId;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    @NonNull
    private final String name;

    @SerializedName("parcel_fee")
    @Expose
    @ColumnInfo(name = "parcel_fee")
    private final long parcelFee;

    @SerializedName("volumex")
    @Expose
    @ColumnInfo(name = "volumex")
    private final int volumex;

    @SerializedName("status")
    @Expose
    @ColumnInfo(name = "status")
    private final int status;

    @SerializedName("created_at")
    @Expose
    @ColumnInfo(name = "created_at")
    @Nullable
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    @ColumnInfo(name = "updated_at")
    @Nullable
    private Date updatedAt;

    public Packaging(final long id,
                     final long providerId,
                     @NonNull final String name,
                     final long parcelFee,
                     final int volumex,
                     final int status,
                     @Nullable final Date createdAt,
                     @Nullable final Date updatedAt) {
        this.id = id;
        this.providerId = providerId;
        this.name = name;
        this.parcelFee = parcelFee;
        this.volumex = volumex;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Ignore
    public Packaging(final long id,
                     final long providerId,
                     @NonNull final String name,
                     final int volumex,
                     final int status) {
        this.id = id;
        this.providerId = providerId;
        this.name = name;
        this.parcelFee = 0;
        this.volumex = volumex;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public long isParcelFree() {
        return parcelFee;
    }

    public int getVolumex() {
        return volumex;
    }

    public int getStatus() {
        return status;
    }

    @Nullable
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@Nullable Date createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@Nullable Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getParcelFee() {
        return parcelFee;
    }

    @NonNull
    @Override
    public String toString() {
        return "Packaging{" +
                "id=" + id +
                ", providerId=" + providerId +
                ", name='" + name + '\'' +
                ", parcelFee=" + parcelFee +
                ", volumex='" + volumex + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}