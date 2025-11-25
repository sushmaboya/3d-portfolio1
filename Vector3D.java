 public class Vector3D {
    public double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector3D normalize() {
        double len = length();
        return new Vector3D(x/len, y/len, z/len);
    }

    public static Vector3D add(Vector3D a, Vector3D b) {
        return new Vector3D(a.x+b.x, a.y+b.y, a.z+b.z);
    }

    // More vector math as needed
}
