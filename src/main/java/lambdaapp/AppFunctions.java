package lambdaapp;

import appdata.Customer;
import appdata.RequestData;
import appdata.ResponseData;
import appdata.DataStore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppFunctions {

    private static final Logger log = LogManager.getLogger(AppFunctions.class);

    public ResponseData appMain(RequestData theRequest) {

        log.info("***** Info  Message Sent to the logger *****");
        log.warn("***** Warn  Message Sent to the logger *****");
        log.error("***** Error Message Sent to the logger *****");
        log.debug("***** Debug Message Sent to the logger *****");
        log.trace("***** Trace Message Sent to the logger *****");

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

                // If customer is not found, write a message to the log, set the request message, and issue a return code 404

                if(returnedCustomer == null) {
                    message = "Customer Id: " + theRequest.getCustomerId() + " not found!";
                    log.error(message);
                    returnCode = 404;
                    break;                }
                returnCode = 0;
                message = "Customer Id: " + theRequest.getCustomerId() + " found!";
                break;
            }
            case "chgname": {
                theData.getACustomer(theRequest.getCustomerId()).setCustomerName(theRequest.getPayload());
                returnedCustomer = theData.getACustomer(theRequest.getCustomerId());

                // If customer is not found, write a message to the log, set the request message, and issue a return code 404
                if (returnedCustomer == null) {
                    message = "Customer Id: " + theRequest.getCustomerId() + " not found, unable to make change!";
                    log.error(message);
                    returnCode = 404;
                    break;
                }
                returnCode = 0;
                message = "Customer Id: " + returnedCustomer.getCustomerID() + "'s name has been changed to:  " + returnedCustomer.getCustomerName() + "!";
                break;
            }
            case "add": {
                String[] newData = theRequest.getPayload().split(",");
                if (newData.length < 1 || newData[0].equals("")) {
                    // if at least one value is not provided, we cannot add a new customer
                    //   so write a message to the log, set the request message, and issue a return code 422
                    message = "Cannot add a new Customer without at least a name";
                    log.error(message);
                    returnCode = 400;
                    break;
                }
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
                // if action provided is not one we expect...
                //   write a message to the log, set the request message, and issue a return code 400
                log.error(message);
                message = "Invalid action: " + theRequest.getActionRequested();
                returnCode = 400;

            }
        }

        ResponseData theResponse = new ResponseData(returnCode, returnedCustomer, message);

        // Return the ResponseData object
        return theResponse;
    }
}
