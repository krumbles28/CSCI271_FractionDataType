/*************************************************************************
* Project 2 for CSCI 271-001 Spring 2026
*
* Author: Kyrah Eagleton
* OS: Ubuntu Debian Linux 21.1
* Compiler: javac 25.0.1
* Date: Feburary 2, 2026
*
* Purpose
* This program calls a new data type called "Fraction"
* this data type creates a fraction and preforms all 
* operations that can be applied to a fraction.
*************************************************************************/
/*******************************************************************
* I declare and confirm the following:
* - I have not discussed this program code with anyone other than my
* instructor or the teaching assistants assigned to this course.
* - I have not used programming code obtained from someone else,
* or any unauthorised sources, including the Internet, either
* modified or unmodified.
* - If any source code or documentation used in my program was
* obtained from other sources, like a text book or course notes,
* I have clearly indicated that with a proper citation in the
* comments of my program.
* - I have not designed this program in such a way as to defeat or
* interfere with the normal operation of the supplied grading code.
*
* Kyrah Eagleton
* W30687981
********************************************************************/

public class CSCI271_Assignment2_KyrahEagleton{
      public static void main(String[] args) {
    
        Fraction a = new Fraction(16);
        Fraction b = new Fraction(3,5).add(new Fraction(7));
        Fraction c = new Fraction(6,7);
        Fraction results = c.multiply(a.divide(b));
        System.out.println(results);
        Fraction test = new Fraction (1,3);
        System.out.println(test.pow(-3));

    }
    public static class Fraction{
        public long Num, Dem; // initianlizes the numerator and denominator
     
        /*****************************<GCD>****************************
        * Description: finds the greatest common denominator
        *
        * Parameters: long a and long b
        *
        * Pre: both a and b are longs
        *
        * Post: returns a long
        * 
        * Returns: the greatest common denominator
        *
        * Called by: Fraction, add, and subtract
        * Calls: none
        ************************************************************************/
        private  long GCD (long a, long b){
            long temp; 
            while (b != 0){
                temp = b;
                b = a % b;
                a = temp;

            }
            return a;
        }
         /*****************************<Fraction>****************************
        * Description: Creates the fraction in the fraction class
        *
        * Parameters: long num and long dem
        *
        * Pre: both num and dem are longs
        *
        * Post: a fration is returned with a Numerator and Denomenator both of which
        * are simplified
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: add, subtract, multiply, divide, pow, and negate
        * Calls: GCD, getNum, and getDem
        ************************************************************************/
        public Fraction (long num, long dem){
            this.Dem = dem;
            this.Num = num;
            long gcd = GCD(getNum(), getDem());
            Num = getNum() / gcd;
            Dem = getDem() / gcd;
            if(Dem < 0){
                Num = Num + (-2*Num);
                Dem = Dem + (-2*Dem);
            }
            
        }
         /*****************************<Fraction>****************************
        * Description: Creates the fraction in the fraction class
        *
        * Parameters: long x
        *
        * Pre: x is a long
        *
        * Post: a fration is returned with a Numerator and Denomenator of 1
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: N/A
        * Calls: N/A
        ************************************************************************/
        public Fraction (long x){
            this.Num = x;
            this.Dem = 1;
        }
         /*****************************<getNum>****************************
        * Description: returns the Numerator of a fraction
        *
        * Parameters: N/A
        *
        * Pre: Fraction has a numerator
        *
        * Post: Numerator is returned
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: Fraction, toString, GCD, add, subtract, multiply, divide, 
        * pow, and negate
        * Calls:N/A
        ************************************************************************/
        private long getNum(){
            return Num;
        }
        /*****************************<getDem>****************************
        * Description: returns the Denomenator of a fraction
        *
        * Parameters: N/A
        *
        * Pre: Fraction has a denomenator
        *
        * Post: Denomenator is returned
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: Fraction, toString, GCD, add, subtract, multiply, divide, 
        * pow, and negate
        * Calls:N/A
        ************************************************************************/
        private long getDem(){
            return Dem;
        }

        @Override
        public String toString(){
            String line= null;
            if ((getDem() == 0 )&& (getNum() > 0)) {
                line = "Infinity";

            }else if ((getDem() == 0) && (getNum()< 0)) {
                line = "-Infinity";
            }else if (getDem()==1) {
                line = getNum() +"";
            }else if (getDem()==0 && getNum()==0) {
                line = "NaN";
            }else{
                line = getNum()+"/"+getDem();
            }
           return line;
        }
        /*****************************<add>****************************
        * Description: returns the sum of 2 fractions
        *
        * Parameters: Fraction inf
        *
        * Pre: inf is a fraction
        *
        * Post: new fraction is returned
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: N/A
        * Calls:getNum, getDem, GCD, Fraction
        ************************************************************************/
        public Fraction add(Fraction inf){
            long inNum = inf.getNum();
            long inDem = inf.getDem();
            long lcm = (inDem*getDem())/GCD(inDem,getDem());
            long a = lcm/Dem;
            long b = lcm/inDem;
            
            inNum = inNum*b;
            Num =Num*a;
            Num = inNum + Num;

            Dem = lcm ;

            Fraction result = new Fraction (Num, Dem);
            return result;
            
        }
        /*****************************<subtract>****************************
        * Description: returns the difference of 2 fractions
        *
        * Parameters: Fraction inf
        *
        * Pre: inf is a fraction
        *
        * Post: new fraction is returned
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: N/A
        * Calls:getNum, getDem, GCD, Fraction
        ************************************************************************/
        public Fraction subtract(Fraction inf){
             long inNum = inf.getNum();
            long inDem = inf.getDem();
            long lcm = (inDem*getDem())/GCD(inDem,getDem());
            long a = lcm/Dem;
            long b = lcm/inDem;
            
            inNum = inNum*b;
            Num =Num*a;
            Num = Num - inNum;

            Dem = lcm ;

            Fraction result = new Fraction (Num, Dem);
            return result;
        }
        /*****************************<divide>****************************
        * Description: returns the quotient of 2 fractions
        *
        * Parameters: Fraction inf
        *
        * Pre: inf is a fraction
        *
        * Post: new fraction is returned
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: pow
        * Calls:getNum, getDem, Fraction
        ************************************************************************/
        public Fraction divide(Fraction inf){
            long inNum = inf.getNum();
            long inDem = inf.getDem();

            Num = inDem * Num;
            Dem = inNum * Dem;


            Fraction result = new Fraction (Num, Dem);
            return result;
        }
        /*****************************<multiply>****************************
        * Description: returns the product of 2 fractions
        *
        * Parameters: Fraction inf
        *
        * Pre: inf is a fraction
        *
        * Post: new fraction is returned
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: N/A
        * Calls:getNum, getDem, Fraction
        ************************************************************************/
        public Fraction multiply(Fraction inf){
            long inNum = inf.getNum();
            long inDem = inf.getDem();

            Num = inNum * Num;
            Dem = inDem * Dem;


            Fraction result = new Fraction (Num, Dem);
            return result;
        }
        /*****************************<pow>****************************
        * Description: returns a new fraction from the original fraction 
        * taken to the power n
        *
        * Parameters: int inf
        *
        * Pre: n is an integer
        *
        * Post: new fraction is returned
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: N/A
        * Calls:getNum, getDem, divide, Fraction
        ************************************************************************/
        public Fraction pow(int n){
            if (n<0) {
                Fraction temp = new Fraction(1);
                temp = temp.divide(new Fraction(Num,Dem));
                int newN = n+(-2*n);
                Num = (long)Math.pow(temp.getNum(),newN);
                Dem = (long)Math.pow(temp.getDem(),newN);
            }else{
               Num = (long)Math.pow(Num, n);
               Dem = (long)Math.pow(Dem,n); 
            }
            

            Fraction result = new Fraction (Num, Dem);
            return result;
        }
        /*****************************<negate>****************************
        * Description: returns the negations fraction
        *
        * Parameters: N/A
        *
        * Pre: N/A
        *
        * Post: new fraction is returned
        * 
        * Returns: returns a simplified fraction
        *
        * Called by: N/A
        * Calls:getNum, getDem, Fraction
        ************************************************************************/
        public Fraction negate(){
             Fraction result = new Fraction (getNum() + (-2*getNum()),Dem);
        return result;
    }
   
    
}
}
