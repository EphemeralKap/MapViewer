
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kap
 */
public class Surface extends JPanel {

    public float minZ = -99999;
    public float maxZ = 99999;

    List<Point3D> points;

    public Surface(List<Point3D> points) {
        setPreferredSize(new Dimension(1000, 1000));
        this.points = points;
    }

    public Surface(List<Point3D> points, float minZ, float maxZ) {
        setPreferredSize(new Dimension(1000, 1000));
        this.points = points;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);

        for (Point3D point : points) {
            if (shouldDraw(Math.round(point.z))) {
                int x = Math.round(point.x * -30);
                int y = Math.round(point.y * 30);
                g.drawLine(x + 500, y + 700, x + 500, y + 700);
            }
        }
        System.out.println(points.size());
    }

    public boolean shouldDraw(int z) {
        if (z > minZ && z < maxZ) {
            return true;
        } else {
            return false;
        }
    }

}
