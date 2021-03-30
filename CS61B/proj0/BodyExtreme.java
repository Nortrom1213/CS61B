import java.lang.Math;

public class BodyExtreme{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
    public double radius;
	public boolean death;
	public int colorindex;
	private static final double g=6.67e-11;
	public BodyExtreme(double xP, double yP, double xV, double yV, 
				double m, String img,int index){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
        radius=9e+9;
		death=false;
		colorindex=index;			
	}

	public BodyExtreme(BodyExtreme b){
		this.xxPos=b.xxPos;
		this.yyPos=b.yyPos;
		this.xxVel=b.xxVel;
		this.yyVel=b.yyVel;
		this.mass=b.mass;
		this.imgFileName=b.imgFileName;
        this.radius=b.radius;
		this.colorindex=b.colorindex;
	}

	public double calcDistance(BodyExtreme b){
		return Math.sqrt((this.xxPos-b.xxPos)*(this.xxPos-b.xxPos)+(this.yyPos-b.yyPos)*(this.yyPos-b.yyPos));
	}

	public double calcForceExertedBy(BodyExtreme a){
		return this.mass*a.mass/(this.calcDistance(a)*this.calcDistance(a))*g;
	}

	public double calcForceExertedByX(BodyExtreme a){
		return this.calcForceExertedBy(a)*(a.xxPos-this.xxPos)/this.calcDistance(a);
	}

	public double calcForceExertedByY(BodyExtreme a){
		return this.calcForceExertedBy(a)*(a.yyPos-this.yyPos)/this.calcDistance(a);
	}

	public double calcNetForceExertedByX(BodyExtreme[] a){
		double force=0;
		for(int i=0;i<a.length;i++){
			if (!a[i].equals(this) && !a[i].death){
				force+=this.calcForceExertedByX(a[i]);
			}
		}
		return force;
	}

	public double calcNetForceExertedByY(BodyExtreme[] a){
		double force=0;
		for(int i=0;i<a.length;i++){
			if (!a[i].equals(this) && !a[i].death){
				force+=this.calcForceExertedByY(a[i]);
			}
		}
		return force;
	}

    public boolean isCollision(BodyExtreme a){
        if (this.calcDistance(a)<=this.radius+a.radius && !a.death){
            return true;
        }
        else{
            return false;
        }
    }

	public void update(double dt, double fX, double fY){
		double ax=fX/mass; 
		double ay=fY/mass;
		xxVel+=ax*dt;
		yyVel+=ay*dt;
		xxPos+=xxVel*dt;
		yyPos+=yyVel*dt;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
	

}