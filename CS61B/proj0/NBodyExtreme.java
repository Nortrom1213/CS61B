public class NBodyExtreme{

    private static String imageToDraw = "images/starfield.jpg";
    private static String audioToPay = "audio/2001.mid";
    private static String spacecraftname = "images/star_destroyer.gif";

    public static double readRadius(String file){
        In in = new In(file);
        int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		String thirdItemInFile = in.readString();
		String fourthItemInFile = in.readString();
		double fifthItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static BodyExtreme[] readBodies(String file){
        In in = new In(file);
        int number=in.readInt();
        double scale=in.readDouble();
        BodyExtreme[] bodies=new BodyExtreme[number];
        for(int i=0;i<number;i++){
            double xPos=in.readDouble();
            double yPos=in.readDouble();
            double xV=in.readDouble();
            double yV=in.readDouble();
            double mass=in.readDouble();
            String address=in.readString();
            int colorindex = (int)Math.random()*4;	
            bodies[i]=new BodyExtreme(xPos,yPos,xV,yV,mass,address,colorindex);
        }
        return bodies;
    }

    public static void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double universeRadius=readRadius(filename);
        BodyExtreme[] bodies=readBodies(filename);
        double time=0;
        BodyExtreme spacecraft=new BodyExtreme(-universeRadius/2,universeRadius/2,0,0,bodies[0].mass,spacecraftname,0);
        double[] xForces=new double[bodies.length];
        double[] yForces=new double[bodies.length];

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear();
        //StdAudio.play(audioToPay);       

        StdDraw.picture(-universeRadius,universeRadius,spacecraftname);
        while(time<T){
            StdDraw.picture(0, 0, imageToDraw);
            boolean press=StdDraw.mousePressed();
            if(press){
                double mx=StdDraw.mouseX();
                double my=StdDraw.mouseY();
                spacecraft.xxPos=mx;
                spacecraft.yyPos=my;
                spacecraft.xxVel=0;
                spacecraft.yyVel=0;
            }

            //Draw bodies
            for(int i=0;i<bodies.length;i++){
                //bodies[i].draw();
                
                switch(bodies[i].colorindex){
                    case 0:
                        StdDraw.setPenColor(StdDraw.RED);
                    case 1:
                        StdDraw.setPenColor(StdDraw.PINK);
                    case 2:
                        StdDraw.setPenColor(StdDraw.YELLOW);
                    case 3:
                        StdDraw.setPenColor(StdDraw.WHITE);
                }
                
                if(!bodies[i].death){
                    StdDraw.filledCircle(bodies[i].xxPos,bodies[i].yyPos,13e+9);
                }
            }

            //Draw spacecraft
            StdDraw.picture(spacecraft.xxPos,spacecraft.yyPos,spacecraftname);

            //Forces for bodies
            for(int i=0;i<bodies.length;i++){
                xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
                yForces[i]=bodies[i].calcNetForceExertedByY(bodies);
            }
            
            //collisions of spacecraft
            for(int i=0;i<bodies.length;i++){
                if(spacecraft.isCollision(bodies[i])){
                   bodies[i].death=true;
                }
                
            }
            //Collisions of bodies
            for(int i=0;i<bodies.length-1;i++){
                for(int j=i+1;j<bodies.length;j++){
                    if(bodies[i].isCollision(bodies[j]) && !bodies[i].death){
                        bodies[i].xxVel=-bodies[i].xxVel;
                        bodies[i].yyVel=-bodies[i].yyVel;
                        bodies[j].xxVel=-bodies[j].xxVel;
                        bodies[j].yyVel=-bodies[j].yyVel;
                    }
                }
            }

            //update for spacecraft
            double xxSpforce=spacecraft.calcNetForceExertedByX(bodies);
            double yySpforce=spacecraft.calcNetForceExertedByY(bodies);
            spacecraft.update(dt,xxSpforce,yySpforce);

            //updates for bodies
            for(int i=0;i<bodies.length;i++){
                bodies[i].update(dt,xForces[i],yForces[i]);
            }
           
            StdDraw.pause(10);
            time+=dt;
            StdDraw.show();
        }

        
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < bodies.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
}