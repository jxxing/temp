package inner;

public class Painter {
	private int num = 6;

	private class InnerShape implements Shape{  
		public void paint(){  
			System.out.println("painter paint() method" + num);  
		}  
	}  

	public Shape getShape(){  
		return new InnerShape();  
	}     

	public static void main(String []args){  
		Painter painter = new Painter();  
		Shape shape = painter.getShape();  
		shape.paint();  
	}  
}
