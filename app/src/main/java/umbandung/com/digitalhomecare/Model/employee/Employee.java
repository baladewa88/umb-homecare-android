
package umbandung.com.digitalhomecare.Model.employee;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Employee {

    @SerializedName("content")
    private List<Content> mContent;
    @SerializedName("first")
    private Boolean mFirst;
    @SerializedName("last")
    private Boolean mLast;
    @SerializedName("number")
    private Long mNumber;
    @SerializedName("numberOfElements")
    private Long mNumberOfElements;
    @SerializedName("pageable")
    private Pageable mPageable;
    @SerializedName("size")
    private Long mSize;
    @SerializedName("sort")
    private Sort mSort;
    @SerializedName("totalElements")
    private Long mTotalElements;
    @SerializedName("totalPages")
    private Long mTotalPages;

    public List<Content> getContent() {
        return mContent;
    }

    public void setContent(List<Content> content) {
        mContent = content;
    }

    public Boolean getFirst() {
        return mFirst;
    }

    public void setFirst(Boolean first) {
        mFirst = first;
    }

    public Boolean getLast() {
        return mLast;
    }

    public void setLast(Boolean last) {
        mLast = last;
    }

    public Long getNumber() {
        return mNumber;
    }

    public void setNumber(Long number) {
        mNumber = number;
    }

    public Long getNumberOfElements() {
        return mNumberOfElements;
    }

    public void setNumberOfElements(Long numberOfElements) {
        mNumberOfElements = numberOfElements;
    }

    public Pageable getPageable() {
        return mPageable;
    }

    public void setPageable(Pageable pageable) {
        mPageable = pageable;
    }

    public Long getSize() {
        return mSize;
    }

    public void setSize(Long size) {
        mSize = size;
    }

    public Sort getSort() {
        return mSort;
    }

    public void setSort(Sort sort) {
        mSort = sort;
    }

    public Long getTotalElements() {
        return mTotalElements;
    }

    public void setTotalElements(Long totalElements) {
        mTotalElements = totalElements;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

}
