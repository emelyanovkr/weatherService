## Weather Service

1. To simplify:
    + Used main branch only (no another branch)
    + Used default public schema in postgres with no other roles
    + Used only temperature field for weather information, however: additional fields like day/night temperature, 
      humidity, pressure, wind speed, sunrise, sunset, "feels like" might be used
    + No validator used to check on entry fields
    + In database only date field used to store temperatures   
      -> might be added temperature for every hour or minute -> count avg on specified period
    + Temperature format - only Celsius
    + No logging
    + No exception handling