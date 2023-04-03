package com.hilltop.enums;

import lombok.Getter;

/**
 * SuccessResponseStatusType
 */
@Getter
public enum SuccessResponseStatusType {
    CREATE_BOOKING(2000, "Successfully created the booking."),
    SEARCH_ROOM(2002, "Successfully returned the search room list.");

    private final int code;
    private final String message;

    SuccessResponseStatusType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Success code covert into string to read display message from success property file
     *
     * @param successCode successCode
     * @return string code
     */
    public String getCodeString(int successCode) {
        return Integer.toString(successCode);
    }
}
