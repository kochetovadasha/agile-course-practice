package ru.unn.agile.vectors.model;

public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(final double x0, final double y0, final double z0) {
        this.x = x0;
        this.y = y0;
        this.z = z0;
    }

    @Override
    public boolean equals(final Object obj) {
        final double eps = 0.01;
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(getClass() == obj.getClass())) {
            return false;
        }
        Vector tmp = (Vector) obj;
        return Math.abs(tmp.x - this.x) < eps
                && Math.abs(tmp.y - this.y) < eps
                && Math.abs(tmp.z - this.z) < eps;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static boolean isEqual(final Vector v1, final Vector v2) {
        return v1.x == v2.x && v1.y == v2.y && v1.z == v2.z;
    }

    public static double getLengthNorm(final Vector v1) {
        double len = 0;
        double sum = Math.pow(v1.x, 2) + Math.pow(v1.y, 2) + Math.pow(v1.z, 2);
        len = Math.sqrt(sum);
        return len;
    }

    public static boolean isOrthogonal(final Vector v1, final Vector v2) {
        double cosAlpha = (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z)
                            / (getLengthNorm(v1) * getLengthNorm(v2));
        return ((cosAlpha) == 0);
    }

    public static Vector getSum(final Vector v1, final Vector v2) {
        Vector res = new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
        return res;
    }

    public static Vector getDiff(final Vector v1, final Vector v2) {
        Vector res = new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
        return res;
    }

    public static double getScalarMult(final Vector v1, final Vector v2) {
        return (v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z);
    }

    public static Vector getVectMult(final  Vector v1, final Vector v2) {
        double newX = v1.y * v2.z - v1.z * v2.y;
        double newY = v1.x * v2.z - v1.z * v2.x;
        double newZ = v1.x * v2.y - v1.y * v2.x;
        Vector res = new Vector(newX, newY, newZ);
        return res;
    }

    public static boolean isComplanar(final Vector v1, final Vector v2) {
        Vector res = getVectMult(v1, v2);
        return new Vector(0, 0, 0).equals(res);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
