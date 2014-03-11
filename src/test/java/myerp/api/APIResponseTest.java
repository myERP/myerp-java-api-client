package myerp.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.myerp.api.APIResponse;
import com.myerp.api.MyERPObject;
import com.myerp.api.exceptions.AuthenticationFailedException;
import com.myerp.api.exceptions.MyERPException;
import com.myerp.api.exceptions.NotFoundException;
import com.myerp.api.internal.gson.MyERPGsonFactory;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

import com.google.gson.Gson;

import java.util.List;

public class APIResponseTest {

  private static class TestObject extends MyERPObject {
    @Field
    public Integer id;
    @Field
    public String name;
  }

  private final Class<TestObject> aClass = TestObject.class;
  private final Gson gson = MyERPGsonFactory.createGson();

  @Test
  public void test200singleObject() throws MyERPException {
    APIResponse<TestObject> r = new APIResponse<TestObject>(200, new Header[] {}, "{\"id\":1337,\"name\": \"test\"}");
    TestObject o = r.getData(gson, aClass).get(0);
    assertEquals(o.id, new Integer(1337));
    assertEquals(o.name, "test");
  }

  @Test
  public void test200arrayObjectsWithNextPage() throws MyERPException {
    BasicHeader h = new BasicHeader("X-MyERP-Has-Next-Page", "true");
    APIResponse<TestObject> r =
        new APIResponse<TestObject>(200, new Header[] { h },
            "[{\"id\":1337,\"name\": \"test1\"},{\"id\":1338,\"name\": \"test2\"},{\"id\":1339,\"name\": \"test3\"}]");
    List<TestObject> os = r.getData(gson, aClass);

    assertEquals(os.size(), 3);
    assertTrue(r.hasNextPage());
    assertEquals(os.get(0).id, new Integer(1337));
    assertEquals(os.get(0).name, "test1");
    assertEquals(os.get(1).id, new Integer(1338));
    assertEquals(os.get(1).name, "test2");
    assertEquals(os.get(2).id, new Integer(1339));
    assertEquals(os.get(2).name, "test3");
  }

  @Test
  public void test200arrayObjectsWithNoNextPage() throws MyERPException {
    BasicHeader h = new BasicHeader("X-MyERP-Has-Next-Page", "false");
    APIResponse<TestObject> r =
        new APIResponse<TestObject>(200, new Header[] { h },
            "[{\"id\":1337,\"name\": \"test1\"},{\"id\":1338,\"name\": \"test2\"},{\"id\":1339,\"name\": \"test3\"}]");
    List<TestObject> os = r.getData(gson, aClass);

    assertEquals(os.size(), 3);
    assertFalse(r.hasNextPage());
  }

  @Test
  public void test404() throws MyERPException {
    APIResponse<TestObject> r =
        new APIResponse<TestObject>(404, new Header[] {},
            "{\"error\":{\"code\":\"003\",\"message\":\"The resource was not found.\"}}");
    List<TestObject> os;
    try {
      os = r.getData(gson, aClass);
      fail();
    } catch (NotFoundException e) {
      assertEquals("003", e.code);
      assertEquals("The resource was not found.", e.message);
      assertEquals("", e.reason);
    }
  }

  @Test
  public void test401() throws MyERPException {
    APIResponse<TestObject> r =
        new APIResponse<TestObject>(401, new Header[] {},
            "{\"error\":{\"code\":\"other\",\"message\":\"The request requires user authentication\"}}");
    List<TestObject> os;
    try {
      os = r.getData(gson, aClass);
      fail();
    } catch (AuthenticationFailedException e) {
      assertEquals("other", e.code);
      assertEquals("The request requires user authentication", e.message);
      assertEquals("", e.reason);
    }
  }

}
