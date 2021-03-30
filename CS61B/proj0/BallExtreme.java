public class Ball{
    /* A ball can only move directly up and down */
    public double xxPos,yyPos,xxVel,yyVel;
    public double radius;
    
    public Ball(double xP, double yP, double yV, double r){
        xxPos = xP;
        yyPos = yP;
        yyVel = yV;
        radius = r;
    }

    public void search(Body[] bodies){
        /* find whether ball is hit by other planets */

        /* pretend : a ball is a body class */
        Body fakebody = new Body(xxPos,yyPos,0,yyVel,0,"it does not matter");
        
        for (int i = 0 ; i < bodies.length() ; i++){
            if (bodies[i].calcDistance(fakebody) <= radius  ){
                this.yyVel = - this.yyVel;
                return;
            }
        }
    }


}