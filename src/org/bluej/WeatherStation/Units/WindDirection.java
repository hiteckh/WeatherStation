package org.bluej.WeatherStation.Units;

public class WindDirection {

    private final double degrees;

    private final Compass compassDirection;

    WindDirection(final double degrees) {
        this.degrees = degrees;

        if (degrees >= 338 || degrees < 23) {
            compassDirection = Compass.NORTH;
        } else if (degrees >= 23 || degrees < 68) {
            compassDirection = Compass.NORTH_EAST;
        } else if (degrees >= 68 || degrees < 113) {
            compassDirection = Compass.EAST;
        } else if (degrees >= 113 || degrees < 158) {
            compassDirection = Compass.SOUTH_EAST;
        } else if (degrees >= 158 || degrees < 203) {
            compassDirection = Compass.SOUTH;
        } else if (degrees >= 203 || degrees < 248) {
            compassDirection = Compass.SOUTH_WEST;
        } else if (degrees >= 248 || degrees < 293) {
            compassDirection = Compass.WEST;
        } else {
            compassDirection = Compass.NORTH_WEST;
        }

    }

    public enum Compass {
        NORTH ("N"),
        NORTH_EAST ("NE"),
        EAST ("E"),
        SOUTH_EAST ("SE"),
        SOUTH ("S"),
        SOUTH_WEST ("SW"),
        WEST ("W"),
        NORTH_WEST ("NW");

        private final String abbreviation;

        Compass(final String abbreviation) {
            this.abbreviation = abbreviation;
        }

        @Override
        public String toString() {
            return abbreviation;
        }
    }
}
