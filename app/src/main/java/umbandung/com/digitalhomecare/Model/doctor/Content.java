
package umbandung.com.digitalhomecare.Model.doctor;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Content {

    @SerializedName("acceptanceStatus")
    private Boolean mAcceptanceStatus;
    @SerializedName("date")
    private Object mDate;
    @SerializedName("day")
    private Object mDay;
    @SerializedName("id")
    private Long mId;
    @SerializedName("idDoctor")
    private IdDoctor mIdDoctor;
    @SerializedName("idTransaction")
    private IdTransaction mIdTransaction;
    @SerializedName("rateStatus")
    private Boolean mRateStatus;
    @SerializedName("reasonAcceptanceStatus")
    private Object mReasonAcceptanceStatus;
    @SerializedName("time")
    private Object mTime;
    @SerializedName("treatement")
    private Object mTreatement;

    public Boolean getAcceptanceStatus() {
        return mAcceptanceStatus;
    }

    public void setAcceptanceStatus(Boolean acceptanceStatus) {
        mAcceptanceStatus = acceptanceStatus;
    }

    public Object getDate() {
        return mDate;
    }

    public void setDate(Object date) {
        mDate = date;
    }

    public Object getDay() {
        return mDay;
    }

    public void setDay(Object day) {
        mDay = day;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public IdDoctor getIdDoctor() {
        return mIdDoctor;
    }

    public void setIdDoctor(IdDoctor idDoctor) {
        mIdDoctor = idDoctor;
    }

    public IdTransaction getIdTransaction() {
        return mIdTransaction;
    }

    public void setIdTransaction(IdTransaction idTransaction) {
        mIdTransaction = idTransaction;
    }

    public Boolean getRateStatus() {
        return mRateStatus;
    }

    public void setRateStatus(Boolean rateStatus) {
        mRateStatus = rateStatus;
    }

    public Object getReasonAcceptanceStatus() {
        return mReasonAcceptanceStatus;
    }

    public void setReasonAcceptanceStatus(Object reasonAcceptanceStatus) {
        mReasonAcceptanceStatus = reasonAcceptanceStatus;
    }

    public Object getTime() {
        return mTime;
    }

    public void setTime(Object time) {
        mTime = time;
    }

    public Object getTreatement() {
        return mTreatement;
    }

    public void setTreatement(Object treatement) {
        mTreatement = treatement;
    }

}
