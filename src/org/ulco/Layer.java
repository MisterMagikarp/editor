package org.ulco;

import java.util.Vector;

public class Layer {


    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID = ID.getINSTANCE().getID();
    }

    public Layer(String json) {
        m_list= new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

       //parseObjects(str.substring(objectsIndex + 9, endIndex - 1));
        Select.parseObjects(str.substring(objectsIndex + 9, groupIndex -2), m_list);
        Select.parseObjects(str.substring(groupIndex + 8, endIndex -1), m_list);
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber()
    {
        int size = 0;
        for(Object o : m_list){
            if(o instanceof Group) {
                size += ((Group) o).size();
                    }
                    else size ++;
                }
                return size;
               }

    public int getID() {
        return m_ID;
    }



    public GraphicsObjects select(Point pt, double distance) {
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : m_list) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }

    public String toJson() {
        String str = "{ type: layer, objects : { ";

        String groupstr = "";

        for (int i = 0; i < m_list.size(); ++i) {
            //GraphicsObject element = m_list.elementAt(i);

            if(m_list.elementAt(i) instanceof Group){
                Group element =(Group) m_list.elementAt(i);
                groupstr += element.toJson();

            } else {
                GraphicsObject element = m_list.elementAt(i);
                str += element.toJson();
                if (i < m_list.size() - 1) {
                    str += ", ";

                }
            }
        }

        str += groupstr;
        return str + " } }";
    }

    public Vector<GraphicsObject> getM_list(){
        return m_list;
    }

    private Vector<GraphicsObject> m_list;
    private int m_ID;
}
