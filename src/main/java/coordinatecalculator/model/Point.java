package coordinatecalculator.model;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point {

    private static final Pattern PATTERN = Pattern.compile("\\(([0-9]*),([0-9]*)\\)");
    private static final int ZERO = 0;
    private static final int SQUARE = 2;
    private final Coordinate xPoint;
    private final Coordinate yPoint;

    public Point(String inputPoint) {
        Matcher matcher = PATTERN.matcher(inputPoint);
        if (!matcher.find()) {
            throw new IllegalArgumentException("잘못된 입력 형식입니다.");
        }
        this.xPoint = new Coordinate(matcher.group(1));
        this.yPoint = new Coordinate(matcher.group(2));
    }

    public Coordinate getXPoint() {
        return xPoint;
    }

    public Coordinate getYPoint() {
        return yPoint;
    }

    public int getSquare(Point anotherPoint) {
        return (int) (Math.pow(this.xPoint.subtract(anotherPoint.xPoint.getValue()), SQUARE)
                + Math.pow(this.yPoint.subtract(anotherPoint.yPoint.getValue()), SQUARE));
    }

    public boolean isDifferBothXY(Point point) {
        return !xPoint.equals(point.xPoint) && !yPoint.equals(point.yPoint);
    }

    public double getSlope(Point point) {
        if (xPoint.subtract(point.xPoint.getValue()) == ZERO) {
            return Double.MAX_VALUE;
        }
        return yPoint.subtract(point.yPoint.getValue()) / xPoint.subtract(point.xPoint.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;
        if (!xPoint.equals(point.xPoint)) return false;
        return yPoint.equals(point.yPoint);

    }

    @Override
    public int hashCode() {
        int result = xPoint.hashCode();
        result = 31 * result + yPoint.hashCode();
        return result;
    }
}
