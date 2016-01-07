package com.hpe.simpleconsumer;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Person implements Parcelable {

    //The naming scheme for these variables is due to gson
    @SerializedName("firstName")
    private String mFirstName;

    @SerializedName("lastName")
    private String mLastName;

    @SerializedName("gender")
    private String mGender;


    /*
     * Default constructor for general object creation
     */
    public Person() {

    }

    /*
     * Constructor needed for parcelable object creation
     */
    public Person(Parcel item) {
        mFirstName = item.readString();
        mLastName = item.readString();
        mGender = item.readString();
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    /*
     * Used to generate parcelable classes from a parcel
     */
    public static final Parcelable.Creator<Person> CREATOR
            = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    /*
     * Needed to implement parcelable
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /*
     * Place the data into the parcel
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mFirstName);
        parcel.writeString(mLastName);
        parcel.writeString(mGender);
    }
}
