package uz.alexits.cargostar.model.actor;

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
import uz.alexits.cargostar.model.location.Region;

@Entity(tableName = "user",
        foreignKeys = {
        @ForeignKey(entity = Country.class, parentColumns = "id", childColumns = "country_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)},
        indices = {
        @Index(value = {"country_id"}), @Index(value = "email")})
public abstract class User {
    @Expose
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    protected final long id;

    @Expose
    @SerializedName("user_id")
    @ColumnInfo(name = "user_id")
    protected final long userId;

    @Expose
    @SerializedName("country_id")
    @ColumnInfo(name = "country_id")
    protected final Long countryId;

    @Expose
    @SerializedName("city_name")
    @ColumnInfo(name = "city_name")
    protected final String cityName;

    @Expose
    @SerializedName("firstname")
    @ColumnInfo(name = "first_name")
    @NonNull protected final String firstName;

    @Expose
    @SerializedName("middlename")
    @ColumnInfo(name = "middle_name")
    @Nullable protected final String middleName;

    @Expose
    @SerializedName("lastname")
    @ColumnInfo(name = "last_name")
    @NonNull protected final String lastName;

    @Expose
    @SerializedName("telephone")
    @ColumnInfo(name = "phone")
    @NonNull protected final String phone;

    @Expose
    @SerializedName("email")
    @ColumnInfo(name = "email")
    @NonNull protected String email;

    @Expose
    @SerializedName("address")
    @ColumnInfo(name = "address")
    @Nullable protected String address;

    @Expose
    @SerializedName("geo")
    @ColumnInfo(name = "geo")
    @Nullable protected String geo;

    @Expose
    @SerializedName("zip")
    @ColumnInfo(name = "zip")
    @Nullable protected String zip;

    @Expose
    @SerializedName("status")
    @ColumnInfo(name = "status")
    protected int status;

    @Expose
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    @Nullable protected Date createdAt;

    @Expose
    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    @Nullable protected Date updatedAt;

    public User(final long id,
                final long userId,
                final Long countryId,
                final String cityName,
                @NonNull final String firstName,
                @Nullable final String middleName,
                @NonNull final String lastName,
                @NonNull final String phone,
                @NonNull final String email,
                @Nullable final String address,
                @Nullable final String geo,
                @Nullable final String zip,
                final int status,
                @Nullable final Date createdAt,
                @Nullable final Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.countryId = countryId;
        this.cityName = cityName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.geo = geo;
        this.zip = zip;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    @NonNull
    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getCityName() {
        return cityName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @Nullable
    public String getMiddleName() {
        return middleName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable final String address) {
        this.address = address;
    }

    @Nullable
    public String getGeo() {
        return geo;
    }

    public void setGeo(@Nullable final String geo) {
        this.geo = geo;
    }

    @Nullable
    public String getZip() {
        return zip;
    }

    public void setZip(@Nullable final String zip) {
        this.zip = zip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Nullable
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@Nullable final Date createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@Nullable final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", countryId=" + countryId +
                ", cityId=" + cityName +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", geo='" + geo + '\'' +
                ", zip='" + zip + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
