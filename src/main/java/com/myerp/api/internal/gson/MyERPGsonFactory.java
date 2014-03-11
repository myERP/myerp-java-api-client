package com.myerp.api.internal.gson;

import com.myerp.api.MyERPObject;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Shamelessly ripped from
 * https://github.com/Ecwid/ecwid-mailchimp/blob/master/src/main/java/com/ecwid/mailchimp/internal/gson/
 * MailChimpGsonFactory.java
 * -
 *
 * @author Vasily Karyaev <v.karyaev@gmail.com>
 */
public class MyERPGsonFactory {

  private MyERPGsonFactory() {}

  /**
   * Excludes every field in any class.
   * Only instances of {@link MyERPObject} can be serialized
   * (see {@link MyERPObjectTypeAdapter}).
   */
  private static final ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
    @Override
    public boolean shouldSkipField(FieldAttributes fa) {
      return true;
    }

    @Override
    public boolean shouldSkipClass(Class<?> type) {
      return false;
    }
  };

  /**
   * Translates dates to json strings and vice versa.
   * <p>
   * This adapter is used instead of {@link GsonBuilder#setDateFormat(java.lang.String)} due to gson's lack of ability
   * to set proper time zone.
   */
  private static class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private final DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    DateTypeAdapter() {
      format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public JsonElement serialize(Date t, Type type, JsonSerializationContext jsc) {
      synchronized (format) {
	return new JsonPrimitive(format.format(t));
      }
    }

    @Override
    public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
      synchronized (format) {
	try {
	  return !je.getAsString().isEmpty() ? format.parse(je.getAsString()) : null;
	} catch (ParseException e) {
	  throw new IllegalArgumentException("Cannot deserialize date: " + je);
	}
      }
    }
  }

  /**
   * Creates a new {@link Gson} object.
   */
  public static Gson createGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.setExclusionStrategies(exclusionStrategy);
    builder.registerTypeAdapter(Date.class, new DateTypeAdapter());
    builder.registerTypeAdapterFactory(MyERPObjectTypeAdapter.FACTORY);
    return builder.create();
  }
}
