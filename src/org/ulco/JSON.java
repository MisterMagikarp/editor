package org.ulco;

import java.lang.reflect.Constructor;

public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));

        /*if (type.compareTo("square") == 0) {
            o = new Square(str);
        } else if (type.compareTo("rectangle") == 0) {
            o = new Rectangle(str);
        } else if (type.compareTo("circle") == 0) {
            o = new Circle(str);
        }*/


        String type2 = type.substring(0, 1).toUpperCase() + type.substring(1);

        try{
            Class c = Class.forName("org.ulco."+type2);
            Constructor ct = c.getConstructor(String.class);
            Object ob = ct.newInstance(str);
            o = (GraphicsObject) ob;
            //ob.build(str);
        } catch(Exception e){
            e.printStackTrace();
        }
        /*o =
        Circle tmp = (Cirle) o;*/
        return o;
    }


    static public Group parseGroup(String json) {
        return new Group(json);
    }

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
