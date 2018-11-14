package space.harbour.java.hw4;

import javax.json.*;
import java.io.StringReader;

public class ExampleValue implements Jsonable {
    public Integer i = 10;
    private String s = "ABC";
    protected float f = .9f;
    private InsideClass hiddenClass = new InsideClass();

    class InsideClass implements Jsonable {
        String s = "XYZ";
        Integer i = 1050;

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("s", s)
                    .add("i", i)
                    .build();
        }

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("i", i)
                .add("s", s)
                .add("f", f)
                .add("hiddenClass", hiddenClass.toJsonObject())
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public void fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jObject = reader.readObject();
        this.i = jObject.getInt("i");
        this.s = jObject.getString("s");
        this.f = (float) jObject.getJsonNumber("f").doubleValue();

        this.hiddenClass = new InsideClass();
    }

    public static void main(String... args) {
        ExampleValue value = new ExampleValue();
        System.out.println(value.toJsonString());
    }
}