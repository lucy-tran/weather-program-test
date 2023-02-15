package comp127.weather;

import comp127.weather.api.OpenWeatherProvider;
import comp127.weather.widgets.*;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A weather UI that shows a collection of small widgets down one edge, and allows the user to
 * select a widget to enlarge.
 */
public class WeatherProgram {

    private static Double    // Location for which we're fetching weather
            FORECAST_LAT = 44.936593,  // OLRI 256 (approximate)
            FORECAST_LON = -93.168650;

    private CanvasWindow canvas;

    private double miniWidgetSize, largeWidgetSize;
    private List<WeatherWidget> miniWidgets, largeWidgets;
    private WeatherWidget displayedLargeWidget;
    private Rectangle selectionHighlight;
    private static final Map<String, List<Double>> LOCATIONS = Map.of(
            "Tokyo", List.of(35.6762,  139.6503),
            "Cairo", List.of(35.6762, 139.6503),
            "NYC", List.of(40.7128, 74.0060),
            "Paris", List.of(48.8566, 2.3522),
            "Ho Chi Minh", List.of(10.8231, 106.6297)
    );

    /**
     * Opens a window, displays the weather UI, and fetches weather conditions.
     *
     * @param largeWidgetSize The height and width of the large widget. The window size is derived
     *                        from this value combined with the number of widget choices.
     */
    public WeatherProgram(double largeWidgetSize) {
        this.largeWidgetSize = largeWidgetSize;
        largeWidgets = createWidgets(largeWidgetSize);

        miniWidgetSize = largeWidgetSize / largeWidgets.size();  // so they stack along one edge
        miniWidgets = createWidgets(miniWidgetSize);

        canvas = new CanvasWindow(
                "Weather Display",
                (int) Math.round(largeWidgetSize + miniWidgetSize),
                (int) Math.round(largeWidgetSize));
        canvas.setBackground(new Color(153, 204, 255));

        selectionHighlight = new Rectangle(0, 0, miniWidgetSize, miniWidgetSize);  // selectWidgetAtIndex() will position it
        selectionHighlight.setStroked(false);
        selectionHighlight.setFillColor(new Color(0x7FFFFFFF, true));
        canvas.add(selectionHighlight);

        double y = 0;
        for (WeatherWidget widget : miniWidgets) {
            canvas.add(widget.getGraphics(), largeWidgetSize, y);
            y += miniWidgetSize;
        }
        selectWidgetAtIndex(0);

        canvas.onMouseMove(event -> {
            if (displayedLargeWidget != null && event.getPosition().getX() < largeWidgetSize) {
                displayedLargeWidget.onHover(event.getPosition());
            }
        });

        canvas.onClick(event -> {
            if (event.getPosition().getX() >= largeWidgetSize) {
                selectWidgetAtIndex(
                        (int) (event.getPosition().getY() / largeWidgetSize * miniWidgets.size()));
            } else if (displayedLargeWidget != null) {
                displayedLargeWidget.onClick(event.getPosition()); // added to interact with CloudWidget.
            }
        });

        updateWeather();
    }

    public List<String> updateLocation(String location) {
        FORECAST_LAT = LOCATIONS.get(location).get(0);
        FORECAST_LON = LOCATIONS.get(location).get(1);

        List<String> widgetsCities = updateWeather();
        return widgetsCities;
    }

    private List<String> updateWeather() {
        List<String> widgetsCities = new ArrayList<>();
        new OpenWeatherProvider(FORECAST_LAT, FORECAST_LON)
                .fetchWeather((weatherData) -> {
                    for (WeatherWidget widget : miniWidgets) {
                        String city = widget.update(weatherData);
                        widgetsCities.add(city);
                    }
                    for (WeatherWidget widget : largeWidgets) {
                        String city = widget.update(weatherData);
                        widgetsCities.add(city);
                    }
                    canvas.draw();
                });
        return widgetsCities;
    }

    private List<WeatherWidget> createWidgets(double size) {
        return List.of(
                new ForecastWidget(size),
                new TemperatureWidget(size),
                new SunWidget(size),
                new WindWidget(size),
                new CloudWidget(size),
                new PressureHumidityWidget(size));
    }

    private void selectWidgetAtIndex(int index) {
        if (index < miniWidgets.size()) {
            if (displayedLargeWidget != null) {
                canvas.remove(displayedLargeWidget.getGraphics());
            }
            displayedLargeWidget = largeWidgets.get(index);
            canvas.add(displayedLargeWidget.getGraphics());
        }
        selectionHighlight.setPosition(largeWidgetSize, miniWidgetSize * index);
    }

    public static void main(String[] args) {
        new WeatherProgram(600);
    }
}
