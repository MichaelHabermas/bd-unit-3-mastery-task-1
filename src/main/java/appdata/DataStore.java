package appdata;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
        private List<Customer> currentCustomers;

        public DataStore() {
                this.currentCustomers = new ArrayList<Customer>();

                this.currentCustomers.add(new Customer(00001,"James T. Kirk", "1701 Bridge Seat", "Enterprise", "MW", null));
                this.currentCustomers.add(new Customer(10101,"Jean-Luc Picard", "1701-D Bridge Seat", " Enterprise", "MW", null));
                this.currentCustomers.add(new Customer(28373,"Luke Skywalker", "R2D2 Sand Way", "Tatooine", "SW", "82736-2934"));
                this.currentCustomers.add(new Customer(25374283,"Yoda", "One Big Swamp", "Dagobah", "SW", "192837"));
        }

        public List<Customer> getCurrentCustomers() {
                return currentCustomers;
        }

        public Customer getACustomer(long customerId) {
                for (int i=0; i < currentCustomers.size(); i++) {
                        if (currentCustomers.get(i).getCustomerID() == customerId) {
                                return currentCustomers.get(i);
                        }
                }
                return null;
        }
}
