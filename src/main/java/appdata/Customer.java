package appdata;

import java.util.Objects;
import java.util.Random;

public class Customer {
        private long customerID;
        private String customerName;
        private String customerStreetAddress;
        private String customerCity;
        private String customerState;
        private String customerZip;


        public Customer() {
                Random randomNumberGenerator = new Random();

                this.customerID = Math.abs(randomNumberGenerator.nextLong());
                this.customerName = null;
                this.customerStreetAddress = null;
                this.customerCity = null;
                this.customerState = null;
                this.customerZip = null;
        }

        public Customer(long customerID, String customerName, String customerStreetAddress, String customerCity, String customerState, String customerZip) {
                this.customerID = customerID;
                this.customerName = customerName;
                this.customerStreetAddress = customerStreetAddress;
                this.customerCity = customerCity;
                this.customerState = customerState;
                this.customerZip = customerZip;
        }

        public long getCustomerID() {
                return customerID;
        }

        public String getCustomerName() {
                return customerName;
        }

        public void setCustomerName(String customerName) {
                this.customerName = customerName;
        }

        public String getCustomerStreetAddress() {
                return customerStreetAddress;
        }

        public void setCustomerStreetAddress(String customerStreetAddress) {
                this.customerStreetAddress = customerStreetAddress;
        }

        public String getCustomerCity() {
                return customerCity;
        }

        public void setCustomerCity(String customerCity) {
                this.customerCity = customerCity;
        }

        public String getCustomerState() {
                return customerState;
        }

        public void setCustomerState(String customerState) {
                this.customerState = customerState;
        }

        public String getCustomerZip() {
                return customerZip;
        }

        public void setCustomerZip(String customerZip) {
                this.customerZip = customerZip;
        }

        @Override
        public String toString() {
                return "Customer{" +
                        "customerID=" + customerID +
                        ", customerName='" + customerName + '\'' +
                        ", customerStreetAddress='" + customerStreetAddress + '\'' +
                        ", customerCity='" + customerCity + '\'' +
                        ", customerState='" + customerState + '\'' +
                        ", customerZip='" + customerZip + '\'' +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Customer customer = (Customer) o;
                return getCustomerID() == customer.getCustomerID() && getCustomerName().equals(customer.getCustomerName()) && getCustomerStreetAddress().equals(customer.getCustomerStreetAddress()) && getCustomerCity().equals(customer.getCustomerCity()) && getCustomerState().equals(customer.getCustomerState()) && getCustomerZip().equals(customer.getCustomerZip());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getCustomerID(), getCustomerName(), getCustomerStreetAddress(), getCustomerCity(), getCustomerState(), getCustomerZip());
        }
}
