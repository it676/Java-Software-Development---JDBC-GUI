package online.visionacademy.validators;

import online.visionacademy.exceptions.ValidationException;

public class LengthValidator implements Validator {

    private String field;
    private String value;
    private int min;
    private int max;
    private String errorMessage;

    public LengthValidator(String field, String value, int min, int max) {
        setValues(field, value, min, max);
    }

    public final void setValues(String field, String value, int min, int max) {
        setField(field);
        setValue(value);
        setMin(min);
        setMax(max);
        setErrorMessage(field + " length must be between " + min + " and " + max);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void validate() {
        int length = value.length();
        if (length < min || length > max) {
            throw new ValidationException(errorMessage);
        }
    }

}
