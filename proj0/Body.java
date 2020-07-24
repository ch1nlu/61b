public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  /** Constructor */
  public Body(double xP, double yP, double xV,double yV,
  double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  /** Constructor to initialize an identical Body object */
  public Body(Body b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }
  /** calcDistance computes the distance between the supplied body and
  the body that is doing the calculation. */
  public double calcDistance(Body b) {
    double dist;
    double squareSum;
    squareSum = Math.pow(this.xxPos - b.xxPos , 2) +
     Math.pow(this.yyPos - b.yyPos, 2);
    dist = Math.sqrt(squareSum);
    return dist;
  }
  /** calcForceExertedBy computes the force exterted on this body by the
  given body */
  public double calcForceExertedBy(Body b) {
    double G = 6.67e-11;
    double force;
    double distance;
    distance = this.calcDistance(b);
    force = G * this.mass * b.mass / Math.pow(distance,2);
    return force;
  }
  /** calcForceExertedByX/Y computes the foce in the X/Y direction */
  public double calcForceExertedByX(Body b) {
    double force = this.calcForceExertedBy(b);
    double forceX;
    double dx = b.xxPos - this.xxPos;
    forceX =  force * dx / this.calcDistance(b);
    return forceX;
  }
  public double calcForceExertedByY(Body b) {
    double force = this.calcForceExertedBy(b);
    double forceY;
    double dy = b.yyPos - this.yyPos;
    forceY =  force * dy / this.calcDistance(b);
    return forceY;
  }
  /** calcNetForceExerteedByX/Y computes takes in an array of Bodys
  and computes the net X and net Y force exerted by all bodies in that
  array upon the current Body. */
  public double calcNetForceExertedByX(Body[] allBodys) {
    double netForceX = 0;
    int i = 0;
    while (i < allBodys.length) {
      if(this.equals(allBodys[i]) == false) {
        netForceX = netForceX + this.calcForceExertedByX(allBodys[i]);
      }
      i++;
    }
    return netForceX;
  }
  public double calcNetForceExertedByY(Body[] allBodys) {
    double netForceY = 0;
    int i = 0;
    while (i < allBodys.length) {
      if(this.equals(allBodys[i]) == false) {
        netForceY = netForceY + this.calcForceExertedByY(allBodys[i]);
      } // end if
      i++;
    } // end while
    return netForceY;
  } // end public
  /** update updates the body's position and velocity instance variables */
  public void update(double dt, double fX,double fY) {
    double aX; // acceleration in the X direction
    double aY;
    aX = fX / this.mass;
    aY = fY / this.mass;
    this.xxVel = this.xxVel + dt * aX;
    this.yyVel = this.yyVel + dt * aY;
    this.xxPos = this.xxPos + dt * this.xxVel;
    this.yyPos = this.yyPos + dt * this.yyVel;
  } // end public

  /** Draw one body*/
  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }

}
