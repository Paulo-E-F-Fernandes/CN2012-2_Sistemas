import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 * @see http://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
 * @see http://docs.oracle.com/javase/1.4.2/docs/api/java/text/DecimalFormat.html
 * @see http://www.univasf.edu.br/~max.santana/arquivos/poo_2008.2/laboratorio06_formatandosaida.pdf
 * @author pargles
 * @version 5.0
 */
public final class Interface extends JFrame{
    private Matriz matrizPrincipal;
    private double erro,vetorAuxiliar[];
    private double[][] matrizArquivo;
    private ResolucaoSistemas solucao;
    private JTextField[] entradas;//contem os botoes da matriz de entrada
    private JLabel[] resultados;//vetor de labels que contem os resultados
    private JButton iniciar,gerar,abrirArquivo;
    private JPanel painelMatriz,painelConfiguracoes,painelResultados;
    private JFrame frameMatriz,frameResultados;
    private JTextField textVetorInicial,textErro;
    private JSpinner spinnerLinhas,spinnerColunas;
    private JRadioButton gauss, lu,cholesky,jacobi,seidel;
    private Integer linhas,colunas;
    private double[] resultado;
    private JFileChooser chooserFile;
    
  //metodo construtor
  public Interface()
  {

      matrizPrincipal = new Matriz();
      setTitle("Sistemas");
      painelConfiguracoes = new JPanel();
      painelConfiguracoes.setLayout(new GridLayout(17,0));// 17 botoes verticalmente
      insereConfiguracoes();
      getContentPane().add(painelConfiguracoes, BorderLayout.WEST);

  }

  /* metodo que instancia os campos de texto matriz 
   * e insere eles no painel painelMatriz
   * @param void
   * @return void
   */
    public void insereBotoesNoLayout() {
        int j=0,k=0;
        for (int i = 0; i < linhas*colunas; i++) {
            entradas[i] = new JTextField();
            if(matrizArquivo==null) { //caso nao tem numeros para completar na matriz
                entradas[i].setText(""); //nao pode ter espaco, senao da problema no parseInt
            }
            else {//senao ele coloca os numeros do
               entradas[i].setText(Double.toString(matrizArquivo[j][k]));
               if(k>=colunas-1){k=0;j++;}//pula para proxima coluna
               else{k++;}//vai andando na linha 
            } 
           
            painelMatriz.add(entradas[i]);//adiciona o campo de texto no painel das jogadas
        }
         matrizArquivo = null;
    }

    /* metodo que limpa as caixas de texto da matriz
     * @param void
     * @return void
     */
    public void limparEntradasMatriz() {
        for (int i = 0; i < linhas*colunas; i++) {
            entradas[i].setText("");///nao pode ter espaco, senao da problema no parseInt
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
        gerar.setFocusable(false);
        gerar.addActionListener(new botaoGerar());
        
        abrirArquivo = new JButton("Abrir Arquivo");
        abrirArquivo.setFocusable(false);
        abrirArquivo.addActionListener(new botaoAbrirArquivo());

        spinnerColunas = new JSpinner();
        spinnerColunas.setValue(5);

        spinnerLinhas = new JSpinner();
        spinnerLinhas.setValue(5);//default

        textVetorInicial = new JTextField();
        textVetorInicial.setText("0.2, 1.7, 5, 3.6");
        //textVetorInicial.setVisible(false);

        textErro = new JTextField();
        textErro.setText("0.005");
        //textErro.setVisible(false);
     
        gauss = new JRadioButton("Gauss");
        gauss.setSelected(true);
        gauss.setMnemonic(KeyEvent.VK_B);
        gauss.setActionCommand("Gauss");

        lu = new JRadioButton("LU");
        //lu.setSelected(true);
        lu.setMnemonic(KeyEvent.VK_B);
        lu.setActionCommand("LU");

        cholesky = new JRadioButton("Cholesky");
        //cholesky.setSelected(true);
        cholesky.setMnemonic(KeyEvent.VK_B);
        cholesky.setActionCommand("Cholesky");
        
        jacobi = new JRadioButton("Jacobi");
        //jacobi.setSelected(true);
        jacobi.setMnemonic(KeyEvent.VK_B);
        jacobi.setActionCommand("Jacobi");

        seidel = new JRadioButton("Seidel");
        //seidel.setSelected(true);
        seidel.setMnemonic(KeyEvent.VK_B);
        seidel.setActionCommand("Seidel");
                
        painelConfiguracoes.add(new JLabel("Linhas: "));
        painelConfiguracoes.add(spinnerLinhas);
        painelConfiguracoes.add(new JLabel("Colunas:"));
        painelConfiguracoes.add(spinnerColunas);
        painelConfiguracoes.add(abrirArquivo);
        painelConfiguracoes.add(gerar);
        painelConfiguracoes.add(new JLabel("Metodo: "));
        painelConfiguracoes.add(gauss);
        painelConfiguracoes.add(lu);
        painelConfiguracoes.add(cholesky);
        painelConfiguracoes.add(jacobi);
        painelConfiguracoes.add(seidel);
        painelConfiguracoes.add(iniciar);
        painelConfiguracoes.add(new JLabel("Erro: "));
        painelConfiguracoes.add(textErro);
        painelConfiguracoes.add(new JLabel("Vetor: "));
        painelConfiguracoes.add(textVetorInicial);        
        //painelConfiguracoes.add(labelVazio);
    }

   /* classe para o evento que cuida do bota iniciar
   * @param void
   * @return void
   */
    public class botaoIniciar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double[][] m = new double[linhas][colunas];//matriz temporaria para pegar os valores dos campos de texto
            int k = 0;
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    m[i][j] = Double.parseDouble(entradas[k].getText());
                    k++;
                }
            }
            matrizPrincipal.setMatrizAmpliada(m);
            matrizPrincipal.setSolucao();
            erro = Double.parseDouble(textErro.getText());
            String stringIn = textVetorInicial.getText();
            StringTokenizer tokens =new StringTokenizer(stringIn,",");//separa os tokens por virgulas
            int y = 0;
            vetorAuxiliar = new double[linhas];
            while(tokens.hasMoreElements())
            {
                vetorAuxiliar[y++]= Double.parseDouble(tokens.nextToken());
            }

            if (matrizPrincipal.getSolucao().compareTo("SLCI") == 0) {
                mensagemInfinitasSolucoes();
            } else if (matrizPrincipal.getSolucao().compareTo("SLI") == 0) {
                mensagemSemSolucao();
            } else {
                solucao = new ResolucaoSistemas(matrizPrincipal);
                criarTelaResultados();
                
                Loading loading = new Loading();
                Thread loadingThread = new Thread(loading);
                loadingThread.start();
                
                if (gauss.isSelected()) {
                	resultado = solucao.executar("Gauss");
                    adicionarResultados(resultado,0);
                }
                if (lu.isSelected()) {
                    resultado = solucao.executar("LU");
                    adicionarResultados(resultado,1);
                }
                if (cholesky.isSelected()) {
                    resultado = solucao.executar("Cholesky");
                    adicionarResultados(resultado,2);
                }
                if (jacobi.isSelected()) {
                    resultado = solucao.executar("Jacobi",vetorAuxiliar,erro);
                    adicionarResultados(resultado,3);
                }
                if (seidel.isSelected()) {
                    resultado = solucao.executar("Seidel",vetorAuxiliar,erro);
                    adicionarResultados(resultado,4);
                }
                loading.exit();
                frameResultados.getContentPane().add(painelResultados, BorderLayout.CENTER);
                frameResultados.pack(); //ajusta o tamanho da janela ao dos componentes
                frameResultados.setVisible(true);//torna visivel a interface

            }
            iniciar.setVisible(false);//ja mostrou o resultado, necessario configurar outra matriz para iniciar novamente
            
        }
        

        private void criarTelaResultados() {
            Dimension boardSize = new Dimension(400, 200);
            frameResultados = new JFrame("Resultados");
            //frameResultados.setResizable(false);//nao deixa o usuario aumentar o tamanho da tela
            frameResultados.setLocationRelativeTo(painelConfiguracoes);
            frameResultados.setLocationRelativeTo(null);
            painelResultados = new JPanel();
            painelResultados.setPreferredSize(boardSize);
            //painelResultados.setBackground(Color.black);
            painelResultados.setLayout(new GridLayout(linhas+1, 5));//5 e o numero de metodos
            limparResultados();
        }

        /* metodo que cria os campos vazios dos resultados
         * @param void
         * @return void
         */
        private void limparResultados() {
            resultados = new JLabel[(linhas+1) * 5];//cria um vetor de campos de texto do tamanho da matriz
            resultados[0] = new JLabel();
            resultados[0].setText("  Gauss ");
            painelResultados.add(resultados[0]);//adiciona o campo de texto no painel das jogadas
            resultados[1] = new JLabel();
            resultados[1].setText("Cholesky");
            painelResultados.add(resultados[1]);//adiciona o campo de texto no painel das jogadas
            resultados[2] = new JLabel();
            resultados[2].setText("    LU  ");
            painelResultados.add(resultados[2]);//adiciona o campo de texto no painel das jogadas
            resultados[3] = new JLabel();
            resultados[3].setText(" Jacobi ");
            painelResultados.add(resultados[3]);//adiciona o campo de texto no painel das jogadas
            resultados[4] = new JLabel();
            resultados[4].setText(" Seidel ");
            painelResultados.add(resultados[4]);//adiciona o campo de texto no painel das jogadas
            for (int i = 5; i < (linhas+1) * 5; i++) {//6 e a quantidade de metodos e linhas e a quantidade d resultados X's
                resultados[i] = new JLabel();
                resultados[i].setText("00000000");
                painelResultados.add(resultados[i]);//adiciona o campo de texto no painel das jogadas
            }
        }
        
        private void adicionarResultados(double vetor[], int numeroMetodo)
        {
            NumberFormat format = NumberFormat.getInstance();//instancia o formatador de numeros
            format.setMaximumFractionDigits(4);
            format.setMaximumIntegerDigits(6);
            String formatado;//string que serve para colocar o numero formtado
            int j =0;
            Double aux;
            for (int i = numeroMetodo+5; i < (linhas+1)*5; i+=5) {
                aux = vetor[j];j++;
                formatado = format.format(aux);
                resultados[i].setText(formatado);
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
            linhas =  (Integer) spinnerLinhas.getValue();
            colunas =  (Integer) spinnerColunas.getValue();
            matrizPrincipal = new Matriz(linhas,colunas);
            frameMatriz = new JFrame("matriz");
            //frameMatriz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frameMatriz.setResizable(false);//nao deixa o usuario aumentar o tamanho da tela
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
    
    
   /* classe para o evento que cuida do botao abrirArquivo
   * @param void
   * @return void
   */
    public class botaoAbrirArquivo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            chooserFile = new JFileChooser();
            final ExtensionFileFilter filter = new ExtensionFileFilter();
            filter.adicionarExtensao(".csv");
            filter.adicionarExtensao(".txt");
            chooserFile.setFileFilter(filter);
            int retorno = chooserFile.showOpenDialog(null);
            if (retorno == JFileChooser.APPROVE_OPTION)//se a pessoa clicar em cancelar nao vai fazer nada pois nao tem um else definido
            {
                try {
                    File arquivo = chooserFile.getSelectedFile();
                    lerArquivo(arquivo);
                } catch (FileNotFoundException ex) {
                    System.err.println("Não foi possivel abrir o arquivo");
                } catch (IOException ex) {
                    System.err.println("A primeira linha deve conter o nº de colunas e de linhas da matriz, seguido pela própria matriz");
                }
            }
        }
    }
    
    public void lerArquivo(File arquivo) throws FileNotFoundException, IOException {
        int y=0,z=0;
        BufferedReader bufferEntrada;
        String stringIn;
        StringTokenizer tokens;
        bufferEntrada = new BufferedReader(new FileReader(arquivo));
        stringIn = bufferEntrada.readLine();
        tokens = new StringTokenizer(stringIn,",");//separa os tokens por virgulas
        linhas = Integer.parseInt(tokens.nextToken());
        colunas = Integer.parseInt(tokens.nextToken());
        spinnerLinhas.setValue(linhas);
        spinnerColunas.setValue(colunas);
        matrizArquivo = new double[linhas][colunas];
        //agora comeca a ler a matriz
        while ((stringIn = bufferEntrada.readLine()) != null) {
            tokens = new StringTokenizer(stringIn,",");
            while(tokens.hasMoreElements())
            {
                matrizArquivo[y][z++]=Double.parseDouble(tokens.nextToken());
            }
            y++;z=0;//proxima linha
        }
        matrizPrincipal.setDimensao(linhas, colunas);
        matrizPrincipal.setMatrizAmpliada(matrizArquivo);//depois de todos os valores armazenados ma matriz temporaria m, setar a matriz no objeto Matriz
        gerar.doClick();//depois de colocado o valor de cada posicao da matriz ele continua como se fosse um clique no botao gerarmatriz
    }
    
     /* metodo que abre uma mensagem indicando que nao ha solucao
     * @param void
     * @return void
     */
    public void mensagemSemSolucao() {
        JOptionPane.showMessageDialog(null, " Sistema nao possui SOLUCAO (SLI)!");
    }

    /* metodo que abre uma mensagem indicando que existem infinitas solucoes
     * @param void
     * @return void
     */
    public void mensagemInfinitasSolucoes() {
        JOptionPane.showMessageDialog(null, " Sistema possui INFINITAS solucoes (SLCI)!");
    }

     
 /* classe que extende a classe FileFilter
  * para definir as extensoes de arquivos que
  * poderao ser abertas pelo programa
  */
    public class ExtensionFileFilter extends FileFilter {
        private ArrayList<String> extensions = new ArrayList<String>();  // array que vai conter as extensoes possiveis
        private void adicionarExtensao(String tipo) {
            if (!tipo.startsWith(".")) {
                tipo = "." + tipo;
            }

            extensions.add(tipo);
        }
        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;//aceita diretorios
            }
            String name = f.getName().toLowerCase();//pega o nome do arquivo selecionado e passa para minusculo  
            for (String extension : extensions) {//for each no array de extensoes
                if (name.endsWith(extension)) {
                    return true;
                }
            }
            return false;
        }
        @Override
        public String getDescription() {
            return "Arquivos contendo dados";
        }
    }
}