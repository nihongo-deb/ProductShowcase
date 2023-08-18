package org.nihongo_deb.ProductShowcase.Util.ErrorResponces;

/**
 * @author KAWAIISHY
 * @project ProductShowcase
 * @created 18.08.2023
 */
public class MyErrorResponse {
    private String message;
    private long timestamp;

    public MyErrorResponse(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
