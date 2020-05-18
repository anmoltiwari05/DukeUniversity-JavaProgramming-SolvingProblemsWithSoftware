import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int NumPoints=0;
        for(Point currPt : s.getPoints()){
            NumPoints++;
        }
        return NumPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int NumPoints = getNumPoints(s);
        double perimeter = getPerimeter(s);
        double Average = perimeter / NumPoints;
        return Average;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double LargestSide=0.0;
        double CurrentSide=0.0;
        Point prvPt = s.getLastPoint();
        for (Point CurrPt: s.getPoints()){
            CurrentSide = CurrPt.distance(prvPt);
            if (CurrentSide>LargestSide){
                LargestSide = CurrentSide;
            }
            prvPt=CurrPt;
        }
        return LargestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double LargestX = 0.0;
        double CurrentX = 0.0;
        for (Point CurrPt: s.getPoints()){
            CurrentX = CurrPt.getX();
            if (CurrentX>LargestX){
            LargestX=CurrentX;
            }      
        }
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double LargestPeri = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape CurrShape = new Shape(fr);
            double Peri = getPerimeter(CurrShape);
            if (Peri>LargestPeri){
                LargestPeri = Peri;
            }            
        }
        return LargestPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double LargestPeri = 0.0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape CurrShape = new Shape(fr);
            double Peri = getPerimeter(CurrShape);
            if (Peri>LargestPeri){
                LargestPeri=Peri;
                temp = f;
            }
        } 
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int points = getNumPoints(s);
        System.out.println("number of points="+points);
        double avgLength = getAverageLength(s);
        System.out.println("avg length="+avgLength);
        double LargestSide = getLargestSide(s);
        System.out.println("Largest Side="+LargestSide);
        double LargestX=getLargestX(s);
        System.out.println("LargestX="+LargestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double LargestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter from All the file is="+LargestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String f = getFileWithLargestPerimeter();
        System.out.println("The file with the largest perimeter is="+f);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
