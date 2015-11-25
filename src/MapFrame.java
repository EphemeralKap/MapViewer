
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Kap
 */
public class MapFrame extends JFrame {
    
    List<Point3D> points = new ArrayList();
    
    public MapFrame(List<Point3D> points) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MapViewer");
        getContentPane().add(new Surface(points));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public MapFrame(List<Point3D> points, float minZ, float maxZ) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MapViewer");
        getContentPane().add(new Surface(points, minZ, maxZ));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
