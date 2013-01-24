import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * http://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
 * @author pargles
 * @version 1.0
 */


public final class Interface extends JFrame{
    private final Matriz matrizPrincipal;
    private ResolucaoSistemas solucao;
    private enum metodo{Todos, Gauss,Cholesky,LU,Jacobi,Seide;}
    private String tipoMetodo ="Todos";//default
    private JComboBox listaMetodos;//para colocar os metodos
    private JTextField[] entradas;//contem os botoes da matriz 3x3 do jogo
    private JButton iniciar,gerar;
    private JLabel labelColunas,labelLinhas,labelErro,labelVetorInicial,labelMetodo;
    private JPanel painelMatriz,painelConfiguracoes;
    private JFrame frameMatriz;
    private JTextField textLinhas,textColunas,textVetorInicial,textErro;
    private int linhas,colunas;
    //Velha jogoDaVelha;
    
  //metodo construtor
  public Interface()
  {

      matrizPrincipal = new Matriz();
      setTitle("Sistemas");
      painelConfiguracoes = new JPanel();
      painelConfiguracoes.setLayout(new GridLayout(12,0));// 12 botoes verticalmente
      insereConfiguracoes();
      getContentPane().add(painelConfiguracoes, BorderLayout.WEST);

  }

  /* metodo que instancia os campos de texto matriz 
   * e insere eles no painel painelMatriz
   * @param void
   * @return void
   */
    public void insereBotoesNoLayout() {
        for (int i = 0; i < linhas*colunas; i++) {
            entradas[i] = new JTextField();
            entradas[i].setText("    ");
            painelMatriz.add(entradas[i]);//adiciona o campo de texto no painel das jogadas
        }
    }

    /* metodo que limpa as caixas de texto da matriz
     * @param void
     * @return void
     */
    public void limparEntradasMatriz() {
        for (int i = 0; i < linhas*colunas; i++) {
            entradas[i].setText("    ");
         }
    }

    /* metodo que insere todos os botoes no Painel de configuracoes
     * @param void
     * @return void
     */
    public void insereConfiguracoes() {
        iniciar = new JButton("Iniciar");
        iniciar.setFocusable(false);
        iniciar.addActionListener(new botaoIniciar());
        iniciar.setVisible(false);

        gerar = new JButton("Gerar Matriz");
        gerar.addActionListener(new botaoGerar());

        labelColunas = new JLabel("Colunas:");
        labelLinhas = new JLabel("Linhas: ");
        labelErro = new JLabel("Erro: ");
        labelVetorInicial=new JLabel("Vetor: ");
        labelMetodo = new JLabel("Metodo: ");

        textColunas = new JTextField();
        textColunas.setText("5");//default

        textLinhas = new JTextField();
        textLinhas.setText("5");//default

        textVetorInicial = new JTextField();
        textVetorInicial.setText("0.2 1.7 5 3.6");
        //textVetorInicial.setVisible(false);

        textErro = new JTextField();
        textErro.setText("0.005");
        //textErro.setVisible(false);

        listaMetodos = new JComboBox();
        listaMetodos.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Todos", "Gauss","Cholesky","LU","Jacobi","Seide"}));
        //listaAlgoritmos.addActionListener(new selecionaMetodo());

        painelConfiguracoes.add(labelLinhas);
        painelConfiguracoes.add(textLinhas);
        painelConfiguracoes.add(labelColunas);
        painelConfiguracoes.add(textColunas);
        painelConfiguracoes.add(gerar);
        painelConfiguracoes.add(labelMetodo);
        painelConfiguracoes.add(listaMetodos);
        painelConfiguracoes.add(iniciar);
        painelConfiguracoes.add(labelErro);
        painelConfiguracoes.add(textErro);
        painelConfiguracoes.add(labelVetorInicial);
        painelConfiguracoes.add(textVetorInicial);        
        //painelConfiguracoes.add(labelVazio);
    }

   /* classe para o evento que cuida do bota iniciar
   * @param void
   * @return void
   */
    public class botaoIniciar implements ActionListener {

        private int[][] m;//matriz temporaria para pegar os valores dos campos de texto

        public void actionPerformed(ActionEvent e) {

            int k = 0;
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    m[i][j] = Integer.parseInt(entradas[k].getText());
                    k++;
                }
            }
            matrizPrincipal.setMatriz(m);
            if (matrizPrincipal.tipoSolucao().compareTo("infinitas") != 0) {
                mensagemInfinitasSolucoes();
            } else if (matrizPrincipal.tipoSolucao().compareTo("nenhuma") != 0) {
                mensagemSemSolucao();
            } else {
                solucao = new ResolucaoSistemas(matrizPrincipal);
                solucao.executar(tipoMetodo);
            }

        }
    }

   /* classe para o evento que cuida do botao gerar
   * @param void
   * @return void
   */
    public class botaoGerar implements ActionListener {
        

        public void actionPerformed(ActionEvent e) {
            
            Dimension boardSize = new Dimension(200, 200);
            linhas = Integer.parseInt(textLinhas.getText());
            colunas = Integer.parseInt(textColunas.getText());
            matrizPrincipal.setDimensao(linhas, colunas);
            frameMatriz = new JFrame("matriz");
            frameMatriz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameMatriz.setResizable(false);//nao deixa o usuario aumentar o tamanho da tela
            frameMatriz.setLocationRelativeTo(null);
            painelMatriz = new JPanel();
            painelMatriz.setPreferredSize(boardSize);
            painelMatriz.setBackground(Color.black);
            painelMatriz.setLayout(new GridLayout(linhas, colunas));
            entradas = new JTextField[linhas*colunas];//cria um vetor de campos de texto do tamanho da matriz
            insereBotoesNoLayout();
            frameMatriz.getContentPane().add(painelMatriz, BorderLayout.CENTER);
            frameMatriz.pack(); //ajusta o tamanho da janela ao dos componentes
            frameMatriz.setVisible(true);//torna visivel a interface
            iniciar.setVisible(true);
                        
        }
    }

    /* metodo que abre uma mensagem indicando que nao ha solucao
     * @param void
     * @return void
     */
    public void mensagemSemSolucao() {
        JOptionPane.showMessageDialog(null, " Sistema nao possui SOLUCAO !");
    }

    /* metodo que abre uma mensagem indicando que existem infinitas solucoes
     * @param void
     * @return void
     */
    public void mensagemInfinitasSolucoes() {
        JOptionPane.showMessageDialog(null, " Sistema possui INFINITAS solucoes !");
    }



}