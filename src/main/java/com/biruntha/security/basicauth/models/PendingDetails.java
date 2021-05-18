package com.biruntha.security.basicauth.models;
import org.springframework.data.annotation.Id; 
import org.springframework.data.mongodb.core.mapping.Document; 

@Document 
public class PendingDetails {
@Id
        private int id;
        private Upload upload;
        private String buyerName;
        private String buyerAddress;
        private String buyerPhoneNum;

        public PendingDetails(int id,Upload upload, String buyerName, String buyerAddress, String buyerPhoneNum ) {
            this.id = id;
            this.upload = upload; 
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

       public Upload getUpload() {
	return upload;
					}
	public void setUpload(Upload upload) {
	this.upload = upload;
	} 

        @Override
        public String toString() {
            return "PendingDetails [id=" + id + ", upload=" + upload + ", buyerName=" + buyerName + ", buyerAddress="
                    + buyerAddress + ", buyerPhoneNum=" + buyerPhoneNum + "]";
        }
    }
