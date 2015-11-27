package org.ulco;

import java.util.Vector;

/**
 * Created by mistermagikarp on 24/11/15.
 */
public class Select {

    public static GraphicsObjects select(Point point, double distance, Document doc){
        GraphicsObjects liste = new GraphicsObjects();

        for(Layer layer: doc.getLayer()){
            liste.addAll(select(point, distance, layer));
        }
        return liste;
    }

    public static GraphicsObjects select(Point point, double distance, Layer layer){
        GraphicsObjects liste = new GraphicsObjects();

        for(GraphicsObject object : layer.getM_list()){
            if(object.isClosed(point, distance)){
                liste.add(object);
            }
        }
        return liste;
    }


    public static void parseObjects(String objectsStr, Vector<GraphicsObject> m_objectList) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = SearchSeparator.searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }
}
