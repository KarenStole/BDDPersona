import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author francisco.perdomo
 */
public class Denuncia extends javax.swing.JFrame {
    BaseDeDatos1 bdd = new BaseDeDatos1();
    int ciDenunciante;
    public Denuncia() {
        initComponents();
        fecha.setText(fecha());
    }
    /**
     * Dicho constructor de encarca de predefinir los datos iniciales a mostrar en pantalla.
     * Pasando la ci de la persona que infreso al programa, para utilizarlo mas adelante y setear
     * la fecha de la denuncia a la actual, para cuando se haga una denuncia.
     * Tambien se llenan los commboBoxes con relacion a tipo de denucia,
     * @param ci
     * @throws SQLException 
     */
    public Denuncia(int ci) throws SQLException {
        initComponents();
        ciDenunciante= ci ;
        fecha.setText(fecha());
        llenarComboBoxes2();
    }
/**
 * Metodo encargado de obtener la fecha actual.
 * @return fecha
 */   
     public String fecha(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");

        return date.format(now);
    }
     /**
      * Metodo encargado de imprimir en pantalla el resultado de una consulta.
      * @param rs
      * @param tipocons 
      */
    public void imprimirResultados(ResultSet rs, int tipocons){
        try {
            String res="";
            if (tipocons ==0 && rs!=null){
                while (rs.next()) {
                    res += rs.getString(1)+ ", " 
                          +rs.getString(2)+ ", "
                          +rs.getString(3)+ ", "
                          +rs.getString(4)+ ", "
                          +rs.getString(5)+ ", "
                          +rs.getString(6)+ ", "
                          +rs.getString(7)+ ", "
                          +rs.getString(8)+ ", "
                          +rs.getString(9)+ "\n";
                }
            }else{
                res = "Listo" ;
            }
            resultado.setText(res);
        } catch (SQLException ex) {
            Logger.getLogger(Denuncia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Metodo encargado de chequear los datos ingresados son correctos.
 * Chequeos: idmascota no nulo, numerico y previamente registrado.
 *          zona no nulo y numerico.
 *          tipo de denuncia ingresado.
 *          
 * @return true si todos los datos son correctos, false de lo contrario.
 */
    public boolean chequeoDatos(){
        boolean respuesta = true;
        limpiarErrores();
        try {  
            if(idmascota.getText().compareTo("")==0){
                lerrormascota.setText("Datos invalidos.");
                respuesta = false;
                return respuesta;
            }
            if (!isNumeric(idmascota.getText())){
                lerrormascota.setText("Solo numeros");
                respuesta = false;
                return respuesta;
            }
            ResultSet rs = bdd.enviarConsulta("SELECT idmascota FROM Mascota WHERE idmascota="+idmascota.getText());
            if (!rs.next()) {
                lerrormascota.setText("No existe una mascota con ese id");
                respuesta = false;
                return respuesta;
            }

            if(!isNumeric(zona.getText())){
                lerrorzona.setText("Zona debe contener solo numeros.");
                respuesta = false;
                return respuesta;
            }
            if(zona.getText().compareTo("")==0){
                lerrorzona.setText("Este campo no puede ser vacio.");
                respuesta = false;
                return respuesta;
            }
            if(tipoDenuncia.getSelectedIndex() == (-1)){
                JOptionPane.showMessageDialog(null, "Ingrese el tipo de denuncia.");
                respuesta= false;
                return respuesta;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Denuncia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException np){
            lerrorzona.setText("Debe rellenar todos los campos.");
        }
        return respuesta;
    } 
     /**
     * Metodo encargado de chequear sila cadena de caracter solo contiene numeros.
     * @param cadena
     * @return True si es solo numeros, False de lo contrario,
     */   
    public static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    public void limpiarErrores(){
        lerrormascota.setText("     ");
        lerrorzona.setText("     ");
    }
    /**
     * Metodo encargado de llenar comboBox de tipo de denucia.
     * Dicha informacion se extrae directamente de la tabla tipodenuncia de la base de datos.
     * @throws SQLException 
     */
    private void llenarComboBoxes2() throws SQLException{
        tipoDenuncia.removeAllItems();
        ArrayList tipoanimal= new ArrayList();
        ResultSet rs= bdd.enviarConsulta("SELECT descripcion FROM tipodenuncia");
        while(rs.next()){
            tipoanimal.add(rs.getString(1));
        }
        for( int e=0; e< tipoanimal.size();e++){
            tipoDenuncia.insertItemAt((String) tipoanimal.get(e), e);
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        fecha = new javax.swing.JFormattedTextField();
        lidmascota = new javax.swing.JLabel();
        lzona = new javax.swing.JLabel();
        insertar = new javax.swing.JButton();
        lfecha = new javax.swing.JLabel();
        zona = new javax.swing.JTextField();
        idmascota = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultado = new javax.swing.JTextArea();
        listar = new javax.swing.JButton();
        tipoDenuncia = new javax.swing.JComboBox<>();
        lerrormascota = new javax.swing.JLabel();
        lerrorzona = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Fondo.setLayout(null);

        Titulo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Titulo.setForeground(new java.awt.Color(0, 78, 150));
        Titulo.setText("Ingresar Denuncia");
        Fondo.add(Titulo);
        Titulo.setBounds(103, 11, 197, 29);

        fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fecha.setText("01/01/2017");
        fecha.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });
        Fondo.add(fecha);
        fecha.setBounds(480, 100, 90, 30);

        lidmascota.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        lidmascota.setForeground(new java.awt.Color(0, 78, 150));
        lidmascota.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lidmascota.setText("ID Mascota");
        Fondo.add(lidmascota);
        lidmascota.setBounds(40, 50, 90, 20);

        lzona.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        lzona.setForeground(new java.awt.Color(0, 78, 150));
        lzona.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lzona.setText("Zona perdido");
        Fondo.add(lzona);
        lzona.setBounds(40, 140, 90, 20);

        insertar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        insertar.setText("Ingresar Denuncia");
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });
        Fondo.add(insertar);
        insertar.setBounds(160, 230, 150, 23);

        lfecha.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        lfecha.setForeground(new java.awt.Color(0, 78, 150));
        lfecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lfecha.setText("Fecha Perdido");
        Fondo.add(lfecha);
        lfecha.setBounds(370, 100, 100, 15);

        zona.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        zona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zonaActionPerformed(evt);
            }
        });
        Fondo.add(zona);
        zona.setBounds(140, 140, 210, 30);

        idmascota.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        Fondo.add(idmascota);
        idmascota.setBounds(140, 50, 210, 30);

        resultado.setColumns(20);
        resultado.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 13)); // NOI18N
        resultado.setRows(5);
        jScrollPane1.setViewportView(resultado);

        Fondo.add(jScrollPane1);
        jScrollPane1.setBounds(20, 310, 620, 96);

        listar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        listar.setText("Listar denuncias");
        listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarActionPerformed(evt);
            }
        });
        Fondo.add(listar);
        listar.setBounds(250, 400, 150, 23);

        tipoDenuncia.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        tipoDenuncia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoDenunciaItemStateChanged(evt);
            }
        });
        tipoDenuncia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoDenunciaActionPerformed(evt);
            }
        });
        Fondo.add(tipoDenuncia);
        tipoDenuncia.setBounds(140, 100, 210, 21);

        lerrormascota.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        lerrormascota.setForeground(new java.awt.Color(255, 0, 0));
        lerrormascota.setText("          ");
        Fondo.add(lerrormascota);
        lerrormascota.setBounds(140, 80, 270, 20);

        lerrorzona.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        lerrorzona.setForeground(new java.awt.Color(255, 0, 0));
        Fondo.add(lerrorzona);
        lerrorzona.setBounds(140, 170, 290, 20);

        jLabel1.setForeground(new java.awt.Color(0, 78, 150));
        jLabel1.setText("Tipo de Denuncia");
        Fondo.add(jLabel1);
        jLabel1.setBounds(50, 100, 100, 14);

        lfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoMasc.jpg"))); // NOI18N
        lfondo.setText("     ");
        Fondo.add(lfondo);
        lfondo.setBounds(0, 0, 660, 520);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActionPerformed
/**
 * Una vez recolectado todos los datos en la interfaz, y chequeados invocando al metodo chequeoDatos(),
 * se ingresa dichos datos a la tabla denuncia de la base de datos.
 * @param evt 
 */
    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
        boolean chequeo = chequeoDatos();
        if (chequeo){
            ResultSet rs = bdd.enviarConsulta("INSERT INTO denuncia(cipersona, tipo_denuncia, id_mascota, fechadenuncia, zona) VALUES ("               
                + ciDenunciante + ","
                + (tipoDenuncia.getSelectedIndex()+1) + ","
                + idmascota.getText() + ","
                + "'"+ fecha.getText() + "',"
                + zona.getText() +");");
            limpiarErrores();
            imprimirResultados(rs, 1);
        }

    }//GEN-LAST:event_insertarActionPerformed

    private void zonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zonaActionPerformed
/**
 * Metodo encargado de listar en pantalla todas las denuncias hechas, recolectadas
 * de la base de datos, de la tabla denuncia.
 * @param evt 
 */
    private void listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarActionPerformed
        ResultSet rs = bdd.enviarConsulta("SELECT * FROM Denuncia;");
        imprimirResultados(rs, 0);
    }//GEN-LAST:event_listarActionPerformed

    private void tipoDenunciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoDenunciaActionPerformed
        
    }//GEN-LAST:event_tipoDenunciaActionPerformed

    private void tipoDenunciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoDenunciaItemStateChanged
        if (tipoDenuncia.getSelectedIndex()==0){
            lfecha.setText("Fecha Perdido");

        }else{
            lfecha.setText("Fecha Encontrado");
        }
    }//GEN-LAST:event_tipoDenunciaItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Denuncia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Denuncia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Denuncia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Denuncia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Denuncia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JLabel Titulo;
    private javax.swing.JFormattedTextField fecha;
    private javax.swing.JTextField idmascota;
    private javax.swing.JButton insertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lerrormascota;
    private javax.swing.JLabel lerrorzona;
    private javax.swing.JLabel lfecha;
    private javax.swing.JLabel lfondo;
    private javax.swing.JLabel lidmascota;
    private javax.swing.JButton listar;
    private javax.swing.JLabel lzona;
    private javax.swing.JTextArea resultado;
    private javax.swing.JComboBox<String> tipoDenuncia;
    private javax.swing.JTextField zona;
    // End of variables declaration//GEN-END:variables

    private void llenarComboBoxes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
