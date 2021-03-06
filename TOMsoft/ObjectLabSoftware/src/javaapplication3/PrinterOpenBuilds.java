package javaapplication3;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matt
 */
public class PrinterOpenBuilds extends javax.swing.JFrame {

    /**
     * Creates new form Options
     */
    static DefaultTableModel OpenBuildsTableModel;
    ZCorpDialog zd;
    ObjetDialog od;
    SolidscapeDialog sd;

    static SQLMethods dba;
    String openBuildsPrinter;
    Reports reports;
    InstanceCall inst;

    public void ZcorpOpenBuildsStart(String printer) {
        inst = new InstanceCall();
        openBuildsPrinter = printer;
        initComponents();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrinterOpenBuilds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        dba = new SQLMethods();
        OpenBuildsTableModel = (DefaultTableModel) buildTable.getModel();

        setVisible(true);
        selectAllFiles(openBuildsPrinter);
    }

    public static void selectAllFiles(String Printer) {
        while (OpenBuildsTableModel.getRowCount() > 0) {
            OpenBuildsTableModel.removeRow(0);
        }
        switch (Printer) {
            case "ZCorp":
                ResultSet result = dba.selectIncompleteFromZCorp();
                try {
                    while (result.next()) {
                        List<Object> data = new LinkedList<>();
                        data.add(result.getString("buildName"));
                        data.add(result.getString("dateRun"));
                        data.add(result.getString("noModels"));
                        OpenBuildsTableModel.addRow(data.toArray());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PrinterBuild.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Objet":
                ResultSet result2 = dba.selectIncompleteFromObjet();
                try {
                    while (result2.next()) {
                        List<Object> data = new LinkedList<>();
                        data.add(result2.getString("buildName"));
                        data.add(result2.getString("dateRun"));
                        data.add(result2.getString("noModels"));
                        OpenBuildsTableModel.addRow(data.toArray());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PrinterBuild.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Solidscape":
                ResultSet result3 = dba.selectIncompleteFromSolidscape();
                try {
                    while (result3.next()) {
                        List<Object> data = new LinkedList<>();
                        data.add(result3.getString("buildName"));
                        data.add(result3.getString("dateRun"));
                        data.add(result3.getString("noModels"));
                        OpenBuildsTableModel.addRow(data.toArray());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PrinterBuild.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        OpenBuildsHeader = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        submitBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        buildTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Art 101-001\nArt 201-002\nArt 401-004\nArt 501-005\nArt 601-006\nArt 701-007\nArt 801-009");
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open Build Editor");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        OpenBuildsHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        OpenBuildsHeader.setText("X");
        getContentPane().add(OpenBuildsHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 380, 10));

        submitBtn.setText("Open");
        submitBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        getContentPane().add(submitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 60, -1));

        closeBtn.setText("Close");
        closeBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        getContentPane().add(closeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 60, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Open Build Files");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 110, 19));

        buildTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Build Title", "Date Started", "# of Models"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        buildTable.setFillsViewportHeight(true);
        buildTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                buildTablePropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(buildTable);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 360, 350));

        jMenu1.setText("File");

        jMenuItem1.setText("Reports");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem2.setText("Contents");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private boolean checkItemSelected() { // checks if row is selected; -1 if not
        return buildTable.getSelectedRow() != -1;
    }
    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (checkItemSelected()) {
            switch (openBuildsPrinter) {
                case "ZCorp":
                    zd = new ZCorpDialog(new java.awt.Frame(), false, (String) buildTable.getValueAt(buildTable.getSelectedRow(), 0),
                            (Integer.parseInt((buildTable.getValueAt(buildTable.getSelectedRow(), 2)).toString())));
                    try {
                        ZCorpMain.dba.deleteByBuildName((String) buildTable.getValueAt(buildTable.getSelectedRow(), 0), "zcorp");
                    } catch (SQLException e) {
                        System.out.println("coudn't delete record");
                    }
                    zd.ZCorpDialogStart();
                    break;
                case "Objet":
                    od = new ObjetDialog(new java.awt.Frame(), false, (String) buildTable.getValueAt(buildTable.getSelectedRow(), 0),
                            (Integer.parseInt((buildTable.getValueAt(buildTable.getSelectedRow(), 2)).toString())));
                    try {
                        ObjetMain.dba.deleteByBuildName((String) buildTable.getValueAt(buildTable.getSelectedRow(), 0), "objet");
                    } catch (SQLException e) {
                        System.out.println("coudn't delete record");
                    }
                    od.ObjetDialogStart();
                    break;
                case "Solidscape":
                    sd = new SolidscapeDialog(new java.awt.Frame(), false, (String) buildTable.getValueAt(buildTable.getSelectedRow(), 0),
                            (Integer.parseInt((buildTable.getValueAt(buildTable.getSelectedRow(), 2)).toString())));
                    try {
                        SolidscapeMain.dba.deleteByBuildName((String) buildTable.getValueAt(buildTable.getSelectedRow(), 0), "solidscape");
                    } catch (SQLException e) {
                        System.out.println("coudn't delete record");
                    }
                    sd.SolidscapeDialogStart();
                    break;
            }
        }
        while (OpenBuildsTableModel.getRowCount() > 0) {
            OpenBuildsTableModel.removeRow(0);
        }
        System.out.println("now repopulating");
        selectAllFiles(openBuildsPrinter);
        dispose();
    }//GEN-LAST:event_submitBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void buildTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_buildTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_buildTablePropertyChange

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        reports.ReportsPage();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + inst.getPDF());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");  //print the error
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel OpenBuildsHeader;
    private javax.swing.JTable buildTable;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
