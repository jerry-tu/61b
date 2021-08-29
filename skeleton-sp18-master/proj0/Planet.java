public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) { 
        xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b) {
        double xP = b.xxPos;
        double yP = b.yyPos;
        return Math.sqrt((xP - xxPos) * (xP - xxPos) + (yP - yyPos) * (yP - yyPos));
    }

    public double calcForceExertedBy(Planet b) {
        double distance = calcDistance(b);
        return G * mass * b.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet b) {
        double distance = calcDistance(b);
        double force = calcForceExertedBy(b);
        return force * (b.xxPos - xxPos) / distance;
    }

    public double calcForceExertedByY(Planet b) {
        double distance = calcDistance(b);
        double force = calcForceExertedBy(b);
        return force * (b.yyPos - yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] b_arr) {
        double netforce = 0;
        for (Planet i : b_arr) {
            if (!this.equals(i)) {
                netforce += this.calcForceExertedByX(i);
            }
        }
        return netforce;
    }

    public double calcNetForceExertedByY(Planet[] b_arr) {
        double netforce = 0;
        for (Planet i : b_arr) {
            if (!this.equals(i)) {
                netforce += this.calcForceExertedByY(i);
            }
        }
        return netforce;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
