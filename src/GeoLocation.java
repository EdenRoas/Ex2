public class GeoLocation implements api.GeoLocation {
    double x, y, z;

    public GeoLocation()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public GeoLocation(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public GeoLocation(GeoLocation other)
    {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(api.GeoLocation g) {
        double dx = this.x - g.x();
        double dy = this.y - g.y();
        double dz = this.z - g.z();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2));
    }
}
