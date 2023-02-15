package comp127.weather.unit;
import comp127.weather.WeatherProgram;
import comp127.weather.api.ForecastConditions;
import comp127.weather.widgets.ForecastBox;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.util.Date;
import java.util.List;

public class UnitTests {
//   private static WeatherProgram weatherProgram = new WeatherProgram(600);

   @Test
   @DisplayName("Forecast Box")
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

   @Test
   @DisplayName("Location Change")
   @ParameterizedTest
   @ValueSource(strings = {"Tokyo"})
   void LocationDropdownTest(String inputCity) {
//      List<String> widgetsCities = weatherProgram.updateLocation(inputCity);
//      for (String widgetCity: widgetsCities) {
//         System.out.println(widgetCity);
//         assertEquals(widgetCity, inputCity);
//      }
   }

//   public static void main(String[] args) {
   @Test
  void cityTest(){
      WeatherProgram weatherProgram = new WeatherProgram(600);

      List<String> widgetsCities = weatherProgram.updateLocation("Tokyo");
      System.out.println("Length of list: "+widgetsCities.size());
      for (String widgetCity: widgetsCities) {
         System.out.println("Widget city: " + widgetCity);
         assertEquals(widgetCity, "Tokyo");
      }
   }}
