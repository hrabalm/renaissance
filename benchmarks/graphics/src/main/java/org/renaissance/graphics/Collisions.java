package org.renaissance.graphics;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.renaissance.Benchmark;
import org.renaissance.BenchmarkContext;
import org.renaissance.BenchmarkResult;
import org.renaissance.BenchmarkResult.Validators;
import org.renaissance.License;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static org.renaissance.Benchmark.*;

@Name("graphics-collisions")
@Group("graphics")
@Summary("A benchmark that computes sphere-ray collisions to simulate part of a raytracer.")
@Licenses(License.MIT)
@Parameter(name = "width", defaultValue = "160")
@Parameter(name = "height", defaultValue = "120")
@Parameter(name = "spheresCount", defaultValue = "100")
@Repetitions(10)
@Configuration(name = "test")
public final class Collisions implements Benchmark {
    private int width;
    private int height;
    Scene scene = null;
    @Override
    public void setUpBeforeAll(BenchmarkContext c) {
        int spheresCount = c.parameter("spheresCount").toPositiveInteger();
        width = c.parameter("width").toPositiveInteger();
        height = c.parameter("height").toPositiveInteger();
        scene = Scene.createSphereScene(spheresCount);
    }
    @Override
    public BenchmarkResult run(BenchmarkContext c) {
        OptionalInt intersectionsCount = IntStream.range(0, height).parallel().map(y -> {
            int sum = 0;
            for (int x = 0; x < width; x++) {
                Vector3D origin = new Vector3D(0, 0, 0);
                Vector3D direction = (new Vector3D(x, y, -1)).subtract(origin);
                Ray ray = new Ray(origin, direction);
                List<Intersection> intersections = scene.intersect(ray);

                sum += intersections.size();
            }
            return sum;
        }).reduce(
                Integer::sum
        );
        if (intersectionsCount.isPresent()) {
            System.out.printf("Intersections found %d\n", intersectionsCount.getAsInt());  // FIXME: Remove
        }
        return Validators.simple("nothing", 0, 0);
    }
}
