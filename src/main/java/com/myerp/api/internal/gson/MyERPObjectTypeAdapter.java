package com.myerp.api.internal.gson;

import com.myerp.api.MyERPObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Shamelessly ripped from
 * https://github.com/Ecwid/ecwid-mailchimp/blob/master/src/main/java/com/ecwid/mailchimp/internal/gson/
 * MailChimpObjectTypeAdapter.java
 * -
 *
 * @author Vasily Karyaev <v.karyaev@gmail.com>
 */
class MyERPObjectTypeAdapter extends TypeAdapter<MyERPObject> {

  static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      if (MyERPObject.class.isAssignableFrom(type.getRawType())) {
	return (TypeAdapter<T>) new MyERPObjectTypeAdapter(gson, (TypeToken<MyERPObject>) type);
      } else {
	return null;
      }
    }
  };
  private final Gson gson;
  private final Constructor<? extends MyERPObject> constructor;

  @SuppressWarnings("unchecked")
  private MyERPObjectTypeAdapter(Gson gson, TypeToken<MyERPObject> type) {
    this.gson = gson;
    try {
      this.constructor = ((Class<? extends MyERPObject>) type.getRawType()).getDeclaredConstructor();
      this.constructor.setAccessible(true);
    } catch (NoSuchMethodException e) {
      throw new IllegalArgumentException("No no-arg counstructor found in " + type.getRawType());
    }
  }

  @Override
  public void write(JsonWriter out, MyERPObject value) throws IOException {
    gson.getAdapter(Map.class).write(out, value);
  }

  @Override
  public MyERPObject read(JsonReader in) throws IOException {
    if (in.peek() == JsonToken.NULL) {
      in.nextNull();
      return null;
    }
    MyERPObject result;
    try {
      result = constructor.newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Failed to invoke " + constructor + " with no args", e);
    }
    in.beginObject();
    while (in.hasNext()) {
      final String key;
      if (in.peek() == JsonToken.NAME) {
	key = in.nextName();
      } else {
	key = in.nextString();
      }
      final Object value;
      Type valueType = result.getReflectiveMappingTypes().get(key);
      if (valueType != null) {
	value = gson.getAdapter(TypeToken.get(valueType)).read(in);
      } else {
	if (in.peek() == JsonToken.BEGIN_OBJECT) {
	  value = gson.getAdapter(MyERPObject.class).read(in);
	} else if (in.peek() == JsonToken.BEGIN_ARRAY) {
	  value = readList(in);
	} else {
	  value = gson.getAdapter(Object.class).read(in);
	}
      }
      if (result.put(key, value) != null) {
	throw new JsonSyntaxException("duplicate key: " + key);
      }
    }
    in.endObject();
    return result;
  }

  private List<?> readList(JsonReader in) throws IOException {
    List<Object> result = new ArrayList<Object>();
    in.beginArray();
    while (in.peek() != JsonToken.END_ARRAY) {
      final Object element;
      if (in.peek() == JsonToken.BEGIN_OBJECT) {
	element = gson.getAdapter(MyERPObject.class).read(in);
      } else if (in.peek() == JsonToken.BEGIN_ARRAY) {
	element = readList(in);
      } else {
	element = gson.getAdapter(Object.class).read(in);
      }
      result.add(element);
    }
    in.endArray();
    return result;
  }

}
