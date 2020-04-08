package zadanie1;

public class RGB {
	private int R_value;
    private  int G_value;
    private  int B_value;
	
     //  konstruktor 
	public RGB(int r_value, int g_value, int b_value) {
        R_value = r_value;
        G_value = g_value;
        B_value = b_value;
        
 
        
	}
        //getery
        public int getR_value() {

            return R_value;
        }
        
        public int getG_value() {
            return this.G_value;
        }
       
        public int getB_value() {
            return this.B_value;
        }
        //setery
        public void setR_value(int r_value) {
        	if(r_value <= 255 && r_value >= 0 )
           this.R_value = r_value;
       
        }

        public void setG_value(int g_value) {
        	if(g_value <=255 && g_value >= 0)
        	this.G_value = g_value;
        }

        public void setB_value(int b_value) {
        	if(b_value <=255 && b_value >=0)
        	this.B_value = b_value;
        }
        
    public String toString1(){
	System.out.println("R_value: "+this.R_value+" G_value: "+this.G_value+" B_value: " +this.B_value);
	return null;

		
	}
    
    public String toString2() {
    	
    String R=	""+R_value ;
    String G=	""+G_value ;
    String B=	""+B_value ;
		return R+G+B;
    	
    }
   
}
