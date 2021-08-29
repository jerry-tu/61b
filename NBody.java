public class NBody {
    
    public static double readRadius(String txtfile) {
        In in = new In(txtfile);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String txtfile) {
        In in = new In(txtfile);
        int PlanetNum = in.readInt();
        in.readDouble();
        Planet[] planetArr = new Planet[PlanetNum];

        for(int i = 0; i < PlanetNum; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planetArr[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planetArr;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for(Planet i : planets) {
            i.draw();
        }

        StdDraw.enableDoubleBuffering();

        for(double t = 0; t <= T; t += dt) {
            double[] xForce = new double[planets.length];
            double[] yForce = new double[planets.length];

            for(int i = 0; i < planets.length; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}

    }

}
