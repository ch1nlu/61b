public class NBody {

  /** readRadius reads the radius of the universe from the file */
  public static double readRadius(String fileName) {
    In in = new In(fileName);
    int numberPlanets = in.readInt();
    double radius = in.readDouble();
    return radius;
  }
  /** readBodies reads the parameters from the file and creates bodys */
  public static Body[] readBodies(String fileName) {
    In in = new In(fileName);
    int numberPlanets = in.readInt();
    double radius = in.readDouble();
    Body[] Bodys = new Body[numberPlanets];
    for (int i = 0; i < numberPlanets; i++) {
      double xPos = in.readDouble();
      double yPos = in.readDouble();
      double xVel = in.readDouble();
      double yVel = in.readDouble();
      double mass = in.readDouble();
      String img = in.readString();
      Bodys[i] = new Body(xPos, yPos, xVel, yVel, mass, img);
    } // end for
    return Bodys;
  } // end public
  /** main method */
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Body[] bodies = readBodies(filename); // Read in the bodies
    double radius = readRadius(filename);
    // Drawing the Background
    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.picture(0, 0, "images/starfield.jpg");
    // Drawing more bodies
    for(int i = 0; i < bodies.length; i++) {
      bodies[i].draw();
    }
    // Creating animation
    StdDraw.enableDoubleBuffering();
    double time = 0;
    while(time <= T) {
      double[] xForces; // Array initialization is super confusing
      xForces = new double[bodies.length];
      double[] yForces;
      yForces = new double[bodies.length];
      for(int i = 0; i < bodies.length; i++) {
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
      }
      for(int i = 0; i < bodies.length; i++) {
        bodies[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.setScale(-radius, radius);
      StdDraw.clear();
      StdDraw.picture(0, 0, "images/starfield.jpg");
      // Drawing more bodies
      for(int i = 0; i < bodies.length; i++) {
        bodies[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(20);
      time = time + dt;
    }

  }


}
