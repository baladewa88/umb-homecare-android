
package umbandung.com.digitalhomecare.Model.nurse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class IdTransaction {

    @SerializedName("addressToVisit")
    private String mAddressToVisit;
    @SerializedName("date")
    private String mDate;
    @SerializedName("dateOrderIn")
    private String mDateOrderIn;
    @SerializedName("dateTreatementEnd")
    private Object mDateTreatementEnd;
    @SerializedName("dateTreatementStart")
    private String mDateTreatementStart;
    @SerializedName("diagnosis")
    private String mDiagnosis;
    @SerializedName("expiredTransactionTimeFixedPrice")
    private Object mExpiredTransactionTimeFixedPrice;
    @SerializedName("expiredTransactionTimePrepaidPrice")
    private Object mExpiredTransactionTimePrepaidPrice;
    @SerializedName("fixedPrice")
    private Double mFixedPrice;
    @SerializedName("id")
    private Long mId;
    @SerializedName("idClinic")
    private IdClinic mIdClinic;
    @SerializedName("locationLatitude")
    private Object mLocationLatitude;
    @SerializedName("locationLongitude")
    private Object mLocationLongitude;
    @SerializedName("medicine")
    private String mMedicine;
    @SerializedName("nurseAction")
    private String mNurseAction;
    @SerializedName("orderNumber")
    private String mOrderNumber;
    @SerializedName("paymentFixedPriceStatusId")
    private PaymentFixedPriceStatusId mPaymentFixedPriceStatusId;
    @SerializedName("paymentMethodId")
    private Object mPaymentMethodId;
    @SerializedName("paymentPrepaidPriceStatusId")
    private Object mPaymentPrepaidPriceStatusId;
    @SerializedName("predictionPrice")
    private String mPredictionPrice;
    @SerializedName("prepaidPrice")
    private Double mPrepaidPrice;
    @SerializedName("receiptPath")
    private Object mReceiptPath;
    @SerializedName("refundPrice")
    private Double mRefundPrice;
    @SerializedName("serviceList")
    private List<ServiceList> mServiceList;
    @SerializedName("sumOfDays")
    private Object mSumOfDays;
    @SerializedName("transactionDescription")
    private Object mTransactionDescription;
    @SerializedName("transactionStatusId")
    private TransactionStatusId mTransactionStatusId;
    @SerializedName("transactionTypeId")
    private TransactionTypeId mTransactionTypeId;
    @SerializedName("userPatient")
    private UserPatient mUserPatient;

    public String getAddressToVisit() {
        return mAddressToVisit;
    }

    public void setAddressToVisit(String addressToVisit) {
        mAddressToVisit = addressToVisit;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDateOrderIn() {
        return mDateOrderIn;
    }

    public void setDateOrderIn(String dateOrderIn) {
        mDateOrderIn = dateOrderIn;
    }

    public Object getDateTreatementEnd() {
        return mDateTreatementEnd;
    }

    public void setDateTreatementEnd(Object dateTreatementEnd) {
        mDateTreatementEnd = dateTreatementEnd;
    }

    public String getDateTreatementStart() {
        return mDateTreatementStart;
    }

    public void setDateTreatementStart(String dateTreatementStart) {
        mDateTreatementStart = dateTreatementStart;
    }

    public String getDiagnosis() {
        return mDiagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        mDiagnosis = diagnosis;
    }

    public Object getExpiredTransactionTimeFixedPrice() {
        return mExpiredTransactionTimeFixedPrice;
    }

    public void setExpiredTransactionTimeFixedPrice(Object expiredTransactionTimeFixedPrice) {
        mExpiredTransactionTimeFixedPrice = expiredTransactionTimeFixedPrice;
    }

    public Object getExpiredTransactionTimePrepaidPrice() {
        return mExpiredTransactionTimePrepaidPrice;
    }

    public void setExpiredTransactionTimePrepaidPrice(Object expiredTransactionTimePrepaidPrice) {
        mExpiredTransactionTimePrepaidPrice = expiredTransactionTimePrepaidPrice;
    }

    public Double getFixedPrice() {
        return mFixedPrice;
    }

    public void setFixedPrice(Double fixedPrice) {
        mFixedPrice = fixedPrice;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public IdClinic getIdClinic() {
        return mIdClinic;
    }

    public void setIdClinic(IdClinic idClinic) {
        mIdClinic = idClinic;
    }

    public Object getLocationLatitude() {
        return mLocationLatitude;
    }

    public void setLocationLatitude(Object locationLatitude) {
        mLocationLatitude = locationLatitude;
    }

    public Object getLocationLongitude() {
        return mLocationLongitude;
    }

    public void setLocationLongitude(Object locationLongitude) {
        mLocationLongitude = locationLongitude;
    }

    public String getMedicine() {
        return mMedicine;
    }

    public void setMedicine(String medicine) {
        mMedicine = medicine;
    }

    public String getNurseAction() {
        return mNurseAction;
    }

    public void setNurseAction(String nurseAction) {
        mNurseAction = nurseAction;
    }

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        mOrderNumber = orderNumber;
    }

    public PaymentFixedPriceStatusId getPaymentFixedPriceStatusId() {
        return mPaymentFixedPriceStatusId;
    }

    public void setPaymentFixedPriceStatusId(PaymentFixedPriceStatusId paymentFixedPriceStatusId) {
        mPaymentFixedPriceStatusId = paymentFixedPriceStatusId;
    }

    public Object getPaymentMethodId() {
        return mPaymentMethodId;
    }

    public void setPaymentMethodId(Object paymentMethodId) {
        mPaymentMethodId = paymentMethodId;
    }

    public Object getPaymentPrepaidPriceStatusId() {
        return mPaymentPrepaidPriceStatusId;
    }

    public void setPaymentPrepaidPriceStatusId(Object paymentPrepaidPriceStatusId) {
        mPaymentPrepaidPriceStatusId = paymentPrepaidPriceStatusId;
    }

    public String getPredictionPrice() {
        return mPredictionPrice;
    }

    public void setPredictionPrice(String predictionPrice) {
        mPredictionPrice = predictionPrice;
    }

    public Double getPrepaidPrice() {
        return mPrepaidPrice;
    }

    public void setPrepaidPrice(Double prepaidPrice) {
        mPrepaidPrice = prepaidPrice;
    }

    public Object getReceiptPath() {
        return mReceiptPath;
    }

    public void setReceiptPath(Object receiptPath) {
        mReceiptPath = receiptPath;
    }

    public Double getRefundPrice() {
        return mRefundPrice;
    }

    public void setRefundPrice(Double refundPrice) {
        mRefundPrice = refundPrice;
    }

    public List<ServiceList> getServiceList() {
        return mServiceList;
    }

    public void setServiceList(List<ServiceList> serviceList) {
        mServiceList = serviceList;
    }

    public Object getSumOfDays() {
        return mSumOfDays;
    }

    public void setSumOfDays(Object sumOfDays) {
        mSumOfDays = sumOfDays;
    }

    public Object getTransactionDescription() {
        return mTransactionDescription;
    }

    public void setTransactionDescription(Object transactionDescription) {
        mTransactionDescription = transactionDescription;
    }

    public TransactionStatusId getTransactionStatusId() {
        return mTransactionStatusId;
    }

    public void setTransactionStatusId(TransactionStatusId transactionStatusId) {
        mTransactionStatusId = transactionStatusId;
    }

    public TransactionTypeId getTransactionTypeId() {
        return mTransactionTypeId;
    }

    public void setTransactionTypeId(TransactionTypeId transactionTypeId) {
        mTransactionTypeId = transactionTypeId;
    }

    public UserPatient getUserPatient() {
        return mUserPatient;
    }

    public void setUserPatient(UserPatient userPatient) {
        mUserPatient = userPatient;
    }

}
