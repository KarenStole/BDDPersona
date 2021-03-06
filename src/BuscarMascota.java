
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author francisco.perdomo
 */
public class BuscarMascota extends javax.swing.JFrame {
    BaseDeDatos1 bdd = new BaseDeDatos1();
    DefaultListModel listModel = new DefaultListModel();
    ArrayList<String> a = new ArrayList();
    int cedula;
    
    public BuscarMascota() {
        initComponents();
        listaci.setModel(listModel);
    }
    /**
     * Constructor encargado de colocar los datos iniciales en pantalla.
     * Se pasa CI de persona traido de Menu, para buscar las mascotas denunciadas 
     * cuyo dueño le pertenezca esa cedula.
     * Tambien se cargan los ComboBoxes con los datos de TipoAnimal y Raza estraidos
     * de la base de datos.
     * @param ci
     * @throws SQLException 
     */
    public BuscarMascota(int ci) throws SQLException {
        initComponents();
        listaci.setModel(listModel);
        cedula = ci;
        cargarMascotas();
        llenarComboBoxes2();
    }
/**
 * Metodo encargado de extraer en nombre de tipodenuncia, dada una mascota que se le hizo la 
 * denuncia.
 * @param id
 * @return
 * @throws SQLException 
 */
    public String mostrarTipoDenuncia(String id) throws SQLException{
        String res="";
        ResultSet rs= bdd.enviarConsulta("SELECT DESCRIPCION FROM TIPODENUNCIA WHERE TIPODENUNCIA.IDDENUNCIA IN ("
                + "SELECT tipo_DENUNCIA FROM DENUNCIA WHERE denuncia.ID_MASCOTA="+id+")");
        while(rs.next()){
            res+= rs.getString(1);
        }
        return res;
    }
/**
 * Metodo encargado de imprimir en el JPanel el resputado de una consulta en especifico.
 * En este caso particular los datos de la mascota denunciada: idmascota, nombre, descripcion, tipoDenuncia.
 * Ademas se guarda en un array los datos completos de la mascota para utilizarlos luego, cuando
 * se seleccione VerMacota.
 * @param rs 
 */   
public void imprimirResultados(ResultSet rs){
        try {
            a.clear();
            listModel.clear();
            String res="";
            String shownRes="";
            if (rs!=null){
                while (rs.next()) {
                    shownRes += rs.getString(1)+ "," 
                          +rs.getString(2)+","+ mostrarTipoDenuncia(rs.getString(1));
                    listModel.addElement(shownRes);
                    shownRes="";
                    res+= rs.getString(1)+ "," 
                          +rs.getString(2)+","
                          + rs.getString(3)+ ","
                          +rs.getString(4)+","
                          +rs.getString(5)+ ","
                          +rs.getString(6);
                    a.add(res);
                    res="";
                }
            }else{
                a.add("No se encontraron mascotas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarMascota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
/**
 * Metodo encargado de llenar el ComboBox de Razas.
 * La informacion es recolectada de la base de datos directamente, de la tabla Raza.
 * Dependiendo de que se elija en el comboBox de tipoAnimal, se presentaran las razas disponibles en la base de datos.
 * @param i
 * @throws SQLException 
 */    
private void llenarComboBoxes(int i) throws SQLException{
        raza.removeAllItems();
        ArrayList razas= new ArrayList();
        ResultSet rs= bdd.enviarConsulta("SELECT NOMBRE FROM RAZA WHERE RAZA.IDraza IN "
                + "(SELECT RAZAtipoanimal.IDraza FROM RAZATIPOANIMAL WHERE RAZATIPOANIMAL.idtipo=" +(i+1)+")");
        while(rs.next()){
            razas.add(rs.getString(1));
        }
        for( int e=0; e< razas.size();e++){
            raza.insertItemAt((String) razas.get(e), e);
        }      
    }

/**
 * Metodo encargado de llenar comboBox donde se presenta los ripos de animales.
 * Dichos datos son esctraidos directamente de la tabla TipoAnimal de la base de datos.
 * @throws SQLException 
 */
private void llenarComboBoxes2() throws SQLException{
        tipoAnimal.removeAllItems();
        ArrayList tipoanimal= new ArrayList();
        ResultSet rs= bdd.enviarConsulta("SELECT NOMBRE FROM tipoanimal");
        while(rs.next()){
            tipoanimal.add(rs.getString(1));
        }
        for( int e=0; e< tipoanimal.size();e++){
            tipoAnimal.insertItemAt((String) tipoanimal.get(e), e);
        }
    }
/**
 * Metodo encargado de cargar las mascotas denuciadas tanto de perdida como de encuentro,
 * dado el ci del dueño. Se muestra en pantalla los resultados.
 */
public void cargarMascotas (){
    ResultSet rs = bdd.enviarConsulta("select * from mascota where idmascota in "
                    + "(select id_mascota from denuncia where id_mascota in "
                    + "(select id_mascota from dueniomascota where ci_dueño = "+cedula+") "
                    + " and fecharesolucion is null)");
    imprimirResultados(rs);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        listaci = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        zona = new javax.swing.JTextField();
        lzona = new javax.swing.JLabel();
        tipoAnimal = new javax.swing.JComboBox<>();
        raza = new javax.swing.JComboBox<>();
        buscarf = new javax.swing.JButton();
        bverMasc = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Fondo.setLayout(null);

        jScrollPane2.setViewportView(listaci);

        Fondo.add(jScrollPane2);
        jScrollPane2.setBounds(30, 149, 330, 110);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 78, 150));
        jLabel1.setText("Buscar Mascota");
        Fondo.add(jLabel1);
        jLabel1.setBounds(40, 11, 210, 24);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Buscar mis mascotas", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 250));

        zona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zonaActionPerformed(evt);
            }
        });

        lzona.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        lzona.setForeground(new java.awt.Color(0, 78, 150));
        lzona.setText("Indica Zona");

        tipoAnimal.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        tipoAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAnimalActionPerformed(evt);
            }
        });

        raza.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N

        buscarf.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        buscarf.setText("Buscar");
        buscarf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lzona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(zona, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarf))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tipoAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(raza, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lzona)
                    .addComponent(buscarf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(raza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Ver mascotas perdidas", jPanel2);

        Fondo.add(jTabbedPane2);
        jTabbedPane2.setBounds(10, 39, 400, 100);

        bverMasc.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        bverMasc.setText("Ver mascota");
        bverMasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bverMascActionPerformed(evt);
            }
        });
        Fondo.add(bverMasc);
        bverMasc.setBounds(250, 270, 160, 23);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoBuscar.jpg"))); // NOI18N
        jLabel2.setText("   ");
        Fondo.add(jLabel2);
        jLabel2.setBounds(0, 0, 420, 305);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zonaActionPerformed
/**
 * Se llenan el comboBox de raza, invocando a llenarComboBoxes() dependiendo
 * de lo seleccionado en comboBox de tipoAnimal.
 * @param evt 
 */
    private void tipoAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAnimalActionPerformed
        try {
            llenarComboBoxes(tipoAnimal.getSelectedIndex());
        } catch (SQLException ex) {
            Logger.getLogger(BuscarMascota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tipoAnimalActionPerformed
/**
 * Metodo encargado de chequear que todos los datos imgresados para la busqueda sean validos.
 * Se chequea: Zona debe ser no nula y numerico
 *             Se debe haber seleccionado tipoAnimal y raza.
 * @return True si los datos son numericos, False si no.
 */
    public boolean chequeoBusqueda(){
        boolean respuesta=true;
         if( tipoAnimal.getSelectedIndex() == (-1)){
                JOptionPane.showMessageDialog(null, "Debe ingresar que animal es.");
                respuesta= false;
                return respuesta;
            }
        if( raza.getSelectedIndex() == (-1)){
                JOptionPane.showMessageDialog(null, "Debe ingresar una raza.");
                respuesta= false;
                return respuesta;
            } 
        if("".equals(zona.getText()) && !isNumeric(zona.getText())){
            JOptionPane.showMessageDialog(null, "Zona invalida");
            respuesta=false;
            return respuesta;
        }
        if(!isNumeric(zona.getText())){
            JOptionPane.showMessageDialog(null, "Zona invalida");
            respuesta=false;
            return respuesta;
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
    /**
     * Metodo encargado de realizar la busqueda dada una zona, tipo animal y raza.
     * Invoca chequeoBusqueda(), para permitir o no realizar ducha accion.
     * Se muestra en pantalla todas las denuncias hechas con esas caracterisiticas.
     * @param evt 
     */
    private void buscarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarfActionPerformed
       if(chequeoBusqueda()){
        int razas = (tipoAnimal.getSelectedIndex()*6)+(raza.getSelectedIndex()); //TODO ARREGLAR ESTO
        ResultSet rs = bdd.enviarConsulta("select * from mascota where idmascota in "
            + "(select id_mascota from denuncia where zona = "+zona.getText()+" and tipo_denuncia = 1 and fecharesolucion is null) "
            + "and id_raza = " + razas);
        imprimirResultados(rs);}
    }//GEN-LAST:event_buscarfActionPerformed
/**
 * Una vez seleccionada la mascota denunciada a ver, se invoca a otra pantalla para ver toda la infroacion
 * de la mascota junto a su foto.
 * @param evt 
 */
    private void bverMascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bverMascActionPerformed
        try{
        new VerMascota(a.get(listaci.getSelectedIndex())).setVisible(true);}
         catch (IOException ex) {
            Logger.getLogger(BuscarMascota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscarMascota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BuscarMascota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BuscarMascota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BuscarMascota.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado nada.");
        }
    }//GEN-LAST:event_bverMascActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarMascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarMascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarMascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarMascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarMascota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JButton buscarf;
    private javax.swing.JButton bverMasc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JList<String> listaci;
    private javax.swing.JLabel lzona;
    private javax.swing.JComboBox<String> raza;
    private javax.swing.JComboBox<String> tipoAnimal;
    private javax.swing.JTextField zona;
    // End of variables declaration//GEN-END:variables
}
