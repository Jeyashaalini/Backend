package com.biruntha.security.basicauth.models;
import org.springframework.data.annotation.Id; 
import org.springframework.data.mongodb.core.mapping.Document; 

@Document 
public class PendingDetails {
@Id
        private int id;
        private String uploadId;
        private String buyerName;
        private String buyerAddress;
        private String buyerPhoneNum;

        public PendingDetails(int id,String uploadId, String buyerName, String buyerAddress, String buyerPhoneNum ) {
            this.id = id;
            this.uploadId = uploadId; 
            this.buyerName = buyerName;
            this.buyerAddress = buyerAddress;
            this.buyerPhoneNum = buyerPhoneNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getBuyerAddress() {
            return buyerAddress;
        }

        public void setBuyerAddress(String buyerAddress) {
            this.buyerAddress = buyerAddress;
        }

        public String getBuyerPhoneNum() {
            return buyerPhoneNum;
        }

        public void setBuyerPhoneNum(String buyerPhoneNum) {
            this.buyerPhoneNum = buyerPhoneNum;
        }

       public String getUploadId() {
	return uploadId;
					}
	public void setUploadId(String uploadId) {
	this.uploadId = uploadId;
	} 

        @Override
        public String toString() {
            return "PendingDetails [id=" + id + ", uploadId=" + uploadId + ", buyerName=" + buyerName + ", buyerAddress="
                    + buyerAddress + ", buyerPhoneNum=" + buyerPhoneNum + "]";
        }
    }
