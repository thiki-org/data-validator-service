package org.thiki.datavalidator.ex;


import org.thiki.datavalidator.foundation.util.StringUtil;

/**
 * Created by joeaniu on 2016/5/31.
 */
public class AssertionException extends RuntimeException{

    private static final long serialVersionUID = 6721796200728513458L;

    private final String message;
    private final Object[] params;

    public AssertionException(String message, Object... params) {
        this.message = message;
        this.params = params;
    }

    @Override
    public String getMessage() {
        return StringUtil.format(message, params) + "\n" + getThrowsLine();
    }

    private String getThrowsLine() {
        StackTraceElement element = this.getStackTrace()[0];
        return "\tat " + element.toString();
    }

}
