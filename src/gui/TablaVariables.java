package gui;

import control.ExVariable;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author GarciHard
 */
public class TablaVariables extends JDialog {

    private DefaultTableModel dtmModelTabla;
    private JPanel pnlBackground;
    private JScrollPane scpTablaVariables;
    private JTable tblTablaVariables;

    public TablaVariables() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Tabla Variables");
        setSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        pnlBackground = new JPanel(new BorderLayout());
        pnlBackground.setSize(new Dimension(getWidth(), getHeight()));
        pnlBackground.setBackground(new Color(255, 255, 255));

        String[] columna = {"ID Variable", "Tipo", "Linea", "¿Se usa?", "Valor"};
        dtmModelTabla = new DefaultTableModel(new Object[][]{}, columna);

        tblTablaVariables = new JTable(dtmModelTabla);
        tblTablaVariables.getTableHeader().setReorderingAllowed(false);

        scpTablaVariables = new JScrollPane(tblTablaVariables);
        scpTablaVariables.setPreferredSize(pnlBackground.getSize());
        scpTablaVariables.setBackground(new Color(220, 220, 222));

        pnlBackground.add(scpTablaVariables, BorderLayout.CENTER);

        add(pnlBackground);
    }

    public void insertarRegistro(ExVariable v) {
        ((DefaultTableModel) tblTablaVariables.getModel()).addRow(new Object[]{
            v.getNombreVariable(),
            v.getTipoVariable(),
            v.getLinea(),
            //v.getNivel(),
            v.esUsada() ? "si" : "no",
            v.getValor()
        });
    }
}
