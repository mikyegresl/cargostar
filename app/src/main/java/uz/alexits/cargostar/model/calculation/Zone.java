package uz.alexits.cargostar.model.calculation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

import uz.alexits.cargostar.model.location.Country;

@Entity(tableName = "zone",
        foreignKeys = {
        @ForeignKey(entity = Country.class, parentColumns = "id", childColumns = "country_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Provider.class, parentColumns = "id", childColumns = "provider_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value = "country_id"), @Index(value = "provider_id")})
public class Zone {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final long id;

    @SerializedName("country_id")
    @Expose
    @ColumnInfo(name = "country_id")
    private final Long countryId;

    @SerializedName("provider_id")
    @Expose
    @ColumnInfo(name = "provider_id")
    private final Long providerId;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    @NonNull
    private final String name;

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

    public Zone(final long id,
                final Long countryId,
                final Long providerId,
                @NonNull final String name,
                final int status,
                @Nullable final Date createdAt,
                @Nullable final Date updatedAt) {
        this.id = id;
        this.countryId = countryId;
        this.providerId = providerId;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getProviderId() {
        return providerId;
    }

    @NonNull
    public String getName() {
        return name;
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

    @NonNull
    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", providerId=" + providerId +
                ", name='" + name + '\'' +
                '}';
    }
}
