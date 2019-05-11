package com.jumia.pay.assessment.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE MMM dd HH:mm:ss z yyyy");

    private Logger logger = LoggerFactory.getLogger(CustomDateDeserializer.class.getName());

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateString = jsonParser.getText().trim();
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error("Unable to convert string to date. Resolve to deserialization context for conversion. Error due to ", e);
        }
        return deserializationContext.parseDate(dateString);
    }
}
