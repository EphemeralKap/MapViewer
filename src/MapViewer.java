
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kap
 */
public class MapViewer {

    public static void main(String[] args) {
        String inputObj = "C:\\OBJTesting\\BarProcessed.obj";

        List<Point3D> points = ObjUtilities.PointsInOBJ(inputObj);
        List<Point3D> trimmedPoints = new ArrayList<>();
        
        float min = ObjUtilities.minZValue(inputObj);
        float max = ObjUtilities.maxZValue(inputObj);
        float diff = max - min;
        float minRange = max - (diff*0.50f + diff*0.06f);
        float maxRange = max - (diff*0.50f - diff*0.06f);

        
        for (Point3D point : points) {
            if(point.z > minRange && point.z < maxRange) {
                trimmedPoints.add(point);
            }
        }
        
        MapFrame mapViewer = new MapFrame(trimmedPoints);
    }

}
