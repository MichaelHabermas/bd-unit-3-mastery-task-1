package appdata;

import java.util.Objects;

public class RequestData {
       private String actionRequested;
       private long   customerId;
       private String payload;

        public String getActionRequested() {
                return actionRequested;
        }

        public void setActionRequested(String actionRequested) {
                this.actionRequested = actionRequested;
        }

        public long getCustomerId() {
                return customerId;
        }

        public void setCustomerId(long customerId) {
                this.customerId = customerId;
        }

        public String getPayload() {
                return payload;
        }

        public void setPayload(String payload) {
                this.payload = payload;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                RequestData that = (RequestData) o;
                return getCustomerId() == that.getCustomerId() && getActionRequested().equals(that.getActionRequested()) && getPayload().equals(that.getPayload());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getActionRequested(), getCustomerId(), getPayload());
        }

        @Override
        public String toString() {
                return "RequestData{" +
                        "actionRequested='" + actionRequested + '\'' +
                        ", customerId=" + customerId +
                        ", payload='" + payload + '\'' +
                        '}';
        }
}
