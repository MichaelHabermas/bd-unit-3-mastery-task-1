package appdata;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class ResponseData {

        private int returnCode;
        private Customer aCustomer;
        private String message;
        private String creationTimeStamp;

        public ResponseData(int returnCode, Customer aCustomer, String message) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                Timestamp timestampNow = new Timestamp(System.currentTimeMillis());

                this.returnCode = returnCode;
                this.aCustomer  = aCustomer;
                this.message    = message;
                this.creationTimeStamp = timestampNow.toString();
        }

        public int getReturnCode() {
                return returnCode;
        }

        public void setReturnCode(int returnCode) {
                this.returnCode = returnCode;
        }

        public Customer getaCustomer() {
                return aCustomer;
        }

        public void setaCustomer(Customer aCustomer) {
                this.aCustomer = aCustomer;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public String getCreationTimeStamp() {
                return creationTimeStamp;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ResponseData that = (ResponseData) o;
                return getReturnCode() == that.getReturnCode() && getaCustomer().equals(that.getaCustomer()) && getMessage().equals(that.getMessage()) && getCreationTimeStamp().equals(that.getCreationTimeStamp());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getReturnCode(), getaCustomer(), getMessage(), getCreationTimeStamp());
        }

        @Override
        public String toString() {
                return "ResponseData{" +
                        "returnCode=" + returnCode +
                        ", aCustomer=" + aCustomer +
                        ", message='" + message + '\'' +
                        ", creationTimeStamp='" + creationTimeStamp + '\'' +
                        '}';
        }
}
