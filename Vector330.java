/**  @author C2C Nathan Lervold<br>
 *  Documentation: None<br>
 *  Description:  Performs Vector operations.  Used from CS330 PEX, only includes applicable functions
 */
public class Vector330 {
    //instance attributes
    private double x;
    private double y;

    /**
     *Vector330- no argument constructor
     */
    public Vector330(){
        this.x = 0;
        this.y = 0;
    }

    /**
     *Vector330- constructor initialized with integers
     */
    public Vector330(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * scale- multiply vector by a scalar value
     * @param scaleFactor - scalar value
     * @return - a new vector multiplied by scalar
     */
    public Vector330 scale(double scaleFactor){
        Vector330 newV = new Vector330();
        newV.x = this.x * scaleFactor;
        newV.y = this.y * scaleFactor;
        return newV;
    }

    /**
     * magnitude- calculate the magnitude of the vector
     * @return - the magnitude (double)
     */
    public double magnitude(){
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    /**
     * direction- calculates the angle in radians of the vector from the x axis
     * @return - the angle in radians (double)
     */
    public double direction(){
        return Math.atan2(this.y, this.x);
    }
}
