package org.renaissance.graphics;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Intersection {
    public final Vector3D point;
    public final Vector3D normal;

    public Intersection(Vector3D point, Vector3D normal) {
        this.point = point;
        this.normal = normal;
    }
}
