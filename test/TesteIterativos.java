import junit.framework.TestCase;

public class TesteIterativos extends TestCase {
	GaussJacobi ja = new GaussJacobi();
	GaussSeidel se = new GaussSeidel();
	Matriz matriz1 = new Matriz(3,4,new double[][] {{10.0, 2.0, 1.0, 7.0},{1.0, 5.0, 1.0, -8.0},{2.0, 3.0, 10.0, 6.0}});
	Matriz matriz2 = new Matriz(3,4,new double[][] {{5.0, 1.0, 1.0, 5.0},{3.0, 4.0, 1.0, 6.0},{3.0, 3.0, 6.0, 0.0}});
	double[] x01 = {0.7, -1.6, 0.6};
	double[] x02 = {0.0, 0.0, 0.0};
	double[] resposta1 = {0.9994000000000001, -1.9888000000000001, 0.9984};
	double[] resposta2 = {1.0075, 0.99125, -0.999375};
	
	
	public void testGaussJacobi() {
        assertArrayEquals(resposta1, ja.executar(matriz1, x01, 0.05));
	}
	
	public void testGaussSeidel() {
        assertArrayEquals(resposta2, se.executar(matriz2, x02, 0.05));
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
