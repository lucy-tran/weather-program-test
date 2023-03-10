package comp127.weather.widgets;

import comp127.weather.api.WeatherData;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

/**
 * A square widget that graphically presents weather conditions.
 */
public interface WeatherWidget {

    /**
     * This widget’s visual interface, which you can add to a CanvasWindow or GraphicsGroup.
     */
    GraphicsObject getGraphics();

    /**
     * Displays the given weather data in the widget, writing over any information the widget was
     * previously displaying.
     *
     * @return The city of the weather data
     */
    String update(WeatherData data);

    /**
     * Called when the mouse moves over the widget.
     *
     * @param position A location in the widget’s local coordinates.
     */
    void onHover(Point position);

    /**
     * Called when the mouse is clicked on the widget.
     *
     * @param position A location in the widget’s local coordinates.
     */
    void onClick(Point position);
}

