package comp127.weather.unit;

import comp127.weather.api.ForecastConditions;
import comp127.weather.api.WeatherDataFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UnitTests {
    @Test

    void forecastBoxTest(){

        WeatherDataFixtures.generateWeatherData(1);
    }
    void check(){assertTrue(true);}



}


