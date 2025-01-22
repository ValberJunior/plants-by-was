import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;

public class TracingExample {
    private static final Tracer tracer = GlobalOpenTelemetry.getTracer("exampleTracer");

    public static void main(String[] args) {
        tracer.spanBuilder("exampleSpan").startSpan().end();
    }
}