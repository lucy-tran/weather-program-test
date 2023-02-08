package comp127.weather.unit;
import comp127.weather.api.ForecastConditions;
import comp127.weather.widgets.ForecastBox;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.util.Date;

public class UnitTests {

   @Test
   @DisplayName(" test")
   void ForecastBoxTest(){
      LocalDateTime randomTimeOfDay = LocalDateTime.now().plusHours(5);
//      Date predictionTime = toDate(randomTimeOfDay);
      Date testDate = Date.from(Instant.from(ZonedDateTime.of(randomTimeOfDay, ZoneId.systemDefault())));

      ForecastConditions forecastConditions = new ForecastConditions(testDate, 10, 20, 30,
              40, 50, 60, 70, 80, "cloudy", "01d.png");
      ForecastBox f = new ForecastBox(forecastConditions, 50, 30, 100, 200);
      System.out.println(f.getForecast());
      assertEquals(30, f.getForecast().getMaxTemperature());
   }
}
