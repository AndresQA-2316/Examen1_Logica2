import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


public class FrmDevueltas extends JFrame{
    JComboBox cmbDenominacion;
    JTextField txtCantidadExistencia, txtDevolver;
    JButton btnActualizar, btnDevolver;
    JTable tblDevuelta;
    String[] encabezado = new String[]{"Cantidad","Presentación","Denominacion"};
    int[] denominaciones = new int[11]; 
    String[] Denominacion = new String[]{"100.000","50.000","20.000","10.000","5.000","2.000","1.000","500","200","100","50"};
    int[] valores = {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50}; 
    int monto;
    

    public FrmDevueltas(){
        //interfaz
        setSize(500,380);
        setTitle("Calculadora de devueltas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblDenominacion = new JLabel("Denominacion");
        lblDenominacion.setBounds(150,25 , 100, 25);
        getContentPane().add(lblDenominacion);

        cmbDenominacion = new JComboBox();
        cmbDenominacion.setBounds(240,25,120,25);
        DefaultComboBoxModel mdlDenominacion = new DefaultComboBoxModel(Denominacion);
        cmbDenominacion.setModel(mdlDenominacion);
        getContentPane().add(cmbDenominacion);

        btnActualizar = new JButton("Actualizar existencia");
        btnActualizar.setBounds(35,55,170,30);
        getContentPane().add(btnActualizar);

        txtCantidadExistencia = new JTextField();
        txtCantidadExistencia.setBounds(210,65,40,20);
        getContentPane().add(txtCantidadExistencia);

        JLabel lblDevolver = new JLabel("Valor a Devolver");
        lblDevolver.setBounds(35,110,100,25);
        getContentPane().add(lblDevolver);

        txtDevolver = new JTextField();
        txtDevolver.setBounds(150,110,120,20);
        getContentPane().add(txtDevolver);

        btnDevolver = new JButton("calcular Devuelta");
        btnDevolver.setBounds(300,110,120,30);
        getContentPane().add(btnDevolver);

        tblDevuelta = new JTable();
        JScrollPane spDevuelta = new JScrollPane(tblDevuelta);
        spDevuelta.setBounds(10,180,460,120);
        getContentPane().add(spDevuelta);

        DefaultTableModel dtm = new DefaultTableModel(null, encabezado);
        tblDevuelta.setModel(dtm);
        
        //eventos
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ActualizarExistencia();
            }
        });

        btnDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CalcularDevolver();
            }
        });
    }

    private void ActualizarExistencia() {
        int denominacion = cmbDenominacion.getSelectedIndex();
        int cantidad = Integer.parseInt(txtCantidadExistencia.getText());
        denominaciones[denominacion] += cantidad; // Sumar la cantidad a la denominación correspondiente
        txtCantidadExistencia.setText("");
        JOptionPane.showMessageDialog(this, "Denominación agregada.");
    }

    private void CalcularDevolver() {
       
        String montoStr = txtDevolver.getText();
        if (montoStr != null) {
            monto = Integer.parseInt(montoStr);
            DefaultTableModel dtm = (DefaultTableModel) tblDevuelta.getModel();
            dtm.setRowCount(0); 

      for (int i = 0; i < valores.length; i++) {
                int valor = valores[i];
                int cantidadDisponible = denominaciones[i];
                int cantidadUsar = Math.min(monto / valor, cantidadDisponible);
                if (cantidadUsar > 0) {
                    dtm.addRow(new Object[]{valor, (valor >= 1000) ? "billete" : "moneda", cantidadUsar});
                    monto -= cantidadUsar * valor;
                }

            if (monto > 0) {
                JOptionPane.showMessageDialog(this, "No se puede devolver el monto exacto con las denominaciones disponibles.");
                }
            }
        }
    }
}

