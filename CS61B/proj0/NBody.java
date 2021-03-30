public class NBody{
    public static double readRadius(String filename){
        In data = new In(filename);
        int nvm = data.readInt();
        double ans = data.readDouble();
        return ans;
    }

    public static Body[] readBodies(String filename){
        In data = new In(filename);
        int num = data.readInt();
        double rad = data.readDouble();
        Body[] ans = new Body[num];
        for(int i =0; i < num; i++){
            double xp = data.readDouble();
            double yp = data.readDouble();
            double xV = data.readDouble();
            double yV = data.readDouble();
            double m = data.readDouble();
            String img = data.readString();
            ans[i] = new Body(xp,yp,xV,yV,m,img);
        }
        return ans;
    }

    public static void main(String[] args){
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename); 
        int num = bodies.length;

        StdAudio.play("audio/2001.mid");
        
        /* Draw the background */
        StdDraw.setScale(-radius,radius);
        StdDraw.picture(0,0,"images/starfield.jpg");

        /* Drawing All the Body */
        for ( Body body : bodies){
            body.draw();
        }

        StdDraw.enableDoubleBuffering();

        double t = 0;
        while (t<T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];

            for(int i = 0;i < num; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for(int i = 0;i < num ;i++){
                bodies[i].update(dt,xForces[i],yForces[i]);   
            }

            StdDraw.picture(0,0,"images/starfield.jpg");
            
            for ( Body body : bodies){
               body.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }


        StdOut.printf("%d\n",num);
        StdOut.printf("%.2e\n",radius);
        for (int i = 0; i< num; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                         bodies[i].xxPos,bodies[i].yyPos,bodies[i].xxVel,
                         bodies[i].yyVel,bodies[i].mass,bodies[i].imgFileName);
        }
    }
}