package lambdaapp;

import appdata.Customer;
import appdata.RequestData;
import appdata.ResponseData;
import appdata.DataStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class AppFunctions {
    private final Logger log = LogManager.getLogger(AppFunctions.class);

    public ResponseData appMain(RequestData theRequest) {

        // Define a DataStore object with a pre-defined set of Customers
        DataStore theData = new DataStore();

        // Define and initialize values and object to be returned in teh response object
        int returnCode;
        String message;
        Customer returnedCustomer;


        // Determine processing bsed on action requested
        switch (theRequest.getActionRequested().toLowerCase()) {
            case "find" -> {
                returnedCustomer = theData.getACustomer(theRequest.getCustomerId());

                if (returnedCustomer == null) {
                    log.error("Customer not found.");
                    message = "Customer not found.";
                    returnCode = 404;
                    break;
                }

                returnCode = 0;
                message = "Customer Id: " + theRequest.getCustomerId() + " found!";

            }
            case "chgname" -> {
                if (theData.getACustomer(theRequest.getCustomerId()) == null) {
                    log.error("Customer not found, Can't change the name.");
                    message = "Customer not found, Can't change the name.";
                    returnCode = 404;
                    returnedCustomer = null;
                    break;
                }
                System.out.println("HERE: " + theData.getACustomer(theRequest.getCustomerId()));
                theData.getACustomer(theRequest.getCustomerId()).setCustomerName(theRequest.getPayload());
                returnedCustomer = theData.getACustomer(theRequest.getCustomerId());

                returnCode = 0;
                message = "Customer Id: " + returnedCustomer.getCustomerID() + "'s name has been changed to:  " + returnedCustomer.getCustomerName() + "!";
            }
            case "add" -> {
                String payload = theRequest.getPayload();

                if (payload == null || payload.equals("")) {
                    log.error("Cannot add new Customer: missing values.");
                    message = "Cannot add new Customer: missing values.";
                    returnCode = 422;
                    returnedCustomer = null;
                    break;
                }

                String[] newData = payload.split(",");

                returnedCustomer = new Customer();

                returnedCustomer.setCustomerName(newData[0].trim());

                if (newData.length > 1) {
                    returnedCustomer.setCustomerStreetAddress(newData[1].trim());
                }
                if (newData.length > 2) {
                    returnedCustomer.setCustomerCity(newData[2].trim());
                }
                if (newData.length > 3) {
                    returnedCustomer.setCustomerState(newData[3].trim());
                }
                if (newData.length > 4) {
                    returnedCustomer.setCustomerZip(newData[4].trim());
                }

                returnCode = 0;
                message = "Customer Id: " + returnedCustomer.getCustomerID() + ", Name:  " + returnedCustomer.getCustomerName() + " has been added!";
            }
            default -> {
                log.error("Invalid action: " + theRequest.getActionRequested());
                returnedCustomer = null;
                message = "Invalid action: " + theRequest.getActionRequested();
                returnCode = 400;
            }
        }

        // Return the ResponseData object
        return new ResponseData(returnCode, returnedCustomer, message);
    }
}
