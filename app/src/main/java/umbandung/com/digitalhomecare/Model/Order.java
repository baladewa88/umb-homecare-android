package umbandung.com.digitalhomecare.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arkhan on 9/7/2018.
 */

public class Order {

    @SerializedName("id")
    private String id;
    @SerializedName("transactionTypeId")
    private String transactionTypeId;
    @SerializedName("transactionStatusId")
    private String transactionStatusId;
    @SerializedName("paymentFixedPriceStatusId")
    private String paymentFixedPriceStatusId;
    @SerializedName("idClinic")
    private String idClinic;
    @SerializedName("serviceList")
    private String[] serviceList;
    @SerializedName("addressToVisit")
    private String addressToVisit;
    @SerializedName("date")
    private String date;

    public Order(){

    }

    public Order(String id, String transactionTypeId,
             String transactionStatusId
            , String paymentFixedPriceStatusId
         , String[] serviceList
            , String addressToVisit
            , String date){
        this.id = id;
        this.transactionStatusId = transactionStatusId;
        this.transactionTypeId = transactionTypeId;
        this.paymentFixedPriceStatusId = paymentFixedPriceStatusId;
        this.serviceList = serviceList;
        this.addressToVisit = addressToVisit;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(String transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionStatusId() {
        return transactionStatusId;
    }

    public void setTransactionStatusId(String transactionStatusId) {
        this.transactionStatusId = transactionStatusId;
    }

    public String getPaymentFixedPriceStatusId() {
        return paymentFixedPriceStatusId;
    }

    public void setPaymentFixedPriceStatusId(String paymentFixedPriceStatusId) {
        this.paymentFixedPriceStatusId = paymentFixedPriceStatusId;
    }

    public String getIdClinic() {
        return idClinic;
    }

    public void setIdClinic(String idClinic) {
        this.idClinic = idClinic;
    }

    public String[] getServiceList() {
        return serviceList;
    }

    public void setServiceList(String[] serviceList) {
        this.serviceList = serviceList;
    }

    public String getAddressToVisit() {
        return addressToVisit;
    }

    public void setAddressToVisit(String addressToVisit) {
        this.addressToVisit = addressToVisit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
