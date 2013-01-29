import junit.framework.TestCase;
/*
 *@version 1.0
 * @author pargles
 * @see http://junit.sourceforge.net/javadoc/org/junit/Assert.html
 * @see http://netbeans.org/kb/docs/java/junit-intro_pt_BR.html#Exercise_21
 */
public class TesteCompleto extends TestCase {
    
    Gauss gauss = new Gauss();
    Matriz m1 = new Matriz(3,4,new double[][] {{3.0,2.0,4.0,1.0},{1.0,1.0,2.0,2.0},{4.0,3.0,-2.0,3.0}});//matriz do trabalho dos caminhoes
    Matriz m2 = new Matriz(2,3,new double[][] {{2.0,1.0,3.0},{1.0,-3.0,-2.0}});//matriz com solucao unica
    Matriz m3 = new Matriz(4,5,new double[][] {{2.0,2.0,1.0,1.0,7.0},{1.0,-1.0,2.0,-1.0,1.0},{3.0,2.0,-3.0,-2.0,4.0},{4.0,3.0,2.0,1.0,12.0}});//matriz exercicio 1
    Matriz m4 = new Matriz(3,4,new double[][] {{2.0,4.0,-1.0,2.0},{3.0,6.0,1.0,3.0},{1.0,1.0,2.0,4.0}});//matriz exercicio 6
    Matriz m5 = new Matriz(2,3,new double[][] {{2.0,1.0,3.0},{4.0,-2.0,6.0}});//matriz com infinitas solucoes
    Matriz m6 = new Matriz(2,3,new double[][] {{2.0,1.0,3.0},{4.0,2.0,2.0}});//matriz que nao tem solucao
    double[] resposta1 = {-3.0,5.0,0.0};
    double[] resposta2 = {1.0,1.0};
    double[] resposta3 = {1.0,2.0,1.0,0.0};
    double[] resposta4 = {7.0,-3.0,0.0};
   
 /**
* Todo método que começar com a palavra "test" será executado pelo JUnit.
*/
    public void testGauss1() {
        assertArrayEquals(resposta1,gauss.executar(m1));
    }
    
    public void testGauss2() {
        assertArrayEquals(resposta2,gauss.executar(m2));
    }
    
    public void testGauss3() {
        assertArrayEquals(resposta3,gauss.executar(m3));
    }
    
    public void testGauss4() {
        assertArrayEquals(resposta4,gauss.executar(m4));
    }
        
    
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
    
    /* assert que compara vetores de float ou double somente estara 
     * disponivel na proxima versao 
     * do JUnit, por isso foi necessario implementa-lo
     */
    private void assertArrayEquals(double[] d, double[] d0) {
        for(int i=0;i<d.length;i++)
        {
            assertTrue(d[i]-d0[i]==0);
        }
    }
}
