package org.renaissance.graphics;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Scene implements Intersectable {
    final private ArrayList<Intersectable> elements;
    public Scene(ArrayList<Intersectable> elements)
    {
        this.elements = elements;
    }
    @Override
    public List<Intersection> intersect(Ray ray) {
        return elements.stream().map(e -> e.intersect(ray).stream())
                .reduce(Stream.empty(), Stream::concat)
                .collect(Collectors.toList());
    }

    public static Scene createSphereScene(int spheresCount) {
        List<Intersectable> elements = IntStream.range(0, spheresCount).mapToObj(i -> {
            Vector3D origin = new Vector3D(i, 0, 0);
            return new Sphere(origin, 0.5);
        }).collect(Collectors.toList());
        return new Scene(new ArrayList<>(elements));
    }
}
