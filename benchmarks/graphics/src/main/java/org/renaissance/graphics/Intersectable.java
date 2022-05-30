package org.renaissance.graphics;

import java.util.List;

public interface Intersectable {
    List<Intersection> intersect(Ray ray);
}
