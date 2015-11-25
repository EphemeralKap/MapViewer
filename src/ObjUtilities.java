
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kap
 */
public class ObjUtilities {

    public ObjUtilities() {

    }

    /**
     * Returns the maximum Z value of an OBJ file.
     *
     * @param obj - URL for OBJ wavefront file.
     * @return max z value for points in OBJ file.
     */
    public static float maxZValue(String obj) {
        float max = -999999f;
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(obj));

            while ((line = br.readLine()) != null) {
                String[] results = line.split(" ");
                if (!results[0].equals("v")) {
                    continue;
                }

                float z = Float.parseFloat(results[3]);
                if (z > max) {
                    max = z;
                }
            }
            br.close();
        } catch (IOException e) {

        }

        return max;
    }

    /**
     * Returns the minimum Z value of an OBJ file.
     *
     * @param obj - the OBJ wavefront file.
     * @return min z value for points in OBJ file.
     */
    public static float minZValue(String obj) {
        float min = 999999f;
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(obj));

            while ((line = br.readLine()) != null) {
                String[] results = line.split(" ");
                if (!results[0].equals("v")) {
                    continue;
                }

                float z = Float.parseFloat(results[3]);
                if (z < min) {
                    min = z;
                }
            }
            br.close();
        } catch (IOException e) {

        }
        return min;
    }

    /**
     * Returns a list of points in a OBJ file, within a certain Z value range.
     *
     * @param obj - URL for OBJ wavefront file.
     * @param minZ - min Z value for points to be included.
     * @param maxZ - max Z value for points to be included.
     * @return list of points in range minZ to maxZ.
     */
    public static java.util.List<Point3D> PointsInOBJ(String obj, float minZ, float maxZ) {
        List<Point3D> points = new ArrayList();
        String line;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(obj));

            while ((line = br.readLine()) != null) {
                String[] results = line.split(" ");
                if (!results[0].equals("v")) {
                    continue;
                }

                float z = Float.parseFloat(results[3]);
                if (z > minZ && z < maxZ) {
                    points.add(new Point3D(
                            Float.parseFloat(results[1]),
                            Float.parseFloat(results[2]),
                            Float.parseFloat(results[3])));
                }
            }
        } catch (IOException ex) {
            System.err.println("Error opemning/reading with BufferedWriter" + ex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.err.println("Error closing BufferedWriter" + ex.getMessage());
            }
        }

        return points;
    }

    /**
     * Returns a list of points in a OBJ file.
     *
     * @param obj - URL for OBJ wavefront file.
     * @return list of points in the OBJ file.
     */
    public static java.util.List<Point3D> PointsInOBJ(String obj) {
        List<Point3D> points = new ArrayList();
        String line;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(obj));

            while ((line = br.readLine()) != null) {
                String[] results = line.split(" ");
                if (!results[0].equals("v")) {
                    continue;
                }

                points.add(new Point3D(
                        Float.parseFloat(results[1]),
                        Float.parseFloat(results[2]),
                        Float.parseFloat(results[3])));

            }
        } catch (IOException ex) {
            System.err.println("Error opemning/reading with BufferedWriter" + ex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.err.println("Error closing BufferedWriter" + ex.getMessage());
            }
        }

        return points;
    }

    /**
     * Creates a new OBJ file from specified points.
     * @param output - URL to save new OBJ file to.
     * @param points - The points to add to the OBJ file.
     */
    public static void createObjFromPoints(String output, java.util.List<Point3D> points) {
        File file = new File(output);
        BufferedWriter bw = null;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            bw = new BufferedWriter(new FileWriter(file));
            for (Point3D point : points) {
                bw.write("v " + point.x + " " + point.y + " " + point.z + " \n");
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException ex) {
                System.err.println("Error closing BufferedWriter " + ex.getMessage());
            }
        }

    }
}
