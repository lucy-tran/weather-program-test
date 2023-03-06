package comp127.weather.unit;
import comp127.weather.WeatherProgram;
import comp127.weather.widgets.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MockitoTests {
    WeatherWidget weatherWidget1 = mock(ForecastWidget.class);
    WeatherWidget weatherWidget2 = mock(PressureHumidityWidget.class);
    WeatherWidget weatherWidget3 = mock(SunWidget.class);
    WeatherWidget weatherWidget4 = mock(WindWidget.class);
    WeatherWidget weatherWidget5 = mock(CloudWidget.class);
    WeatherWidget weatherWidget6 = mock(PressureHumidityWidget.class);

    @Test
    void demoTest() {
        WeatherProgram
    }

}
