package org.bluej.WeatherStation.Units;

/**
 * Represents the wind direction.
 */
public class WindDirection {

    /**
     * The angle, in degrees.
     * Between 0 and 365.
     */
    private final double degrees;

    /**
     * The direction, as a compass direction.
     */
    private final Compass compassDirection;

    /**
     * Constructor.
     * @param degrees The angle to represent, in degrees.
     */
    public WindDirection(final double degrees) {
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

    /**
     * @return The angle, in degrees.
     */
    public double getDegrees() {
        return degrees;
    }

    /**
     * @return The angle, as a compass direction.
     * @see Compass
     */
    public Compass getCompassDirection() {
        return compassDirection;
    }

    /**
     * Represents a direction as a compass direction.
     */
    public enum Compass {
        NORTH ("N"),
        NORTH_EAST ("NE"),
        EAST ("E"),
        SOUTH_EAST ("SE"),
        SOUTH ("S"),
        SOUTH_WEST ("SW"),
        WEST ("W"),
        NORTH_WEST ("NW");

        /**
         * The abbreviation of the direction.
         */
        private final String abbreviation;

        /**
         * Constructor.
         * @param abbreviation This direction's abbreviation.
         */
        Compass(final String abbreviation) {
            this.abbreviation = abbreviation;
        }

        @Override
        public String toString() {
            return abbreviation;
        }
    }
}
