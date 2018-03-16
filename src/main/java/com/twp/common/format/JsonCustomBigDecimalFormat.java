package com.twp.common.format;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class JsonCustomBigDecimalFormat extends JsonSerializer<BigDecimal> {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private static final DecimalFormat decimalFormatInteger = new DecimalFormat("#.##");
    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeObject(new BigDecimal(format(bigDecimal)));
    }

    public static String format(BigDecimal bigDecimal){
        if (bigDecimal.equals("0")) return "0";
        if (isIntegerValue(bigDecimal)){
            return decimalFormatInteger.format(bigDecimal);
        }
        return decimalFormat.format(bigDecimal==null?"0":bigDecimal);
    }

    private static boolean isIntegerValue(BigDecimal bd) {
        boolean ret;
        if (bd==null){
            return false;
        }

        try {
            bd.toBigIntegerExact();
            ret = true;
        } catch (ArithmeticException ex) {
            ret = false;
        }

        return ret;
    }
}
