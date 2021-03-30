public class Body{
    public double xxPos,yyPos,xxVel,yyVel,mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        return Math.sqrt( (this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) + (this.yyPos - b.yyPos ) * (this.yyPos - b.yyPos) )   ;
    }

    public double calcForceExertedBy(Body b){
        return this.mass * b.mass * 6.67 * 0.00000000001 / (this.calcDistance(b) * this.calcDistance(b));
    }

    public double calcForceExertedByX(Body b){
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] b){
        double ans = 0;
        for(int j = 0; j < b.length ;j++){
            if (!this.equals(b[j]))
                ans += this.calcForceExertedByX(b[j]);
        }
        return ans;
    }

    public double calcNetForceExertedByY(Body[] b){
        double ans = 0;
        for(int j = 0; j < b.length ;j++){
            if (!this.equals(b[j]))
                ans += this.calcForceExertedByY(b[j]);
        }
        return ans;
    }

    public void update(double t,double xf,double yf){
        double ax = xf / mass;
        double ay = yf / mass;
        this.xxVel += ax * t;
        this.yyVel += ay * t;
        this.xxPos += xxVel * t;
        this.yyPos += yyVel * t; 
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
    }
}