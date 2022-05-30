package org.renaissance.graphics;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Ray {
    public final Vector3D origin;
    public final Vector3D direction;

    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vector3D pointAt(double t) {
        return origin.add(direction.scalarMultiply(t));
    }
}
