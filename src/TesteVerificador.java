import junit.framework.TestCase;

public class TesteVerificador extends TestCase {
	Matriz m1 = new Matriz(5,6,new double[][] {{1.0,1.0,1.0,0.0,2.0,27.0},{0.0,1.0,2.0,1.0,1.0,23.0},{2.0,1.0,1.0,2.0,0.0,31.0},{3.0,2.0,1.0,2.0,1.0,31.0},{2.0,1.0,2.0,3.0,1.0,22.0}});
    Matriz m2 = new Matriz(2,3,new double[][] {{2.0,1.0,3.0},{1.0,-3.0,-2.0}});
    Matriz m3 = new Matriz(4,5,new double[][] {{2.0,2.0,1.0,1.0,7.0},{1.0,-1.0,2.0,-1.0,1.0},{3.0,2.0,-3.0,-2.0,4.0},{4.0,3.0,2.0,1.0,12.0}});
    Matriz m4 = new Matriz(3,4,new double[][] {{2.0,4.0,-1.0,2.0},{3.0,6.0,1.0,3.0},{1.0,1.0,2.0,4.0}});
    Matriz m5 = new Matriz(2,3,new double[][] {{2.0,1.0,3.0},{4.0,2.0,6.0}});
    Matriz m6 = new Matriz(2,3,new double[][] {{2.0,1.0,3.0},{4.0,2.0,2.0}});
    
    public void testVerificador1()
    {
        assertEquals("SLCD",m1.verificador());
    }
    public void testVerificador2()
    {
        assertEquals("SLCD",m2.verificador());
    }
    public void testVerificador3()
    {
        assertEquals("SLCD",m3.verificador());
    }
    
    public void testVerificador4()
    {
        assertEquals("SLCD",m4.verificador());
    }
    
    public void testVerificador5()
    {
        assertEquals("SLCI",m5.verificador());
    }
    
    public void testVerificador6()
    {
        assertEquals("SLI",m6.verificador());
    }
}
