package comp127.weather.unit;
import comp127.weather.WeatherProgram;
import comp127.weather.widgets.*;
import org.junit.jupiter.api.Test;

import comp127.weather.WeatherProgram;
import comp127.weather.widgets.CloudWidget;
import comp127.weather.widgets.WeatherWidget;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MockitoTests {
    WeatherWidget weatherWidget1 = mock(ForecastWidget.class);
    WeatherWidget weatherWidget2 = mock(PressureHumidityWidget.class);
    WeatherWidget weatherWidget3 = mock(SunWidget.class);
    WeatherWidget weatherWidget4 = mock(WindWidget.class);
    WeatherWidget weatherWidget5 = mock(CloudWidget.class);
    WeatherWidget weatherWidget6 = mock(PressureHumidityWidget.class);


    @Test
    public void getGraphicsTest(){
        WeatherProgram weatherProgram = new WeatherProgram(600);
//
        CloudWidget widget = mock(CloudWidget.class);

//        when(widget.getGraphics()).thenReturn("graphics")
        widget.getGraphics();
        Mockito.verify(widget).getGraphics();
    }
}
