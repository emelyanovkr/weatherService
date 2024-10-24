package emv.weather.repository;

import emv.weather.model.WeatherEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherEntry, Integer> {

  List<WeatherEntry> findByRecordDateBetween(LocalDate startDate, LocalDate endDate);
}
