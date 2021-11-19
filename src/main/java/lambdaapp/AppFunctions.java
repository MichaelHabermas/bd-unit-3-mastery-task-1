package lambdaapp;

import appdata.Customer;
import appdata.RequestData;
import appdata.ResponseData;
import appdata.DataStore;

public class AppFunctions {

    // TODO: Instantiate a log4j logger instance

    public ResponseData appMain(RequestData theRequest) {

        // Define a DataStore object with a pre-defined set of Customers
        DataStore theData = new DataStore();

        // Define and initialize values and object to be returned in teh response object
        int returnCode = 0;
        String message = null;
        Customer returnedCustomer = null;


        // Determine processing bsed on action requested
        switch (theRequest.getActionRequested().toLowerCase()) {
            case "find": {
                returnedCustomer = theData.getACustomer(theRequest.getCustomerId());

                // TODO - If customer is not found, write a message to the log, set the request message, and issue a return code 404

                returnCode = 0;
                message = "Customer Id: " + theRequest.getCustomerId() + " found!";
                break;
            }
            case "chgname": {
                theData.getACustomer(theRequest.getCustomerId()).setCustomerName(theRequest.getPayload());
                returnedCustomer = theData.getACustomer(theRequest.getCustomerId());

                // TODO - If customer is not found, write a message to the log, set the request message, and issue a return code 404

                returnCode = 0;
                message = "Customer Id: " + returnedCustomer.getCustomerID() + "'s name has been changed to:  " + returnedCustomer.getCustomerName() + "!";
                break;
            }
            case "add": {
                String[] newData = theRequest.getPayload().split(",");

                // TODO - if at least one value is not provided, we cannot add a new customer
                //   so write a message to the log, set the request message, and issue a return code 422

                returnedCustomer = new Customer();

                if (newData.length > 0)  { returnedCustomer.setCustomerName(newData[0].trim());         }
                if (newData.length > 1)  { returnedCustomer.setCustomerStreetAddress(newData[1].trim());}
                if (newData.length > 2)  { returnedCustomer.setCustomerCity(newData[2].trim());         }
                if (newData.length > 3)  { returnedCustomer.setCustomerState(newData[3].trim());        }
                if (newData.length > 4)  { returnedCustomer.setCustomerZip(newData[4].trim());          }

                returnCode = 0;
                message = "Customer Id: " + returnedCustomer.getCustomerID() + ", Name:  " + returnedCustomer.getCustomerName() + " has been added!";
                break;
            }
            default: {
                // TODO - if action provided is not one we expect...
                //   write a message to the log, set the request message, and issue a return code 400

                message = "Invalid action: " + theRequest.getActionRequested();
                returnCode = 400;

            }
        }

        ResponseData theResponse = new ResponseData(returnCode, returnedCustomer, message);

        // Return the ResponseData object
        return theResponse;
    }
}
