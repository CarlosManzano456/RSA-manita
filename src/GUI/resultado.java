/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author carlo
 */
public class resultado extends javax.swing.JFrame {

    int tamprimo;
    private BigInteger p, q;
    private BigInteger fi;
    private BigInteger n, e, d;
    private int llave1;
    private String res;
    public resultado() {
        initComponents();
    }
    
    public resultado(int tamprimo) {
        this.tamprimo = tamprimo;
    }

    public void generarPrimos() {
        p = new BigInteger(tamprimo, 10, new Random());
        do
            q = new BigInteger(tamprimo, 10, new Random());
        while (q.compareTo(p) == 0);
    }

    public void generarClaves() {
        n = p.multiply(q);
        fi = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));

        do
            e = new BigInteger(2 * tamprimo, new Random());
        while (e.compareTo(fi) != 1 || e.gcd(fi).compareTo(BigInteger.valueOf(1)) != 0);

        d = e.modInverse(fi);
    }

    public BigInteger[] cifrar(String mensaje) {
        byte[] temp = mensaje.getBytes();
        BigInteger[] bigdigitos = new BigInteger[temp.length];

        for (int i = 0; i < bigdigitos.length; ++i) {
            bigdigitos[i] = new BigInteger(String.valueOf(temp[i]));
        }

        BigInteger[] cifrado = new BigInteger[bigdigitos.length];

        for (int i = 0; i < bigdigitos.length; ++i) {
            cifrado[i] = bigdigitos[i].modPow(e, n);
        }

        return cifrado;
    }

    public String descifrar(BigInteger[] cifrado) {
        BigInteger[] descifrado = new BigInteger[cifrado.length];

        for (int i = 0; i < descifrado.length; ++i) {
            descifrado[i] = cifrado[i].modPow(d, n);
        }

        char[] charArray = new char[descifrado.length];

        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char) (descifrado[i].intValue());
        }

        return new String(charArray);
    }

    public void setLLave(int llave, String txt) {
        this.llave1 = llave;
        this.res = txt;
        resultado rsa = new resultado(llave1);
        rsa.generarPrimos();
        rsa.generarClaves();
        BigInteger[] cifrado = rsa.cifrar(res);
        String mensajeDescifrado = rsa.descifrar(cifrado);
        txtArea1.setText(Arrays.toString(cifrado));
        txtArea1.setEditable(false);
        txtArea2.setText(mensajeDescifrado);
        txtArea2.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("TEXTO DESCIFRADO");

        txtArea2.setColumns(20);
        txtArea2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtArea2.setRows(5);
        jScrollPane1.setViewportView(txtArea2);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("TEXTO CIFRADO");

        txtArea1.setColumns(20);
        txtArea1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtArea1.setRows(5);
        jScrollPane2.setViewportView(txtArea1);

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Cifrar otro txt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(388, 388, 388)
                        .addComponent(jButton1))
                    .addComponent(jLabel1))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtCifrar cifrar = new txtCifrar();
        cifrar.setVisible(true);
        cifrar.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(resultado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(resultado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(resultado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(resultado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new resultado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtArea1;
    private javax.swing.JTextArea txtArea2;
    // End of variables declaration//GEN-END:variables
}
