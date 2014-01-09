/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package forms;

import schemas.SchemaVAHDR;
import datagenerator.CSVWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import schemas.SchemaVAITM;
import schemas.SchemaVCHDR;
import schemas.SchemaVCITM;
import schemas.SchemaVDHDR;
import schemas.SchemaVDITM;
import schemas.Schemas;
/**
 *
 * @author Martin
 */
public class FormMain extends javax.swing.JFrame {

    /**
     * Creates new form DataGenerator
     */
    public FormMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Write CSV");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jScrollPane1.setViewportView(jButton1);

        jTextPane1.setDoubleBuffered(true);
        jScrollPane2.setViewportView(jTextPane1);

        jLabel1.setText("Max Rows:");

        jLabel2.setText("Max Storage (MB):");

        jLabel3.setText("Save Path:");

        jTextField3.setText("./");

        jLabel4.setText("Output:");

        jButton2.setText("Open");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Max Size/File (MB):");

        jLabel6.setText("Split File at Size (MB):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 305, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(jTextField2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(jTextField5)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        this.jTextPane1.setText("");
        
        Schemas VAHDR = new SchemaVAHDR();
        Schemas VAITM = new SchemaVAITM();
        Schemas VDHDR = new SchemaVDHDR();
        Schemas VDITM = new SchemaVDITM();
        Schemas VCHDR = new SchemaVCHDR();
        Schemas VCITM = new SchemaVCITM();
        
        VDHDR.setBottomTop(true);
        VCHDR.setBottomTop(true);
        
        VDHDR.setSubschema(VDITM);
        
        VAHDR.setSubschema(VAITM);
        
        VAITM.setParentSchema(VAHDR);
        VAITM.setSubschema(VDITM);
        VAITM.setSubschema(VCITM);
        
        VDITM.setParentSchema(VAITM);
        VDITM.setParentSchema(VDHDR);
        
        VCHDR.setSubschema(VCITM);
        VCITM.setParentSchema(VAITM);
        VCITM.setParentSchema(VCHDR);
       
        //Schemas schemas = new Schemas();
        Integer maxSize = this.jTextField2.getText().isEmpty() ? 0 : Integer.parseInt(this.jTextField2.getText());
        Integer maxRows = this.jTextField1.getText().isEmpty() ? 0 : Integer.parseInt(this.jTextField1.getText());  
        Integer maxSizePerFile = this.jTextField4.getText().isEmpty() ? 0 : Integer.parseInt(this.jTextField4.getText());
        Integer splitFileAtSize = this.jTextField5.getText().isEmpty() ? 0 : Integer.parseInt(this.jTextField5.getText());;
        Boolean success = false;
        String path = this.jTextField3.getText();
        final CSVWriter writer = new CSVWriter(VAHDR, path, maxSize, maxSizePerFile, splitFileAtSize, maxRows);
        final Calendar calender = Calendar.getInstance();
        
//        Thread time = new Thread()
//        {
//            @Override
//            public void run()
//            {
//                Long i = 0l;
//                DateFormat df = new SimpleDateFormat("hh:mm:ss.SSS");
//                
//                do {
//                    try
//                    {
//                        calender.setTimeInMillis(i);
//                        jTextField6.setText("Duration: " + df.format(calender.getTimeInMillis()));
//                        jTextField6.update(jTextField6.getGraphics());
//                        Thread.sleep(1);
//                    }
//                    catch ( InterruptedException e ) {}
//                    i++;
//                }while(!writer.isDone());
//            }
//        };
        
        
        try {
            Thread t = new Thread() 
            {
                @Override
                public void run()
                {
                    do
                    {
                        try
                        {
                            buildTextPaneContent(writer);
                            Thread.sleep( 50 );
                        }
                        catch ( InterruptedException e ) {}
                    }while (!writer.isDone());

                }
            };

            t.start();
            //time.start();
            writer.createSchemas(VAHDR);
            //writer.writeSchemas(schemas.getSchemas());
            success = true;
        }
        catch(Exception e)
        {
            this.jTextPane1.setText(jTextPane1.getText() + "\nException:\n" + e.getLocalizedMessage());
        }
        finally{
            if (success) {
                this.buildTextPaneContent(writer);
                this.jTextPane1.setText(jTextPane1.getText() + "\nDone!");
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    public void buildTextPaneContent(CSVWriter writer) {
        jTextPane1.setText("Rows: " + writer.getCurrentRows() + " (" + writer.getOverallSize()/1000 + " KB)");
        jTextPane1.setText(jTextPane1.getText() + "\n--------------------------------");
        Iterator<Map.Entry<String, List<Long>>> entries = writer.getSchemaMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<Long>> entry = entries.next();
            List<Long> l = entry.getValue();
            jTextPane1.setText(jTextPane1.getText() + "\n" + entry.getKey() + ": " + l.get(0) + " (" + (l.get(1)/1000) + " KB)");
        }
        jTextPane1.update(jTextPane1.getGraphics());
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.jFileChooser1.setFileSelectionMode( this.jFileChooser1.DIRECTORIES_ONLY);
        int returnVal = this.jFileChooser1.showOpenDialog(this);
        if (returnVal == this.jFileChooser1.APPROVE_OPTION) {
            this.jTextField3.setText(this.jFileChooser1.getSelectedFile().getAbsolutePath());
            
        } else {
            this.jTextPane1.setText("Path access cancelled by user.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}