package gui;

import com.alee.laf.WebLookAndFeel;
import control.Assembler;
import control.ControladorIntermedio;
import control.ControladorIntermedioDobleOpt;
import control.ControladorIntermedioOptimizado;
import control.ExArchivo;
import control.ExAutomata;
import control.ExCompatibilidad;
import control.ExSemantico;
import control.ExSintaxis;
import control.ExTablaTransicion;
import control.InterAssambler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Element;
import model.LexemaVO;

/**
 * Hecho con <3 Por:
 * @author GarciHard
 */

public class DamaIDE extends JFrame implements ActionListener{

    private ArrayList<Integer> variableLexico = new ArrayList<>();
    private ArrayList<LexemaVO> lexemas;
    private BufferedReader leer;
    private ControladorIntermedio intermedioOptimizado;
    private ControladorIntermedioDobleOpt intermedioDobleOpt;
    private ControladorIntermedioOptimizado controladorIntermedio;
    private DefaultTableModel dtmTablaLexema;
    private File abrirArchivos;
    private FileReader fichero;
    private JButton btnAnalisisLexico;
    private JButton btnAnalisisSemantico;
    private JButton btnAnalisisSintactico;
    private JButton btnAssembler;
    private JButton btnCodigoIntermedio;
    private JButton btnLimpiarPantalla;
    private JFileChooser jfcManejoArchivos;
    private JMenu mnuArchivo;
    private JMenu mnuCambiarTemaEditor;
    private JMenu mnuHerramientas;
    private JMenuBar mnbPrincipal;
    private JMenuItem mniAbrir;
    private JMenuItem mniCodigoFuenteEjemplo;
    private JMenuItem mniGuardar;
    private JMenuItem mniNuevo;
    private JMenuItem mniSalir;
//    private JMenuItem mniTemaClaro;
    private JMenuItem mniTemaDefault;
    private JMenuItem mniTemaOscuro;
    private JPanel panAreaTexto;
    private JPanel panAreaTextoSintactico;
    private JPanel panBackground;
    private JPanel panBotonesEjeY;
    private JPanel panSeparadorNorte;
    private JPanel panTablaLexemas;
    private JScrollPane scpAnalisisSintactico;
    private JScrollPane scpCodigoIntermedio;
    private JScrollPane scpCodigoIntermedioOpt;
    private JScrollPane scpEditorCodigo;
    private JScrollPane scpLineasEditorCodigo;
    private JScrollPane scpTablaLexema;
    private JScrollPane scpTemporales;
    private JScrollPane scpTemporalesOpt;
    private JSplitPane splitPane;
    private JSplitPane splitPaneOpt;
    private JTabbedPane tbpPrincipal;
    private JTable tblLexema;
    private JTextArea txaAnalisisSintactico;
    private JTextArea txaCodigoIntermedio;
    private JTextArea txaCodigoIntermedioOpt;
    private JTextArea txaEditorCodigo;
    private JTextArea txaLineasEditorCodigo;
    private JTextArea txaTemporales;
    private JTextArea txaTemporalesOpt;
    private Object tableContent[][];
    private String columnName[];
    private String guardarTexto;
    public String intermedioAssembler;
    
    public DamaIDE() {
        initFrame();
    }
    
    private void initFrame() {
/******************** Creación del frame principal ********************/
        setTitle("Dama IDE v0.9");
        setSize(new Dimension(800,600));
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creacion del menuBar para el frame principal
        mnbPrincipal = new JMenuBar();
        
        mnuArchivo = new JMenu("Archivo");
        mniNuevo = new JMenuItem("Nuevo");
        mniNuevo.addActionListener(this);
        mniAbrir = new JMenuItem("Abrir");
        mniAbrir.addActionListener(this);
        mniGuardar = new JMenuItem("Guardar");
        mniGuardar.addActionListener(this);
        mniSalir = new JMenuItem("Salir");
        mniSalir.addActionListener(this);
        
        mnuArchivo.add(mniNuevo);
        mnuArchivo.add(mniAbrir);
        mnuArchivo.add(mniGuardar);
        mnuArchivo.add(mniSalir);
        
        mnuHerramientas = new JMenu("Herramientas");
        mniCodigoFuenteEjemplo = new JMenuItem("Código Fuente Ejemplo");
        mniCodigoFuenteEjemplo.addActionListener(this);
        mnuCambiarTemaEditor = new JMenu("Cambiar Tema Editor");
        mniTemaDefault = new JMenuItem("Default Dama");
        mniTemaDefault.addActionListener(this);
        mniTemaOscuro = new JMenuItem("Dark Dama");
        mniTemaOscuro.addActionListener(this);
//        mniTemaClaro = new JMenuItem("Light Dama");
//        mniTemaClaro.addActionListener(this);
        
        mnuCambiarTemaEditor.add(mniTemaDefault);
        mnuCambiarTemaEditor.add(mniTemaOscuro);
//        mnuCambiarTemaEditor.add(mniTemaClaro);
        
        mnuHerramientas.add(mniCodigoFuenteEjemplo);
        mnuHerramientas.add(mnuCambiarTemaEditor);
        
        mnbPrincipal.add(mnuArchivo);
        mnbPrincipal.add(mnuHerramientas);
        
        //Creación del panel de fondo
        panBackground = new JPanel();
        panBackground.setSize(getSize());
        panBackground.setLayout(new BorderLayout());
        panBackground.setBackground(new Color(255,255,255));
        
/******************** Creacion del panel separador norte, que estara en el norte ... ********************/
        panSeparadorNorte = new JPanel();
        panSeparadorNorte.setSize(800,50);
        
/******************** Creacion del panel de botones en eje Y, que estara en la parte izquierda ********************/
        panBotonesEjeY = new JPanel();
        panBotonesEjeY.setSize(300,300);
        panBotonesEjeY.setLayout(new BoxLayout(panBotonesEjeY, BoxLayout.Y_AXIS));
        panBotonesEjeY.setBorder(BorderFactory.createTitledBorder("Opciones"));
        
        //Creacion de componentes para el panel de Botones en eje y
        btnAnalisisLexico = new JButton("Análisis Léxico");
        btnAnalisisLexico.setMaximumSize(new Dimension(135, 35));
        btnAnalisisLexico.setMinimumSize(btnAnalisisLexico.getMaximumSize());
        btnAnalisisLexico.setToolTipText("Realiza un análisis léxico del código escrito");
        btnAnalisisLexico.addActionListener(this);
        
        btnAnalisisSintactico = new JButton("Análisis Sintáctico");
        btnAnalisisSintactico.setMaximumSize(new Dimension(135, 35));
        btnAnalisisSintactico.setMinimumSize(btnAnalisisSintactico.getMaximumSize());
        btnAnalisisSintactico.setToolTipText("Realiza un análisis sintáctico del código escrito");
        btnAnalisisSintactico.setEnabled(false);
        btnAnalisisSintactico.addActionListener(this);

        btnAnalisisSemantico = new JButton("Análisis Semántico");
        btnAnalisisSemantico.setMaximumSize(new Dimension(135, 35));
        btnAnalisisSemantico.setMinimumSize(btnAnalisisSemantico.getMaximumSize());
        btnAnalisisSemantico.setToolTipText("Realiza un análisis semántico del código escrito");
        btnAnalisisSemantico.setEnabled(false);
        btnAnalisisSemantico.addActionListener(this);
        
        btnCodigoIntermedio = new JButton("Código Intermedio");
        btnCodigoIntermedio.setMaximumSize(new Dimension(135, 35));
        btnCodigoIntermedio.setMinimumSize(btnCodigoIntermedio.getMaximumSize());
        btnCodigoIntermedio.setToolTipText("Realiza el código intermedio del programa");
        btnCodigoIntermedio.setEnabled(false);
        btnCodigoIntermedio.addActionListener(this);
        
        btnAssembler = new JButton("Assembler");
        btnAssembler.setMaximumSize(new Dimension(135, 35));
        btnAssembler.setMinimumSize(btnAssembler.getMaximumSize());
        btnAssembler.setToolTipText("Realiza el código ensamblador del programa");
        btnAssembler.setEnabled(false);
        btnAssembler.addActionListener(this);
        
        btnLimpiarPantalla = new JButton("Limpiar  Editor");
        btnLimpiarPantalla.setMaximumSize(new Dimension(135, 35));
        btnLimpiarPantalla.setMinimumSize(btnLimpiarPantalla.getMaximumSize());
        btnLimpiarPantalla.setToolTipText("Borra todo el texto en el editor de código");
        btnLimpiarPantalla.addActionListener(this);
                
        //Agregar componentes al panel de botones en eje Y
        panBotonesEjeY.add(Box.createVerticalStrut(5));
        panBotonesEjeY.add(btnAnalisisLexico);
        panBotonesEjeY.add(Box.createVerticalStrut(5));
        panBotonesEjeY.add(btnAnalisisSintactico);
        panBotonesEjeY.add(Box.createVerticalStrut(5));
        panBotonesEjeY.add(btnAnalisisSemantico);
        panBotonesEjeY.add(Box.createVerticalStrut(5));
        panBotonesEjeY.add(btnCodigoIntermedio);
        panBotonesEjeY.add(Box.createVerticalStrut(5));
        panBotonesEjeY.add(btnAssembler);
        panBotonesEjeY.add(Box.createVerticalStrut(5));
        panBotonesEjeY.add(btnLimpiarPantalla);
        
/******************** Creacion del panel area de texto, estara en la parte derecha(termino en el centro) ********************/
        panAreaTexto = new JPanel();
        panAreaTexto.setSize(500,300);
        panAreaTexto.setLayout(new BorderLayout());
        panAreaTexto.setBorder(BorderFactory.createTitledBorder("Editor de código"));

        //Creacion del area de texto correspondiente a las lineas del editor de codigo
        txaLineasEditorCodigo = new JTextArea("1");
        txaLineasEditorCodigo.setBackground(new Color(220, 220, 222));
        txaLineasEditorCodigo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txaLineasEditorCodigo.setForeground(new Color(0, 0, 0));
        txaLineasEditorCodigo.setEditable(false);

        //Creacion de componentes para el panel area de texto
        txaEditorCodigo = new JTextArea();
        txaEditorCodigo.setBackground(new Color(220, 220, 222));
        txaEditorCodigo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txaEditorCodigo.setForeground(new Color(0, 0, 0));
        txaEditorCodigo.setSelectedTextColor(Color.white);
        txaEditorCodigo.setSelectionColor(new Color(120, 120, 122));
        txaEditorCodigo.setTabSize(4);
        txaEditorCodigo.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (txaEditorCodigo.getText().isEmpty()
                        && btnAnalisisLexico.isEnabled()
                        && tblLexema.getRowCount() > 0
                        && !txaAnalisisSintactico.getText().isEmpty()) {
                    txaEditorCodigo.setText("");
                    txaAnalisisSintactico.setText("");
                    txaTemporales.setText("");
                    txaCodigoIntermedio.setText("");
                    txaTemporalesOpt.setText("");
                    txaCodigoIntermedioOpt.setText("");
                    tbpPrincipal.setSelectedIndex(0);
                    tblLexema.setModel(new DefaultTableModel(
                            new Object[][]{},
                            new String[]{"Lexama", "Nombre", "Token", "Linea"}
                    ));
                } else if (txaEditorCodigo.getText().isEmpty()
                        && btnAnalisisSintactico.isEnabled()) {
                    txaEditorCodigo.setText("");
                    txaEditorCodigo.setText("");
                    txaTemporales.setText("");
                    txaCodigoIntermedio.setText("");
                    txaTemporalesOpt.setText("");
                    txaCodigoIntermedioOpt.setText("");
                    tbpPrincipal.setSelectedIndex(0);
                    tblLexema.setModel(new DefaultTableModel(
                            new Object[][]{},
                            new String[]{"Lexama", "Nombre", "Token", "Linea"}
                    ));
                    btnAnalisisSintactico.setEnabled(false);
                    btnAnalisisLexico.setEnabled(true);
                } else if (txaEditorCodigo.getText().isEmpty()
                        && btnAnalisisSemantico.isEnabled()) {
                    txaEditorCodigo.setText("");
                    txaAnalisisSintactico.setText("");
                    tblLexema.setModel(new DefaultTableModel(
                            new Object[][]{},
                            new String[]{"Lexama", "Nombre", "Token", "Linea"}
                    ));
                    btnAnalisisSemantico.setEnabled(false);
                    btnAnalisisSintactico.setEnabled(false);
                    btnAnalisisLexico.setEnabled(true);
                } else if (txaEditorCodigo.getText().isEmpty()
                        && btnAnalisisLexico.isEnabled()
                        && tblLexema.getRowCount() > 0
                        && txaAnalisisSintactico.getText().isEmpty()) {
                    txaEditorCodigo.setText("");
                    txaAnalisisSintactico.setText("");
                    txaTemporales.setText("");
                    txaCodigoIntermedio.setText("");
                    txaTemporalesOpt.setText("");
                    txaCodigoIntermedioOpt.setText("");
                    tbpPrincipal.setSelectedIndex(0);
                    tblLexema.setModel(new DefaultTableModel(
                            new Object[][]{},
                            new String[]{"Lexama", "Nombre", "Token", "Linea"}
                    ));
                }
            }
        });
        
        //Listener para las lineas del editor de codigo
        txaEditorCodigo.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = txaEditorCodigo.getDocument().getLength();
                Element root = txaEditorCodigo.getDocument().getDefaultRootElement();
                StringBuilder text = new StringBuilder();
                text.append("1").append(System.lineSeparator());
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text.append(i).append(System.lineSeparator());
                }
                return text.toString();
            }
            @Override
            public void changedUpdate(DocumentEvent de) {txaLineasEditorCodigo.setText(getText());}
            @Override
            public void insertUpdate(DocumentEvent de) {txaLineasEditorCodigo.setText(getText());}
            @Override
            public void removeUpdate(DocumentEvent de) {txaLineasEditorCodigo.setText(getText());}
        });
        
        scpLineasEditorCodigo = new JScrollPane(txaLineasEditorCodigo);
        scpLineasEditorCodigo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scpLineasEditorCodigo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scpLineasEditorCodigo.setBorder(BorderFactory.createEmptyBorder());
        
        scpEditorCodigo = new JScrollPane();
        scpEditorCodigo.getViewport().add(txaEditorCodigo);
        scpEditorCodigo.setRowHeaderView(scpLineasEditorCodigo);
        scpEditorCodigo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //Agregar componentes para el panel area de texto
        panAreaTexto.add(scpEditorCodigo, BorderLayout.CENTER);
        
/******************** Creacion del panel area de texto donde se desplegaran los resultados del análisis sintáctico ********************/
        //SE CONVIRTIO EN LA PARTE DONDE SE DESPLEGARAN ERRORES TANTO SINTÁCTICOS COMO SEMÁNTICOS
        panAreaTextoSintactico = new JPanel();
        panAreaTextoSintactico.setPreferredSize(new Dimension(800, 150));
        panAreaTextoSintactico.setLayout(new BorderLayout());

        tbpPrincipal = new JTabbedPane();

        txaAnalisisSintactico = new JTextArea();
        txaAnalisisSintactico.setEditable(false);
        txaAnalisisSintactico.setBackground(new Color(220, 220, 222));
        txaAnalisisSintactico.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txaAnalisisSintactico.setForeground(new Color(0, 0, 0));

        scpAnalisisSintactico = new JScrollPane(txaAnalisisSintactico, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setPreferredSize(new Dimension(800, 150));
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(.5);

        txaCodigoIntermedio = new JTextArea();
        txaCodigoIntermedio.setEditable(false);
        txaCodigoIntermedio.setBackground(new Color(220, 220, 222));
        txaCodigoIntermedio.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txaCodigoIntermedio.setForeground(new Color(0, 0, 0));

        scpCodigoIntermedio = new JScrollPane(txaCodigoIntermedio, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        txaTemporales = new JTextArea();
        txaTemporales.setEditable(false);
        txaTemporales.setBackground(new Color(220, 220, 222));
        txaTemporales.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txaTemporales.setForeground(new Color(0, 0, 0));

        scpTemporales = new JScrollPane(txaTemporales, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        splitPaneOpt = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneOpt.setPreferredSize(new Dimension(800, 150));
        splitPaneOpt.setDividerLocation(400);
        splitPaneOpt.setResizeWeight(.5);

        txaCodigoIntermedioOpt = new JTextArea();
        txaCodigoIntermedioOpt.setEditable(false);
        txaCodigoIntermedioOpt.setBackground(new Color(220, 220, 222));
        txaCodigoIntermedioOpt.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txaCodigoIntermedioOpt.setForeground(new Color(0, 0, 0));

        scpCodigoIntermedioOpt = new JScrollPane(txaCodigoIntermedioOpt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        txaTemporalesOpt = new JTextArea();
        txaTemporalesOpt.setEditable(false);
        txaTemporalesOpt.setBackground(new Color(220, 220, 222));
        txaTemporalesOpt.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txaTemporalesOpt.setForeground(new Color(0, 0, 0));

        scpTemporalesOpt = new JScrollPane(txaTemporalesOpt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        splitPane.setLeftComponent(scpCodigoIntermedio);
        splitPane.setRightComponent(scpTemporales);

        splitPaneOpt.setLeftComponent(scpCodigoIntermedioOpt);
        splitPaneOpt.setRightComponent(scpTemporalesOpt);        

        tbpPrincipal.addTab("Errores", scpAnalisisSintactico);
        tbpPrincipal.add("Código Intermedio", splitPane);
        tbpPrincipal.add("Código Intermedio Optimizado", splitPaneOpt);
        panAreaTextoSintactico.add(tbpPrincipal);

/******************** Creacion del panel que tendra la tabla de lexema, estara en la parte de abajo (sur) ********************/
        panTablaLexemas = new JPanel();
        panTablaLexemas.setLayout(new BorderLayout());
        panTablaLexemas.setPreferredSize(new Dimension(800,150));
        //panTablaLexemas.setSize(new Dimension(800, 150));
        panTablaLexemas.setBorder(BorderFactory.createTitledBorder("Salida"));
        
        columnName = new String[]{"Lexema", "Nombre", "Token","Linea"};
        tableContent = new Object[][]{};
        dtmTablaLexema = new DefaultTableModel(tableContent, columnName);
        tblLexema = new JTable(dtmTablaLexema);
        tblLexema.getTableHeader().setReorderingAllowed(false);//Para que no se muevan los encabezados.
        
        scpTablaLexema = new JScrollPane(tblLexema);
        scpTablaLexema.setPreferredSize(panTablaLexemas.getSize());
        scpTablaLexema.setBackground(new Color(220,220,222));
        
        panTablaLexemas.add(scpTablaLexema);
        
/******************** Agregar Componentes a los paneles y al frame principal ********************/
        
        //Agregar componentes al panel de fondo
        panBackground.add(panSeparadorNorte, BorderLayout.NORTH);
        panBackground.add(panBotonesEjeY, BorderLayout.WEST);
        panBackground.add(panAreaTexto, BorderLayout.CENTER);

        JPanel panDoble = new JPanel();
        panDoble.setLayout(new BorderLayout());
        panDoble.setSize(800, 300);
        panDoble.add(panTablaLexemas, BorderLayout.NORTH);
        panDoble.add(panAreaTextoSintactico, BorderLayout.SOUTH);
        panBackground.add(panDoble, BorderLayout.SOUTH);
        
        //Agregar componentes al frame principal
        setJMenuBar(mnbPrincipal);
        add(panBackground);
        WebLookAndFeel.install();
    }
/********************  ********************/    
    
 
    
/******************** Creacion de Listener a lo Medina(o sea a lo buey) ********************/
    @Override
    public void actionPerformed(ActionEvent evt) {
        //Acciones de los Menus
        if (evt.getSource() == mniNuevo) {//MenuItem Nuevo Archivo
            if (!txaEditorCodigo.getText().isEmpty()) {
                txaEditorCodigo.setText("");
                tblLexema.setModel(dtmTablaLexema);
            } else {
                JOptionPane.showMessageDialog(this, "Editor de Código Vacio\n Ya es archivo nuevo");
            }
        }
        if (evt.getSource() == mniAbrir) {//MenuItem Abrir Archivo existente
            try {
                jfcManejoArchivos = new JFileChooser();
                jfcManejoArchivos.showOpenDialog(this);
                abrirArchivos = jfcManejoArchivos.getSelectedFile();
                if (abrirArchivos != null) {
                    txaEditorCodigo.setText("");
                    fichero = new FileReader(abrirArchivos);
                    leer = new BufferedReader(fichero);
                    while ((guardarTexto = leer.readLine()) != null) {
                        txaEditorCodigo.append(guardarTexto + "\n");
                    }
                    leer.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        if (evt.getSource() == mniGuardar) {//MenuItem Guardar Archivo escrito
            String texto = txaEditorCodigo.getText();
            try {
                jfcManejoArchivos= new JFileChooser();
                jfcManejoArchivos.showSaveDialog(this);
                File guardar = jfcManejoArchivos.getSelectedFile();
                if (guardar != null) {
                    texto = jfcManejoArchivos.getSelectedFile().getName();
                    FileWriter escribir = new FileWriter(guardar + ".txt", true);
                    escribir.write(txaEditorCodigo.getText());
                    escribir.close();
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        if (evt.getSource() == mniSalir) {//MenuItem Salir, cerrar la aplicación
            System.exit(0);
        }
        //Código Fuente Ejemplo
        if (evt.getSource() == mniCodigoFuenteEjemplo) {
            try {
                jfcManejoArchivos = new JFileChooser("src/samples");
                jfcManejoArchivos.showOpenDialog(this);
                abrirArchivos = jfcManejoArchivos.getSelectedFile();
                if (abrirArchivos != null) {
                    txaEditorCodigo.setText("");
                    tblLexema.setModel(dtmTablaLexema);
                    fichero = new FileReader(abrirArchivos);
                    leer = new BufferedReader(fichero);
                    while ((guardarTexto = leer.readLine()) != null) {
                        txaEditorCodigo.append(guardarTexto + "\n");
                    }
                    leer.close();
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        //Temas de Dama IDE
        if (evt.getSource() == mniTemaDefault) {
            txaEditorCodigo.setBackground(new Color(220, 220, 222));
            txaEditorCodigo.setCaretColor(new Color(0, 0, 0));
            txaEditorCodigo.setForeground(new Color(0, 0, 0));
            txaEditorCodigo.setSelectedTextColor(Color.white);
            txaEditorCodigo.setSelectionColor(new Color(120, 120, 122));
            txaAnalisisSintactico.setBackground(new Color(220, 220, 222));
            txaAnalisisSintactico.setForeground(new Color(0, 0, 0));
            tblLexema.setBackground(new Color(220, 220, 222));
            tblLexema.setForeground(new Color(0, 0, 0));
            txaLineasEditorCodigo.setBackground(txaEditorCodigo.getBackground());
            txaLineasEditorCodigo.setForeground(txaEditorCodigo.getForeground());
        }
        if (evt.getSource() == mniTemaOscuro) {
            txaEditorCodigo.setBackground(new Color(18, 30, 49));
            txaEditorCodigo.setCaretColor(Color.YELLOW);
            txaEditorCodigo.setForeground(new Color(226, 206, 0));
            txaEditorCodigo.setSelectedTextColor(new Color(255, 255, 255));
            txaEditorCodigo.setSelectionColor(new Color(104, 93, 156));
            txaAnalisisSintactico.setBackground(new Color(18, 30, 49));
            txaAnalisisSintactico.setForeground(new Color(247,187,0));
            tblLexema.setBackground(new Color(18, 30, 49));
            tblLexema.setForeground(txaAnalisisSintactico.getForeground());
            txaLineasEditorCodigo.setBackground(txaEditorCodigo.getBackground());
            txaLineasEditorCodigo.setForeground(new Color(255, 255, 255));
        }
//        if (evt.getSource() == mniTemaClaro) { LO QUITAMOS TEMPORALMENTE
//            txaEditorCodigo.setBackground(new Color(52, 152, 219));
//            txaEditorCodigo.setForeground(new Color(255,255,255));
//            txaAnalisisSintactico.setBackground(new Color(52, 152, 219));
//            txaEditorCodigo.setCaretColor(new Color(255,255,255));
//            txaAnalisisSintactico.setForeground(new Color(255,255,255));
//            tblLexema.setBackground(new Color(52, 152, 219));
//            tblLexema.setForeground(new Color(255,255,255));
//            txaLineasEditorCodigo.setBackground(txaEditorCodigo.getForeground());
//        }
        
        //Botón para el análisis léxico
        if (evt.getSource() == btnAnalisisLexico) {
            if (!txaEditorCodigo.getText().isEmpty()) {
                txaAnalisisSintactico.setText("");
                lexemas = new ArrayList();
                tblLexema.setModel(new DefaultTableModel(
                        new Object[][]{},
                        new String[]{"Lexama", "Nombre", "Token", "Linea"}
                ));
                Analizar();
                int contador = 0;
                for (int string : variableLexico) {
                    if (string == 1) {
                        contador++;
                    }
                }
                if (contador == 0) {
                    System.out.println("NO HAY ERROR EN LÉXICO");
                    btnAnalisisSintactico.setEnabled(true);
                    btnAnalisisLexico.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Existe un error Léxico, verifique la tabla para más info.");
                }
                variableLexico.clear();
            } else {
                JOptionPane.showMessageDialog(this, "Editor de Código Vacio");
            }
        }
        //Botón para el análisis sintáctico
        if (evt.getSource() == btnAnalisisSintactico) {
            if (!txaEditorCodigo.getText().isEmpty()) {
                if (!(tblLexema.getRowCount() <= 0)) {
                    ArrayList<String> ids = new ArrayList();
                    ArrayList<String> lineas = new ArrayList();
                    ids.add("$");
                    lineas.add(String.valueOf(txaEditorCodigo.getLineCount()));
                    for (int j = lexemas.size() - 1; j >= 0; j--) {
                        ids.add(lexemas.get(j).getToken());
                        lineas.add(lexemas.get(j).getLinea());
                    }
                    String errores = new ExSintaxis(ids,lineas).MensajeSintaxis();
                    if (!errores.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Existe un error Sintáctico, verifique la consola de errores para más info.");
                        txaAnalisisSintactico.setText(errores);
                        btnAnalisisSintactico.setEnabled(false);
                        btnAnalisisLexico.setEnabled(true);
                    } else {
                        System.out.println("NO HAY ERROR EN SINTÁCTICO");
                        //JOptionPane.showMessageDialog(this, "Código sin errores :D");
                        btnAnalisisLexico.setEnabled(false);
                        btnAnalisisSintactico.setEnabled(false);
                        btnAnalisisSemantico.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Desbes realizar el análisis léxico primero");
                    btnAnalisisSintactico.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Área sintáctica vacia");
            }
        }
        //Botón para el analisis semántico
        if (evt.getSource() == btnAnalisisSemantico) {
            ArrayList<String> ids = new ArrayList();
            ArrayList<String> lineas = new ArrayList();
            ids.add("$");
            lineas.add(String.valueOf(txaEditorCodigo.getLineCount()));
            for (int j = lexemas.size() - 1; j >= 0; j--) {
                ids.add(lexemas.get(j).getToken());
                lineas.add(lexemas.get(j).getLinea());
            }
            String errores = new ExSintaxis(ids, lineas).MensajeSintaxis();
            System.out.println("------------------------------------------------1er errores: \n"+errores);
            ExSemantico sem = new ExSemantico(lexemas);
            errores += sem.MensajeSemantico();
            System.out.println("------------------------------------------------2do errores: \n"+errores);
            ExCompatibilidad com = new ExCompatibilidad();
            com.setLineasError(sem.getLineasdeError());
            errores += com.MensajeCompativilidad(lexemas);
            System.out.println("------------------------------------------------3er errores: \n"+errores);
            System.out.println("<><><><><>\n"+errores+"\n<><><><><>");
            if (errores.compareTo("") == 0) {
                btnAnalisisSemantico.setEnabled(false);
                btnAnalisisLexico.setEnabled(false);
                btnCodigoIntermedio.setEnabled(true);
            } else {
                btnAnalisisLexico.setEnabled(true);
                btnAnalisisSemantico.setEnabled(false);
                txaAnalisisSintactico.setText(errores);
            }

        }
        
        //Boton para el codigo intermedio

        if (evt.getSource() == btnCodigoIntermedio) {
            //CODIGO INTERMEDIO SIN OPTIMIZAR FULL_HD_4K_5K_#ESTE_SI_LO_TIENE_OBAMA
            controladorIntermedio = new ControladorIntermedioOptimizado((DefaultTableModel) tblLexema.getModel());
            txaCodigoIntermedio.setText(controladorIntermedio.leerSentencias(null));
            txaTemporales.setText(controladorIntermedio.codigoEtiquetas);
            //tbpPrincipal.setSelectedIndex(1);

            //CODIGO INTERMEDIO OPTIMIZADO 100%REAL_NO_FAKE_#NI_OBAMA_LO_TIENE
            intermedioOptimizado = new ControladorIntermedio((DefaultTableModel) tblLexema.getModel());
            txaCodigoIntermedioOpt.setText(intermedioOptimizado.leerSentencias(null));

            
            //CODIGO INTERMEDIO OPTIMIZADO VUELTO A OPTIMIZAR_#MERECE_UN_100
            intermedioDobleOpt = new ControladorIntermedioDobleOpt((DefaultTableModel) tblLexema.getModel());
            txaTemporalesOpt.setText("clase {\n\n"+intermedioDobleOpt.leerSentenciasOptimizadas(null)+"\n}");
            tbpPrincipal.setSelectedIndex(2);

            btnCodigoIntermedio.setEnabled(false);
            btnAssembler.setEnabled(true);
        }
        if (evt.getSource() == btnAssembler) {
            InterAssambler interAsm = new InterAssambler((DefaultTableModel) tblLexema.getModel());
            String inter = interAsm.leerSentencias(null);
//            System.out.println("ESTA ES LA SALIDA GENERADA POR INTERASSAMBLER");
//            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
//            System.out.println(inter);
//            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n"
                    + "TITLE automatas2\n.MODEL SMALL\n.STACK 64");
            System.out.println(".DATA");
            Assembler ens = new Assembler();
            ArrayList<String> tem;
            ArrayList<String> tem2;
            ArrayList<String> arr;
            ArrayList<ArrayList> arr1 = new ArrayList();
            arr1 = lexemaDeVerdad2(inter.replace("int", "").replace("doble","").replace("cad", ""));
            int co = 0;
            for (int i = 0; i < arr1.size(); i++) {
                tem2 = new ArrayList();
                tem2 = arr1.get(i);
                arr = new ArrayList();
                StringTokenizer tokens = new StringTokenizer(tem2.toString(), " ,][=()+/*-", true);
                while (tokens.hasMoreTokens()) {
                    String palabra = tokens.nextToken();
                    if (!" ".equals(palabra) && !",".equals(palabra) && !"[".equals(palabra) && !"]".equals(palabra)) {
                        arr.add(palabra);
                    }
                }
                if ("escribir".equals(arr.get(0))) {
                    co = co + 1;
                    ens.evar(tem2, co);
                } else {
                    ens.evar(tem2, co);
                }
            }
            System.out.println(".CODE");
            System.out.println("BEGIN proc main\n"
                    + "MOV ax,@data\n"
                    + "MOV ds,ax\n");
            co = 0;
            for (int i = 0; i < arr1.size(); i++) {
                tem = new ArrayList();
                tem = arr1.get(i);
                arr = new ArrayList();
                StringTokenizer tokens = new StringTokenizer(tem.toString(), " ,][=()+/*-", true);
                while (tokens.hasMoreTokens()) {
                    String palabra = tokens.nextToken();
                    if (!" ".equals(palabra) && !",".equals(palabra) && !"[".equals(palabra) && !"]".equals(palabra)) {
                        arr.add(palabra);
                    }
                }
                if ("escribir".equals(arr.get(0))) {
                    co = co + 1;
                    ens.ensamblador(tem, co);
                } else {
                    ens.ensamblador(tem, co);
                }
            }
            System.out.println("ENDP main\n"
                    + "end\n"
                    + "<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
            btnAssembler.setEnabled(false);
            btnAnalisisLexico.setEnabled(true);
        }

        //Botón para limpiar pantalla
        if (evt.getSource() == btnLimpiarPantalla) {
            if (!txaEditorCodigo.getText().isEmpty()) {
                txaEditorCodigo.setText("");
                txaAnalisisSintactico.setText("");
                txaTemporales.setText("");
                txaCodigoIntermedio.setText("");
                txaTemporalesOpt.setText("");
                txaCodigoIntermedioOpt.setText("");
                tblLexema.setModel(dtmTablaLexema);
                btnAnalisisSintactico.setEnabled(false);
                btnAnalisisSemantico.setEnabled(false);
                btnCodigoIntermedio.setEnabled(false);
                tbpPrincipal.setSelectedIndex(0);
            } else if (txaEditorCodigo.getText().isEmpty() && tblLexema.getRowCount() > 0) {
                txaTemporales.setText("");
                txaCodigoIntermedio.setText("");
                txaTemporalesOpt.setText("");
                txaCodigoIntermedioOpt.setText("");
                tblLexema.setModel(dtmTablaLexema);
                btnAnalisisSintactico.setEnabled(false);
                btnAnalisisSemantico.setEnabled(false);
                btnCodigoIntermedio.setEnabled(false);
                tbpPrincipal.setSelectedIndex(0);
            } else if (!txaAnalisisSintactico.getText().isEmpty()) {
                txaAnalisisSintactico.setText("");
                txaTemporales.setText("");
                txaCodigoIntermedio.setText("");
                txaTemporalesOpt.setText("");
                txaCodigoIntermedioOpt.setText("");
                btnAnalisisSintactico.setEnabled(false);
                btnAnalisisSemantico.setEnabled(false);
                btnCodigoIntermedio.setEnabled(false);
                tbpPrincipal.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Campos Vacios");
            }
        }
    }

/******************** Creacion de unos Métodos ********************/
    private void SepararCodigo(String codigo) {
        codigo = codigo + "\n";
        String[][] aux = ExArchivo.ExtraerCarctaeresDeArchivo();
        boolean arroba = false;
        boolean comilla = false;
        String lexema = "";
        int linea = 1;
        for (int i = 0; i < codigo.length(); i++) {
            for (int j = 0; j < aux.length; j++) {
                if (codigo.charAt(i) == '&') {//cadena
                    lexema = lexema + codigo.charAt(i);
                    comilla = !comilla;
                    break;
                } else if (comilla == true) {
                    lexema = lexema + codigo.charAt(i);
                    break;
                }
                if (codigo.charAt(i) == '&') {//cometario
                    lexema = lexema + codigo.charAt(i);
                    arroba = !arroba;
                    break;
                } else if (arroba == true) {
                    lexema = lexema + codigo.charAt(i);
                    break;
                }                                
                LexemaVO lex = new LexemaVO();
                LexemaVO lex2 = new LexemaVO();
                if (aux[j][0].length() == 1) {
                    if (aux[j][0].compareTo(codigo.charAt(i) + "") == 0 || codigo.charAt(i) == ' ' || codigo.charAt(i) == '\n' || codigo.charAt(i) == '\t') {
                        if (lexema.compareTo("") != 0) {
                            lex.setLexema(lexema);
                            lex.setLinea(linea +"");
                            lexema = "";
                            lexemas.add(lex);
                        }
                        if (codigo.charAt(i) == '\n') linea ++;
                        if (codigo.charAt(i) != ' ' & codigo.charAt(i) != '\n' & codigo.charAt(i) != '\t') {
                            lex2.setLexema(codigo.charAt(i) + "");
                            lex2.setToken(aux[j][1]);
                            lex2.setNombre(aux[j][2]);
                            lex2.setLinea(linea + "");
                            lexemas.add(lex2);
                        }
                        break;
                    } else if (j == aux.length - 1) {
                        lexema = lexema + codigo.charAt(i);
                    }
                } else if (i != codigo.length() - 1) {
                    if (aux[j][0].compareTo(codigo.charAt(i) + "" + codigo.charAt(i + 1)) == 0) {
                        if (lexema.compareTo("") != 0) {
                            lex.setLexema(lexema);
                            lex.setLinea(linea +"");
                            lexema = "";
                            lexemas.add(lex);
                        }
                        lex2.setLexema(aux[j][0]);
                        lex2.setToken(aux[j][1]);
                        lex2.setNombre(aux[j][2]);
                        lex2.setLinea(linea +"");
                        lexemas.add(lex2);
                        i++;
                        break;
                    }
                }
            }
        }
    }

    private void Analizar() {
        SepararCodigo(txaEditorCodigo.getText());
        String[][] aux = ExArchivo.ExtraerPalabraReservada();
        for (String[] a : aux) {
            for (LexemaVO l : lexemas) {
                if (a[0].compareTo(l.getLexema()) == 0) {
                    l.setToken(a[1]);
                    l.setNombre("Palabra reservada");
                }
            }
        }
        char[][] array = ExTablaTransicion.getTabla();
        for (LexemaVO l : lexemas) {
            if (l.getToken().compareTo("") == 0) {
                ExAutomata.EsValido(l.getLexema(), array, l);
            }
        }
        //finalmente imprimir resultados
        for (LexemaVO l : lexemas) {
            if (l.getNombre().compareTo("Variable no valida")==0 ||
                l.getNombre().compareTo("Comentario no valido")==0||
                    l.getNombre().compareTo("Numero no valido")==0||
                    l.getNombre().compareTo("Cadena no valida")==0||
                    l.getNombre().compareTo("Error no identificado")==0) {
                variableLexico.add(1);
            } else variableLexico.add(0);
            ((DefaultTableModel) tblLexema.getModel()).addRow(
                    new Object[]{l.getLexema(), l.getNombre(), l.getToken(), l.getLinea()}
            );
        }
    }
        
    public ArrayList<ArrayList> lexemaDeVerdad2(String g) {
        ArrayList<String> op3;
        ArrayList<String> op = new ArrayList();
        ArrayList<ArrayList> arr = new ArrayList();
        StringTokenizer tokens = new StringTokenizer(g, "\n", true);
        String texto = "";
        while (tokens.hasMoreTokens()) {
            String palabra = tokens.nextToken();
            texto += palabra + "\n";
        }
        StringTokenizer tokens2 = new StringTokenizer(texto);
        while (tokens2.hasMoreTokens()) {
            String palabra = tokens2.nextToken();
            op.add(palabra);
        }
        for (int i = 0; i < op.size(); i++) {
            int con = 0;
            if ("si".equals(op.get(i))) {
                if ("=".equals(op.get(i + 3)) && con == 0) {
                    op3 = new ArrayList();
                    op3.add(op.get(i));
                    op3.add(op.get(i + 1));
                    op3.add(op.get(i + 2));
                    op3.add(op.get(i + 3));
                    op3.add(op.get(i + 4));
                    op3.add(op.get(i + 5));
                    op3.add(op.get(i + 6));
                    arr.add(op3);
                    i = i + 6;
                    con++;
                }
                if (!"=".equals(op.get(i + 3)) && con == 0) {
                    op3 = new ArrayList();
                    op3.add(op.get(i));
                    op3.add(op.get(i + 1));
                    op3.add(op.get(i + 2));
                    op3.add(op.get(i + 3));
                    op3.add(op.get(i + 4));
                    op3.add(op.get(i + 5));
                    arr.add(op3);
                    i = i + 5;
                    con++;
                }
            }
            if ("goto".equals(op.get(i)) && con == 0) {
                op3 = new ArrayList();
                op3.add(op.get(i));
                op3.add(op.get(i + 1));
                arr.add(op3);
                i = i + 1;
                con++;
            }
            if (!"si".equals(op.get(i)) && !"goto".equals(op.get(i)) && con == 0) {
                op3 = new ArrayList();
                op3.add(op.get(i));
                arr.add(op3);
                con++;
            }
        }
        return arr;
    }
}//Fin de la aplicación

/*tabla de transicion sin mayusculas

#abcdefghijklmnopqrstuvwxyz0123456789~!@\¿$%^&*()_+¬·|¤€<>?:';{}[] =*-,."PN
1333333333333333333333333334444444444xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx7f1
2111111111111111111111111111111111111111111111111111111111111111111111111f2
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxv3
x333333333333333333333333333333333333xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxv4
xxxxxxxxxxxxxxxxxxxxxxxxxxx4444444444xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx5xv5
xxxxxxxxxxxxxxxxxxxxxxxxxxx6666666666xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxf6
xxxxxxxxxxxxxxxxxxxxxxxxxxx6666666666xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxv9
x777777777777777777777777777777777777777777777777777777777777777777777778f7
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxv8

v2

#abcdefghijklmnopqrstuvwxyz0123456789~!@\¿$%^&*()_+¬·|¤€<>?:';{}[] =*-,."PN
6222222222222222222222222223333333333xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx8f1
x22222222222222222222222222xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxv2
xxxxxxxxxxxxxxxxxxxxxxxxxxx3333333333xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx4xv3
xxxxxxxxxxxxxxxxxxxxxxxxxxx5555555555xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxf4
xxxxxxxxxxxxxxxxxxxxxxxxxxx5555555555xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxv5
7666666666666666666666666666666666666666666666666666666666666666666666666f6
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxv7
x888888888888888888888888888888888888888888888888888888888888888888888889f8
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxv9

*/