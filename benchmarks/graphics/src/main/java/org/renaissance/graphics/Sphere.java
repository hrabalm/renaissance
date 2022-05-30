package org.renaissance.graphics;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class Sphere implements Intersectable {
    public final Vector3D center;
    public final double radius;

    public Sphere(Vector3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public List<Intersection> intersect(Ray ray) {
        Vector3D oc = center.subtract(ray.origin);
        double a = ray.direction.dotProduct(ray.direction);
        double b = 2.0 * oc.dotProduct(ray.direction);
        double c = oc.dotProduct(oc) - radius*radius;
        double discriminant = b*b - 4*a*c;

        if (discriminant > 0) {
            ArrayList<Intersection> out = new ArrayList<>();
            double d_root = Math.sqrt(discriminant);
            double t = (-b - d_root) / (2.0 * a);

            if (t > 0.0001 && Double.isFinite(t)) {
                Vector3D p1 = ray.pointAt(t);
                Vector3D n1 = p1.subtract(center).normalize();
                out.add(new Intersection(p1, n1));
            }

            t = (-b + d_root) / (2.0 * a);
            if (t > 0.0001 && Double.isFinite(t)) {
                Vector3D p2 = ray.pointAt(t);
                Vector3D n2 = p2.subtract(center).normalize();

                out.add(new Intersection(p2, n2));
            }
            return out;
        }
        return new ArrayList<>();
    }
}
