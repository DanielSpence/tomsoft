package javaapplication3;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PrinterBuild extends javax.swing.JFrame {

    public static DefaultTableModel fileTableModel;
    static SQLMethods dba;
    public static int countNumOfModels;
    public static String BuildPrinter;
    InstanceCall inst;

    public void ZcorpBuildStart(String print) {
        initComponents();
        inst = new InstanceCall();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrinterBuild.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        BuildPrinter = print;
        System.out.println("Testing");
        countNumOfModels = 0;
        fileTableModel = (DefaultTableModel) stlFileTable.getModel();
        dba = new SQLMethods();
        ErrorText.setVisible(false);
        this.setVisible(true);
    }

    public static void selectAllFiles(String Printer) {
        while (fileTableModel.getRowCount() > 0) {
            fileTableModel.removeRow(0);
        }
        switch (Printer) {
            case "ZCorp":
                ResultSet result = dba.searchApprovedZcorp();
                try {
                    while (result.next()) {
                        List<Object> data = new LinkedList<>();
                        data.add(false);
                        data.add(result.getString("fileName"));
                        data.add(result.getString("dateStarted"));
                        fileTableModel.addRow(data.toArray());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PrinterBuild.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Objet":
                ResultSet result2 = dba.searchApprovedObjet();
                try {
                    while (result2.next()) {
                        List<Object> data = new LinkedList<>();
                        // data.add((Boolean) false);
                        data.add(false);
                        data.add(result2.getString("fileName"));
                        data.add(result2.getString("dateStarted"));
                        fileTableModel.addRow(data.toArray());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PrinterBuild.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Solidscape":
                ResultSet result3 = dba.searchApprovedSolidscape();
                try {
                    while (result3.next()) {
                        List<Object> data = new LinkedList<>();
                        // data.add((Boolean) false);
                        data.add(false);
                        data.add(result3.getString("fileName"));
                        data.add(result3.getString("dateStarted"));
                        fileTableModel.addRow(data.toArray());
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
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean valid() {
        if (BuildName.getText().isEmpty()) {
            ErrorText.setText("Choose a build file above!");
            ErrorText.setVisible(true);
            return false;
        } else {//checks to see if any sleections in table exist to prevent no file submit case
            for (int z = 0; z < fileTableModel.getRowCount(); z++) {
                if ((Boolean) fileTableModel.getValueAt(z, 0)) {
                    return true;
                }
            }
            ErrorText.setText("Select Files used for build!");
            ErrorText.setVisible(true);
            return false;
        }
    }

    private void updateRecordInPendingJobsTable(String b, String f) { // this should update buildName of each file in pending
        File buildName = new File(b);
        String bName = buildName.getName();
        //System.out.println("Updating " + b + " to be associated with " + f);
        dba.updatePendingJobsBuildName(bName, f);
    }

    public void submit() {
        countNumOfModels = 0;
        if (valid()) {
            ErrorText.setVisible(false);
            Boolean bool = false;

            //int z;
            //ArrayList selected = new ArrayList();
            for (int z = 0; z < fileTableModel.getRowCount(); z++) {
                if ((Boolean) fileTableModel.getValueAt(z, 0)) {
                    updateRecordInPendingJobsTable(BuildName.getText(), (String) fileTableModel.getValueAt(z, 1));
                    countNumOfModels++;
                }
            }
            //now number of models are set
            //let's sequentially open Zcorp windows FOR EACH build-based STL file
            switch (BuildPrinter) {
                case "ZCorp":
                    ZCorpDialog zd = new ZCorpDialog(new java.awt.Frame(), true, BuildName.getText(), countNumOfModels);
                    zd.ZCorpDialogStart();
                    break;
                case "Objet":
                    ObjetDialog od = new ObjetDialog(new java.awt.Frame(), true, BuildName.getText(), countNumOfModels);
                    od.ObjetDialogStart();
                    break;
                case "Solidscape":
                    SolidscapeDialog sd = new SolidscapeDialog(new java.awt.Frame(), true, BuildName.getText(), countNumOfModels);
                    sd.SolidscapeDialogStart();
                    break;
            }
            while (fileTableModel.getRowCount() > 0) {
                fileTableModel.removeRow(0);
            }
            System.out.println("now repopulating");
            selectAllFiles(BuildPrinter);
            dispose();

        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        PrinterBuildHeader = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Submit_Button = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        buildLbl = new javax.swing.JLabel();
        BuildName = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        ErrorText = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stlFileTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        reportsMenu = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenu = new javax.swing.JMenuItem();

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
        setTitle("Build File Creator");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PrinterBuildHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PrinterBuildHeader.setText("x");
        getContentPane().add(PrinterBuildHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 380, 10));

        Submit_Button.setText("Submit");
        Submit_Button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Submit_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Submit_ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Submit_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, 60, -1));

        closeBtn.setText("Close");
        closeBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        getContentPane().add(closeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, 60, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Choose STL files from build: ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 200, 19));

        buildLbl.setText("Build File Name:");
        getContentPane().add(buildLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        BuildName.setEditable(false);
        BuildName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuildNameActionPerformed(evt);
            }
        });
        getContentPane().add(BuildName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 200, -1));

        browseBtn.setText("Browse");
        browseBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });
        getContentPane().add(browseBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 70, -1));

        ErrorText.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        ErrorText.setForeground(new java.awt.Color(255, 0, 0));
        ErrorText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ErrorText.setText("Error Text");
        getContentPane().add(ErrorText, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 180, -1));

        stlFileTable.setAutoCreateRowSorter(true);
        stlFileTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Project Title", "Date Submitted"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(stlFileTable);
        if (stlFileTable.getColumnModel().getColumnCount() > 0) {
            stlFileTable.getColumnModel().getColumn(0).setMinWidth(30);
            stlFileTable.getColumnModel().getColumn(0).setMaxWidth(30);
            stlFileTable.getColumnModel().getColumn(1).setResizable(false);
            stlFileTable.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 360, 350));

        fileMenu.setText("File");

        reportsMenu.setText("Reports");
        reportsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsMenuActionPerformed(evt);
            }
        });
        fileMenu.add(reportsMenu);

        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");

        contentsMenu.setText("Contents");
        contentsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentsMenuActionPerformed(evt);
            }
        });
        helpMenu.add(contentsMenu);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void Submit_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Submit_ButtonActionPerformed
        //add stl information to build table zcorp and create incomplete entry
        submit();
    }//GEN-LAST:event_Submit_ButtonActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void BuildNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuildNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuildNameActionPerformed

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        JFileChooser chooser = new JFileChooser();//Select Default
        chooser.setPreferredSize(new Dimension(800, 500));
        int returnVal = chooser.showDialog(null, "Select");

        if (returnVal == chooser.APPROVE_OPTION) {
            File myFile = chooser.getSelectedFile();
            //String fileName = myFile.getName();
            BuildName.setText(myFile.getAbsolutePath().replaceAll("'", ""));
        }
        if (!BuildName.getText().isEmpty()) {
            clearEntries(fileTableModel);
            selectAllFiles(BuildPrinter);
        }
    }//GEN-LAST:event_browseBtnActionPerformed
    public void clearEntries(DefaultTableModel fileTableModel) {
        while (fileTableModel.getRowCount() > 0) {
            fileTableModel.removeRow(0);
        }
    }
    private void reportsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsMenuActionPerformed
        // TODO add your handling code here:
        Reports reports = new Reports();
        reports.ReportsPage();
    }//GEN-LAST:event_reportsMenuActionPerformed

    private void contentsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentsMenuActionPerformed
        // TODO add your handling code here:
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + inst.getPDF());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");  //print the error
        }
    }//GEN-LAST:event_contentsMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BuildName;
    private javax.swing.JLabel ErrorText;
    public static javax.swing.JLabel PrinterBuildHeader;
    private javax.swing.JButton Submit_Button;
    private javax.swing.JButton browseBtn;
    private javax.swing.JLabel buildLbl;
    private javax.swing.JButton closeBtn;
    private javax.swing.JMenuItem contentsMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem reportsMenu;
    private javax.swing.JTable stlFileTable;
    // End of variables declaration//GEN-END:variables
}
