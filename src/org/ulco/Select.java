package org.ulco;

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


}
