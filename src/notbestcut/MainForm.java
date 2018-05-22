/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notbestcut;

import database.ChangeSaver;
import database.DBLoader;
import database.SQLiteManager;
import entities.Client;
import entities.Material;
import entities.Orders;
import entities.Details;
import java.awt.Component;
import java.lang.String;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Max
 */
public class MainForm extends javax.swing.JFrame {
    
    ChangeSaver orderLists, materialLists, detailLists[];
    int id_order, cnt;
    boolean askUser, firstClick, userChange;
    
    public MainForm() {
        initComponents();
        initTables();
        hideTabs();
        userChange = false;
        askUser = false;
        firstClick = true;
        id_order = -10;
        orderLists = new ChangeSaver();
        materialLists = new ChangeSaver();
        detailLists = new ChangeSaver[7];
        for (int i = 0; i < 7; i++)
            detailLists[i] = new ChangeSaver();
    }
    
    private void initTables(){
        jTableOrders.removeColumn(jTableOrders.getColumnModel().getColumn(0));
        jTableOrders.getColumnModel().getColumn(0).setMaxWidth(50);
        jTableOrders.getColumnModel().getColumn(1).setMaxWidth(200);
        jTableOrders.getColumnModel().getColumn(2).setMaxWidth(200);
        jTableOrders.getColumnModel().getColumn(3).setMaxWidth(150);
        ButtonsHandler.refreshTableOrders(jTableOrders);
        
        jTableOrdMat.removeColumn(jTableOrdMat.getColumnModel().getColumn(0));
        
        jTableOrdStats.removeColumn(jTableOrdStats.getColumnModel().getColumn(0));
        
        jTableOrdService.removeColumn(jTableOrdService.getColumnModel().getColumn(0));
        jTableOrdService.getColumnModel().getColumn(0).setMaxWidth(50);
        
        
        jTableSheet.getColumnModel().getColumn(0).setMaxWidth(30);
        jTableSheet.getColumnModel().getColumn(1).setMinWidth(150);    
        jTableSheet.removeColumn(jTableSheet.getColumnModel().getColumn(4));
        
        //AllStaff
        jTableAllStaff.removeColumn(jTableAllStaff.getColumnModel().getColumn(0));
        jTableAllStaff.getColumnModel().getColumn(0).setMaxWidth(48);
        jTableAllStaff.getColumnModel().getColumn(1).setMinWidth(85);
        jTableAllStaff.getColumnModel().getColumn(1).setMaxWidth(85);
        jTableAllStaff.getColumnModel().getColumn(2).setMinWidth(85);
        jTableAllStaff.getColumnModel().getColumn(2).setMaxWidth(85);
        jTableAllStaff.getColumnModel().getColumn(3).setMaxWidth(85);
        jTableAllStaff.getColumnModel().getColumn(4).setMaxWidth(85);
        jTableAllStaff.getColumnModel().getColumn(5).setMinWidth(85);
        
        jTableStaffPost.removeColumn(jTableStaffPost.getColumnModel().getColumn(0));
        
        jTableAllClients.removeColumn(jTableAllClients.getColumnModel().getColumn(0));
        jTableAllClients.getColumnModel().getColumn(0).setMaxWidth(30);
        jTableAllClients.getColumnModel().getColumn(0).setMaxWidth(84);
        jTableAllClients.getColumnModel().getColumn(1).setMaxWidth(84);
        jTableAllClients.getColumnModel().getColumn(2).setMaxWidth(84);
        jTableAllClients.getColumnModel().getColumn(3).setMaxWidth(84);
        jTableAllClients.getColumnModel().getColumn(4).setMinWidth(84);
        jTableAllClients.getColumnModel().getColumn(4).setMinWidth(130);
        
        jLabel21.setVisible(false);
        jLabelError.setVisible(false);
        jLabelError1.setVisible(false);
        jLabelError2.setVisible(false);
        jLabelError3.setVisible(false);
        jLabelError4.setVisible(false);
        
        jTableOrdMat.getColumnModel().getColumn(0).setMinWidth(150);
        jTableOrdMat.getColumnModel().getColumn(1).setMaxWidth(90);
        jTableOrdMat.getColumnModel().getColumn(2).setMaxWidth(70);
        
        jTableOrdService.getColumnModel().getColumn(0).setMaxWidth(30);
        jTableOrdService.getColumnModel().getColumn(1).setMinWidth(110);
        jTableOrdService.getColumnModel().getColumn(1).setMaxWidth(110);
        jTableOrdService.getColumnModel().getColumn(3).setMaxWidth(70);
        
        jTableMaterial.removeColumn(jTableMaterial.getColumnModel().getColumn(0));
        jTablePosts.removeColumn(jTablePosts.getColumnModel().getColumn(0));
        jTableStatus.removeColumn(jTableStatus.getColumnModel().getColumn(0));
        jTableService.removeColumn(jTableService.getColumnModel().getColumn(0));
        jTableService.getColumnModel().getColumn(1).setMaxWidth(70);
    }
    
    private void hideTabs(){
        for (int i = 0; i < 7; i++)
            this.jTabbedPane2.setEnabledAt(i, false);
        
        jTable1.removeColumn(jTable1.getColumnModel().getColumn(0));
        jTable3.removeColumn(jTable3.getColumnModel().getColumn(6));
        jTable4.removeColumn(jTable4.getColumnModel().getColumn(6));
        jTable5.removeColumn(jTable5.getColumnModel().getColumn(6));
        jTable6.removeColumn(jTable6.getColumnModel().getColumn(6));
        jTable7.removeColumn(jTable7.getColumnModel().getColumn(6));
        jTable8.removeColumn(jTable8.getColumnModel().getColumn(6));
        jTable10.removeColumn(jTable10.getColumnModel().getColumn(6));
        jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        jTable1.getColumnModel().getColumn(1).setMinWidth(280);
        jTable3.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable3.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable3.getColumnModel().getColumn(5).setMinWidth(200);
        jTable4.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable4.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable4.getColumnModel().getColumn(5).setMinWidth(200);
        jTable5.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable5.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable5.getColumnModel().getColumn(5).setMinWidth(200);
        jTable6.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable6.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable6.getColumnModel().getColumn(5).setMinWidth(200);
        jTable7.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable7.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable7.getColumnModel().getColumn(5).setMinWidth(200);
        jTable8.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable8.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable8.getColumnModel().getColumn(5).setMinWidth(200);
        jTable10.getColumnModel().getColumn(0).setMaxWidth(100);
        jTable10.getColumnModel().getColumn(4).setMaxWidth(100);
        jTable10.getColumnModel().getColumn(5).setMinWidth(200);
        
        jTableOrdSheets.removeColumn(jTableOrdSheets.getColumnModel().getColumn(2));
        
        jLabelFilter1.setVisible(false);
        jLabelFilter2.setVisible(false);
        jTextFieldFilter1.setVisible(false);
        jTextFieldFilter2.setVisible(false);
        jLabelFilter3.setVisible(false);
        jComboBoxFilter.setVisible(false);
        jButtonFilterSearch.setVisible(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableSheet = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jDialogEmptyDetails = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jDialogEmptyCells = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jDialogNewClient = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldSurname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldAddress = new javax.swing.JTextField();
        jTextFieldTel = new javax.swing.JTextField();
        jButtonClientAdd = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jDialogNewStaff = new javax.swing.JDialog();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldStaffName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldStaffSurname = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldStaffTel = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldStaffAdr = new javax.swing.JTextField();
        jButtonStaffAdd = new javax.swing.JButton();
        jButtonCancel1 = new javax.swing.JButton();
        jComboBoxStaffPost = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldStaffBirth = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabelError3 = new javax.swing.JLabel();
        jTextFieldStaffPostDate = new javax.swing.JTextField();
        jDialogInfo = new javax.swing.JDialog();
        jTabbedPaneInfo = new javax.swing.JTabbedPane();
        jPanelMaterial = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTableMaterial = new javax.swing.JTable();
        jButtonAddMaterial = new javax.swing.JButton();
        jButtonDelMaterial = new javax.swing.JButton();
        jPanelPost = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTablePosts = new javax.swing.JTable();
        jButtonAddStaff = new javax.swing.JButton();
        jButtonDelStaff = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTableStatus = new javax.swing.JTable();
        jButtonAddStatus = new javax.swing.JButton();
        jButtonDelStatus = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTableService = new javax.swing.JTable();
        jButtonAddService = new javax.swing.JButton();
        jButtonDelService = new javax.swing.JButton();
        jButtonApplyChanges = new javax.swing.JButton();
        jButtonCanelChanges = new javax.swing.JButton();
        jDialogAllStaff = new javax.swing.JDialog();
        jButtonOpenAddStaff = new javax.swing.JButton();
        jButtonOpenEditStaff = new javax.swing.JButton();
        jButtonDelOrder1 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTableStaffPost = new javax.swing.JTable();
        jButtonOpenAddStaff1 = new javax.swing.JButton();
        jButtonDelOrder2 = new javax.swing.JButton();
        jButtonOpenAddStaff2 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTableAllStaff = new javax.swing.JTable();
        jCheckBoxSearch = new javax.swing.JCheckBox();
        jComboBoxStaffPostSearch = new javax.swing.JComboBox<>();
        jDialogAllClients = new javax.swing.JDialog();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTableAllClients = new javax.swing.JTable();
        jButtonOpenAddClient = new javax.swing.JButton();
        jButtonOpenEdiClient = new javax.swing.JButton();
        jButtonDelOrder3 = new javax.swing.JButton();
        jButtonApplyClients = new javax.swing.JButton();
        jDialogNewOrderService = new javax.swing.JDialog();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxServices = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jButtonClientAdd3 = new javax.swing.JButton();
        jButtonCancel3 = new javax.swing.JButton();
        jComboBoxStaff = new javax.swing.JComboBox<>();
        jDialogAddPost = new javax.swing.JDialog();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldAddPost = new javax.swing.JTextField();
        jButtonAddPost = new javax.swing.JButton();
        jButtonCancel4 = new javax.swing.JButton();
        jLabelError = new javax.swing.JLabel();
        jDialogAddMaterial = new javax.swing.JDialog();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldAddMaterial = new javax.swing.JTextField();
        jButtonAddMaterial1 = new javax.swing.JButton();
        jButtonCancel5 = new javax.swing.JButton();
        jLabelError1 = new javax.swing.JLabel();
        jDialogAddService = new javax.swing.JDialog();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldServicePrice = new javax.swing.JTextField();
        jButtonAddService1 = new javax.swing.JButton();
        jButtonCancel6 = new javax.swing.JButton();
        jLabelError2 = new javax.swing.JLabel();
        jTextFieldAddService1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jDialogAddStatus = new javax.swing.JDialog();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldAddStatus = new javax.swing.JTextField();
        jButtonAddStatus1 = new javax.swing.JButton();
        jButtonCancel8 = new javax.swing.JButton();
        jLabelError4 = new javax.swing.JLabel();
        jDialogNewOrder = new javax.swing.JDialog();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldOrdDesc = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jComboBoxClients = new javax.swing.JComboBox<>();
        jButtonOrderAdd = new javax.swing.JButton();
        jButtonCancel2 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jComboBoxStats = new javax.swing.JComboBox<>();
        jTextFieldOrdDate = new javax.swing.JTextField();
        jLabelError6 = new javax.swing.JLabel();
        jDialogAddSheet = new javax.swing.JDialog();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldLength = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jComboBoxMaterial = new javax.swing.JComboBox<>();
        jLabelError5 = new javax.swing.JLabel();
        jButtonSheetAdd = new javax.swing.JButton();
        jButtonCancel7 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldWidth = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextFieldPrice = new javax.swing.JTextField();
        jDialogAddOrdStatus = new javax.swing.JDialog();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldStatDate = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jComboBoxStatuses = new javax.swing.JComboBox<>();
        jButtonStatAdd = new javax.swing.JButton();
        jLabelError7 = new javax.swing.JLabel();
        jButtonCancel9 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTsbbedPaneOrders = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableOrders = new javax.swing.JTable();
        jButtonAddOrder = new javax.swing.JButton();
        jButtonEditOrder = new javax.swing.JButton();
        jButtonDelOrder = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableOrdMat = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButtonAddStatToOrd = new javax.swing.JButton();
        jButtonDelStatOrd = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTableOrdService = new javax.swing.JTable();
        jButtonAddOrdServ = new javax.swing.JButton();
        jButtonDelOrdServ = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTableOrdStats = new javax.swing.JTable();
        jButtonAddOrdMat = new javax.swing.JButton();
        jButtonDelOrdMat = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabelFilter1 = new javax.swing.JLabel();
        jLabelFilter2 = new javax.swing.JLabel();
        jTextFieldFilter1 = new javax.swing.JTextField();
        jTextFieldFilter2 = new javax.swing.JTextField();
        jComboBoxFilter = new javax.swing.JComboBox<>();
        jLabelFilter3 = new javax.swing.JLabel();
        jButtonFilterSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableOrdSheets = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButtonAddDetail = new javax.swing.JButton();
        jButtonDeleteDetail = new javax.swing.JButton();
        jButtonCreateSheets = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jSheetPaintPanel2 = new notbestcut.JSheetPaintPanel();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemStaff = new javax.swing.JMenuItem();
        jMenuItemClient = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jDialog1.setTitle("Заказы");
        jDialog1.setMinimumSize(new java.awt.Dimension(489, 285));
        jDialog1.setName("dialogOrder"); // NOI18N
        jDialog1.setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "№", "Клиент", "Принят", "Исполнен"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton10.setBackground(new java.awt.Color(52, 142, 249));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Добавить");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(52, 142, 249));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Удалить");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(52, 142, 249));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("ОК");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addContainerGap())
        );

        jDialog2.setTitle("Материалы");
        jDialog2.setMinimumSize(new java.awt.Dimension(472, 253));
        jDialog2.setResizable(false);

        jTableSheet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "№", "Материал", "Длина", "Ширина", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSheet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableSheetKeyPressed(evt);
            }
        });
        jScrollPane9.setViewportView(jTableSheet);

        jButton13.setText("Добавить");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Удалить");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Добавить в заказ");
        jButton15.setEnabled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("ОК");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogEmptyDetails.setTitle("Error");
        jDialogEmptyDetails.setAlwaysOnTop(true);
        jDialogEmptyDetails.setMinimumSize(new java.awt.Dimension(314, 130));
        jDialogEmptyDetails.setResizable(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Добавьте детали для раскроя!");

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton19.setText("OK");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogEmptyDetailsLayout = new javax.swing.GroupLayout(jDialogEmptyDetails.getContentPane());
        jDialogEmptyDetails.getContentPane().setLayout(jDialogEmptyDetailsLayout);
        jDialogEmptyDetailsLayout.setHorizontalGroup(
            jDialogEmptyDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogEmptyDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jDialogEmptyDetailsLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogEmptyDetailsLayout.setVerticalGroup(
            jDialogEmptyDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogEmptyDetailsLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jButton19)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jDialogEmptyCells.setTitle("Warning");
        jDialogEmptyCells.setMinimumSize(new java.awt.Dimension(251, 120));
        jDialogEmptyCells.setResizable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("<html>Элементы с пустыми значениями <br>не будут сохранены. Продолжить?");

        jButton7.setText("Отмена");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("ОК");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogEmptyCellsLayout = new javax.swing.GroupLayout(jDialogEmptyCells.getContentPane());
        jDialogEmptyCells.getContentPane().setLayout(jDialogEmptyCellsLayout);
        jDialogEmptyCellsLayout.setHorizontalGroup(
            jDialogEmptyCellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogEmptyCellsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jDialogEmptyCellsLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(44, 44, 44))
        );
        jDialogEmptyCellsLayout.setVerticalGroup(
            jDialogEmptyCellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogEmptyCellsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jDialogEmptyCellsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        jLabel3.getAccessibleContext().setAccessibleName("Материалы с пустыми значениями не <br>будут сохранены. Продолжить?");

        jDialogNewClient.setTitle("Новый клиент");
        jDialogNewClient.setMinimumSize(new java.awt.Dimension(380, 300));
        jDialogNewClient.setResizable(false);

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Имя*");

        jTextFieldSurname.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Фамилия");

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Адрес");

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Телефон");

        jTextFieldName.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jTextFieldAddress.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jTextFieldTel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jButtonClientAdd.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonClientAdd.setText("ОК");
        jButtonClientAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientAddActionPerformed(evt);
            }
        });

        jButtonCancel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonCancel.setText("Отмена");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText(" Поле должно быть заполнено");

        javax.swing.GroupLayout jDialogNewClientLayout = new javax.swing.GroupLayout(jDialogNewClient.getContentPane());
        jDialogNewClient.getContentPane().setLayout(jDialogNewClientLayout);
        jDialogNewClientLayout.setHorizontalGroup(
            jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNewClientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addGroup(jDialogNewClientLayout.createSequentialGroup()
                        .addComponent(jButtonClientAdd)
                        .addGap(56, 56, 56)
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldTel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jDialogNewClientLayout.setVerticalGroup(
            jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNewClientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClientAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jDialogNewStaff.setMinimumSize(new java.awt.Dimension(374, 400));
        jDialogNewStaff.setResizable(false);

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Имя");

        jTextFieldStaffName.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Фамилия");

        jTextFieldStaffSurname.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Телефон");

        jTextFieldStaffTel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Адрес");

        jTextFieldStaffAdr.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jButtonStaffAdd.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonStaffAdd.setText("ОК");
        jButtonStaffAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStaffAddActionPerformed(evt);
            }
        });

        jButtonCancel1.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonCancel1.setText("Отмена");
        jButtonCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel1ActionPerformed(evt);
            }
        });

        jComboBoxStaffPost.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jComboBoxStaffPost.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxStaffPostItemStateChanged(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Должность");

        jTextFieldStaffBirth.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("День рожд.");

        jLabelError3.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError3.setText("Поля должны быть заполнены");

        jTextFieldStaffPostDate.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        javax.swing.GroupLayout jDialogNewStaffLayout = new javax.swing.GroupLayout(jDialogNewStaff.getContentPane());
        jDialogNewStaff.getContentPane().setLayout(jDialogNewStaffLayout);
        jDialogNewStaffLayout.setHorizontalGroup(
            jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNewStaffLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jButtonStaffAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jButtonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(jDialogNewStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelError3)
                    .addComponent(jTextFieldStaffName, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldStaffSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldStaffAdr, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldStaffTel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextFieldStaffBirth, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addGroup(jDialogNewStaffLayout.createSequentialGroup()
                        .addComponent(jComboBoxStaffPost, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldStaffPostDate)))
                .addContainerGap())
        );
        jDialogNewStaffLayout.setVerticalGroup(
            jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNewStaffLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStaffSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxStaffPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldStaffPostDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStaffTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStaffAdr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStaffBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(2, 2, 2)
                .addComponent(jLabelError3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogNewStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStaffAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(84, 84, 84))
        );

        jDialogInfo.setTitle("Общая информация");
        jDialogInfo.setLocationByPlatform(true);
        jDialogInfo.setMinimumSize(new java.awt.Dimension(319, 430));
        jDialogInfo.setResizable(false);

        jTableMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Название"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jTableMaterial);

        jButtonAddMaterial.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddMaterial.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddMaterial.setText("Добавить");
        jButtonAddMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMaterialActionPerformed(evt);
            }
        });

        jButtonDelMaterial.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelMaterial.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelMaterial.setText("Удалить");
        jButtonDelMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelMaterialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMaterialLayout = new javax.swing.GroupLayout(jPanelMaterial);
        jPanelMaterial.setLayout(jPanelMaterialLayout);
        jPanelMaterialLayout.setHorizontalGroup(
            jPanelMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaterialLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMaterialLayout.createSequentialGroup()
                        .addComponent(jButtonAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDelMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelMaterialLayout.setVerticalGroup(
            jPanelMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMaterialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddMaterial)
                    .addComponent(jButtonDelMaterial))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPaneInfo.addTab("Материалы", jPanelMaterial);

        jTablePosts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Название"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jTablePosts);

        jButtonAddStaff.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddStaff.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddStaff.setText("Добавить");
        jButtonAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddStaffActionPerformed(evt);
            }
        });

        jButtonDelStaff.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelStaff.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelStaff.setText("Удалить");
        jButtonDelStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPostLayout = new javax.swing.GroupLayout(jPanelPost);
        jPanelPost.setLayout(jPanelPostLayout);
        jPanelPostLayout.setHorizontalGroup(
            jPanelPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPostLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPostLayout.createSequentialGroup()
                        .addComponent(jButtonAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDelStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelPostLayout.setVerticalGroup(
            jPanelPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPostLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddStaff)
                    .addComponent(jButtonDelStaff))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPaneInfo.addTab("Должности", jPanelPost);

        jTableStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Название"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane18.setViewportView(jTableStatus);

        jButtonAddStatus.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddStatus.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddStatus.setText("Добавить");
        jButtonAddStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddStatusActionPerformed(evt);
            }
        });

        jButtonDelStatus.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelStatus.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelStatus.setText("Удалить");
        jButtonDelStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonAddStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddStatus)
                    .addComponent(jButtonDelStatus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneInfo.addTab("Статусы заказа", jPanel5);

        jTableService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Название", "Цена"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(jTableService);

        jButtonAddService.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddService.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddService.setText("Добавить");
        jButtonAddService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddServiceActionPerformed(evt);
            }
        });

        jButtonDelService.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelService.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelService.setText("Удалить");
        jButtonDelService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelServiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonAddService, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDelService, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddService)
                    .addComponent(jButtonDelService))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPaneInfo.addTab("Услуги", jPanel6);

        jButtonApplyChanges.setText("ОК");

        jButtonCanelChanges.setText("Cancel");

        javax.swing.GroupLayout jDialogInfoLayout = new javax.swing.GroupLayout(jDialogInfo.getContentPane());
        jDialogInfo.getContentPane().setLayout(jDialogInfoLayout);
        jDialogInfoLayout.setHorizontalGroup(
            jDialogInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneInfo)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogInfoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonApplyChanges)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCanelChanges)))
                .addContainerGap())
        );
        jDialogInfoLayout.setVerticalGroup(
            jDialogInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialogInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonApplyChanges)
                    .addComponent(jButtonCanelChanges))
                .addContainerGap())
        );

        jDialogAllStaff.setTitle("Список сотрудников");
        jDialogAllStaff.setMinimumSize(new java.awt.Dimension(800, 310));

        jButtonOpenAddStaff.setBackground(new java.awt.Color(52, 142, 249));
        jButtonOpenAddStaff.setForeground(new java.awt.Color(255, 255, 255));
        jButtonOpenAddStaff.setText("Добавить");
        jButtonOpenAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenAddStaffActionPerformed(evt);
            }
        });

        jButtonOpenEditStaff.setBackground(new java.awt.Color(52, 142, 249));
        jButtonOpenEditStaff.setForeground(new java.awt.Color(255, 255, 255));
        jButtonOpenEditStaff.setText("Редактировать");

        jButtonDelOrder1.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelOrder1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelOrder1.setText("Удалить");
        jButtonDelOrder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrder1ActionPerformed(evt);
            }
        });

        jTableStaffPost.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Дата", "Должность"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane20.setViewportView(jTableStaffPost);

        jButtonOpenAddStaff1.setBackground(new java.awt.Color(52, 142, 249));
        jButtonOpenAddStaff1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonOpenAddStaff1.setText("Добавить");

        jButtonDelOrder2.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelOrder2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelOrder2.setText("Удалить");
        jButtonDelOrder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrder2ActionPerformed(evt);
            }
        });

        jButtonOpenAddStaff2.setBackground(new java.awt.Color(52, 142, 249));
        jButtonOpenAddStaff2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonOpenAddStaff2.setText("ОК");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(52, 142, 249));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Должности сотрудника");

        jTableAllStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "№", "Фамилия", "Имя", "День рожд.", "Должность", "Телефон", "Адрес"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAllStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableAllStaffMousePressed(evt);
            }
        });
        jScrollPane22.setViewportView(jTableAllStaff);

        jCheckBoxSearch.setText("Поиск по должности");
        jCheckBoxSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxSearchItemStateChanged(evt);
            }
        });

        jComboBoxStaffPostSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxStaffPostSearch.setVisible(false);
        jComboBoxStaffPostSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxStaffPostSearchItemStateChanged(evt);
            }
        });
        jComboBoxStaffPostSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxStaffPostSearchFocusGained(evt);
            }
        });
        jComboBoxStaffPostSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxStaffPostSearchMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jDialogAllStaffLayout = new javax.swing.GroupLayout(jDialogAllStaff.getContentPane());
        jDialogAllStaff.getContentPane().setLayout(jDialogAllStaffLayout);
        jDialogAllStaffLayout.setHorizontalGroup(
            jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane22)
                        .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                            .addComponent(jButtonOpenAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonOpenEditStaff)
                            .addGap(238, 238, 238)
                            .addComponent(jButtonDelOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                        .addComponent(jCheckBoxSearch)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxStaffPostSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAllStaffLayout.createSequentialGroup()
                        .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                                .addComponent(jButtonOpenAddStaff1)
                                .addGap(15, 15, 15)
                                .addComponent(jButtonDelOrder2))
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAllStaffLayout.createSequentialGroup()
                        .addComponent(jButtonOpenAddStaff2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jDialogAllStaffLayout.setVerticalGroup(
            jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonOpenAddStaff1)
                            .addComponent(jButtonDelOrder2)))
                    .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                        .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonOpenEditStaff)
                            .addComponent(jButtonOpenAddStaff))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDialogAllStaffLayout.createSequentialGroup()
                        .addComponent(jButtonDelOrder1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(jDialogAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonOpenAddStaff2)
                            .addComponent(jCheckBoxSearch)
                            .addComponent(jComboBoxStaffPostSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jDialogAllClients.setTitle("Список клиентов");
        jDialogAllClients.setMinimumSize(new java.awt.Dimension(580, 330));

        jTableAllClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "№", "Фамилия", "Имя", "Телефон", "Адрес"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane21.setViewportView(jTableAllClients);

        jButtonOpenAddClient.setBackground(new java.awt.Color(52, 142, 249));
        jButtonOpenAddClient.setForeground(new java.awt.Color(255, 255, 255));
        jButtonOpenAddClient.setText("Добавить");
        jButtonOpenAddClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenAddClientActionPerformed(evt);
            }
        });

        jButtonOpenEdiClient.setBackground(new java.awt.Color(52, 142, 249));
        jButtonOpenEdiClient.setForeground(new java.awt.Color(255, 255, 255));
        jButtonOpenEdiClient.setText("Редактировать");
        jButtonOpenEdiClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenEdiClientActionPerformed(evt);
            }
        });

        jButtonDelOrder3.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelOrder3.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelOrder3.setText("Удалить");
        jButtonDelOrder3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrder3ActionPerformed(evt);
            }
        });

        jButtonApplyClients.setText("ОК");
        jButtonApplyClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApplyClientsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogAllClientsLayout = new javax.swing.GroupLayout(jDialogAllClients.getContentPane());
        jDialogAllClients.getContentPane().setLayout(jDialogAllClientsLayout);
        jDialogAllClientsLayout.setHorizontalGroup(
            jDialogAllClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAllClientsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAllClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonApplyClients, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialogAllClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAllClientsLayout.createSequentialGroup()
                            .addComponent(jButtonOpenAddClient, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonOpenEdiClient)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonDelOrder3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogAllClientsLayout.setVerticalGroup(
            jDialogAllClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAllClientsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialogAllClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelOrder3)
                    .addComponent(jButtonOpenEdiClient)
                    .addComponent(jButtonOpenAddClient))
                .addGap(31, 31, 31)
                .addComponent(jButtonApplyClients)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogNewOrderService.setTitle("Добавить услугу");
        jDialogNewOrderService.setMinimumSize(new java.awt.Dimension(374, 185));
        jDialogNewOrderService.setResizable(false);

        jLabel22.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Услуга");

        jComboBoxServices.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jComboBoxServices.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Сотрудник");

        jButtonClientAdd3.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonClientAdd3.setText("ОК");
        jButtonClientAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientAdd3ActionPerformed(evt);
            }
        });

        jButtonCancel3.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonCancel3.setText("Отмена");
        jButtonCancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel3ActionPerformed(evt);
            }
        });

        jComboBoxStaff.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        javax.swing.GroupLayout jDialogNewOrderServiceLayout = new javax.swing.GroupLayout(jDialogNewOrderService.getContentPane());
        jDialogNewOrderService.getContentPane().setLayout(jDialogNewOrderServiceLayout);
        jDialogNewOrderServiceLayout.setHorizontalGroup(
            jDialogNewOrderServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNewOrderServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogNewOrderServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewOrderServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxServices, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDialogNewOrderServiceLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonClientAdd3)
                .addGap(72, 72, 72)
                .addComponent(jButtonCancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jDialogNewOrderServiceLayout.setVerticalGroup(
            jDialogNewOrderServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNewOrderServiceLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jDialogNewOrderServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxServices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewOrderServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBoxStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewOrderServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClientAdd3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        jDialogAddPost.setTitle("Добавить должность");
        jDialogAddPost.setMinimumSize(new java.awt.Dimension(280, 150));
        jDialogAddPost.setResizable(false);

        jLabel25.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Новая должность:");

        jTextFieldAddPost.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jButtonAddPost.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButtonAddPost.setText("ОК");
        jButtonAddPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPostActionPerformed(evt);
            }
        });

        jButtonCancel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButtonCancel4.setText("Отмена");
        jButtonCancel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel4ActionPerformed(evt);
            }
        });

        jLabelError.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError.setText(" Поле должно быть заполнено");

        javax.swing.GroupLayout jDialogAddPostLayout = new javax.swing.GroupLayout(jDialogAddPost.getContentPane());
        jDialogAddPost.getContentPane().setLayout(jDialogAddPostLayout);
        jDialogAddPostLayout.setHorizontalGroup(
            jDialogAddPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddPostLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAddPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAddPostLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAddPost)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancel4))
                    .addGroup(jDialogAddPostLayout.createSequentialGroup()
                        .addGroup(jDialogAddPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAddPost, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelError)
                            .addComponent(jLabel25))
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialogAddPostLayout.setVerticalGroup(
            jDialogAddPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddPostLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAddPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogAddPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jDialogAddMaterial.setTitle("Добавить материал");
        jDialogAddMaterial.setMinimumSize(new java.awt.Dimension(280, 150));
        jDialogAddMaterial.setResizable(false);

        jLabel26.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Новый материал:");

        jTextFieldAddMaterial.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jButtonAddMaterial1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButtonAddMaterial1.setText("ОК");
        jButtonAddMaterial1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMaterial1ActionPerformed(evt);
            }
        });

        jButtonCancel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButtonCancel5.setText("Отмена");
        jButtonCancel5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel5ActionPerformed(evt);
            }
        });

        jLabelError1.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError1.setText(" Поле должно быть заполнено");

        javax.swing.GroupLayout jDialogAddMaterialLayout = new javax.swing.GroupLayout(jDialogAddMaterial.getContentPane());
        jDialogAddMaterial.getContentPane().setLayout(jDialogAddMaterialLayout);
        jDialogAddMaterialLayout.setHorizontalGroup(
            jDialogAddMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddMaterialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAddMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAddMaterialLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAddMaterial1)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancel5))
                    .addGroup(jDialogAddMaterialLayout.createSequentialGroup()
                        .addGroup(jDialogAddMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelError1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialogAddMaterialLayout.setVerticalGroup(
            jDialogAddMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddMaterialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabelError1)
                .addGap(1, 1, 1)
                .addGroup(jDialogAddMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddMaterial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jDialogAddService.setTitle("Добавить услугу");
        jDialogAddService.setMinimumSize(new java.awt.Dimension(280, 160));
        jDialogAddService.setResizable(false);

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Новая услуга:");

        jTextFieldServicePrice.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jButtonAddService1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButtonAddService1.setText("ОК");
        jButtonAddService1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddService1ActionPerformed(evt);
            }
        });

        jButtonCancel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButtonCancel6.setText("Отмена");
        jButtonCancel6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel6ActionPerformed(evt);
            }
        });

        jLabelError2.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError2.setText(" Поля должны быть заполнены");

        jTextFieldAddService1.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Цена:");

        javax.swing.GroupLayout jDialogAddServiceLayout = new javax.swing.GroupLayout(jDialogAddService.getContentPane());
        jDialogAddService.getContentPane().setLayout(jDialogAddServiceLayout);
        jDialogAddServiceLayout.setHorizontalGroup(
            jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAddServiceLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAddService1)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancel6))
                    .addGroup(jDialogAddServiceLayout.createSequentialGroup()
                        .addGroup(jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelError2)
                            .addGroup(jDialogAddServiceLayout.createSequentialGroup()
                                .addGroup(jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldAddService1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldServicePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialogAddServiceLayout.setVerticalGroup(
            jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAddService1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldServicePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelError2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogAddServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddService1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jDialogAddStatus.setTitle("Добавить статус");
        jDialogAddStatus.setMinimumSize(new java.awt.Dimension(280, 150));
        jDialogAddStatus.setResizable(false);

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Новый статус:");

        jTextFieldAddStatus.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jButtonAddStatus1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButtonAddStatus1.setText("ОК");
        jButtonAddStatus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddStatus1ActionPerformed(evt);
            }
        });

        jButtonCancel8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButtonCancel8.setText("Отмена");
        jButtonCancel8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel8ActionPerformed(evt);
            }
        });

        jLabelError4.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError4.setText(" Поле должно быть заполнено");

        javax.swing.GroupLayout jDialogAddStatusLayout = new javax.swing.GroupLayout(jDialogAddStatus.getContentPane());
        jDialogAddStatus.getContentPane().setLayout(jDialogAddStatusLayout);
        jDialogAddStatusLayout.setHorizontalGroup(
            jDialogAddStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAddStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAddStatusLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAddStatus1)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancel8))
                    .addGroup(jDialogAddStatusLayout.createSequentialGroup()
                        .addGroup(jDialogAddStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAddStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelError4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialogAddStatusLayout.setVerticalGroup(
            jDialogAddStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAddStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabelError4)
                .addGap(1, 1, 1)
                .addGroup(jDialogAddStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddStatus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jDialogNewOrder.setTitle("Новый заказ");
        jDialogNewOrder.setMinimumSize(new java.awt.Dimension(380, 250));
        jDialogNewOrder.setResizable(false);

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Описание");

        jTextFieldOrdDesc.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Клиент");

        jComboBoxClients.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jComboBoxClients.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxClientsItemStateChanged(evt);
            }
        });

        jButtonOrderAdd.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonOrderAdd.setText("ОК");
        jButtonOrderAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrderAddActionPerformed(evt);
            }
        });

        jButtonCancel2.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonCancel2.setText("Отмена");
        jButtonCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel2ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Статус");

        jComboBoxStats.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jComboBoxStats.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxStatsItemStateChanged(evt);
            }
        });

        jTextFieldOrdDate.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabelError6.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError6.setText(" Поля должны быть заполнены");
        jLabelError6.setVisible(false);

        javax.swing.GroupLayout jDialogNewOrderLayout = new javax.swing.GroupLayout(jDialogNewOrder.getContentPane());
        jDialogNewOrder.getContentPane().setLayout(jDialogNewOrderLayout);
        jDialogNewOrderLayout.setHorizontalGroup(
            jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNewOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialogNewOrderLayout.createSequentialGroup()
                        .addGroup(jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldOrdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxClients, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialogNewOrderLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialogNewOrderLayout.createSequentialGroup()
                                .addComponent(jButtonOrderAdd)
                                .addGap(52, 52, 52)
                                .addComponent(jButtonCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialogNewOrderLayout.createSequentialGroup()
                                .addComponent(jComboBoxStats, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldOrdDate, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelError6))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jDialogNewOrderLayout.setVerticalGroup(
            jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNewOrderLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldOrdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jComboBoxClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jComboBoxStats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldOrdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelError6)
                .addGap(5, 5, 5)
                .addGroup(jDialogNewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOrderAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jDialogAddSheet.setTitle("Новый лист");
        jDialogAddSheet.setLocationByPlatform(true);
        jDialogAddSheet.setMaximumSize(new java.awt.Dimension(300, 363));
        jDialogAddSheet.setMinimumSize(new java.awt.Dimension(300, 363));
        jDialogAddSheet.setResizable(false);

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Длина");

        jTextFieldLength.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Материал");

        jComboBoxMaterial.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jComboBoxMaterial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMaterialItemStateChanged(evt);
            }
        });

        jLabelError5.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError5.setText("Поля должны быть заполнены");
        jLabelError5.setVisible(false);

        jButtonSheetAdd.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonSheetAdd.setText("ОК");
        jButtonSheetAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSheetAddActionPerformed(evt);
            }
        });

        jButtonCancel7.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonCancel7.setText("Отмена");
        jButtonCancel7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel7ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Ширина");

        jTextFieldWidth.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jTextFieldWidth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldWidthActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Цена");

        jTextFieldPrice.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jTextFieldPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPriceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogAddSheetLayout = new javax.swing.GroupLayout(jDialogAddSheet.getContentPane());
        jDialogAddSheet.getContentPane().setLayout(jDialogAddSheetLayout);
        jDialogAddSheetLayout.setHorizontalGroup(
            jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddSheetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialogAddSheetLayout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldLength, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogAddSheetLayout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogAddSheetLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelError5)
                            .addComponent(jComboBoxMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialogAddSheetLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSheetAdd)
                        .addGap(49, 49, 49)
                        .addComponent(jButtonCancel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogAddSheetLayout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jDialogAddSheetLayout.setVerticalGroup(
            jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddSheetLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addGroup(jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18)
                .addGroup(jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(30, 30, 30)
                .addComponent(jLabelError5)
                .addGap(18, 18, 18)
                .addGroup(jDialogAddSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSheetAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(75, 75, 75))
        );

        jDialogAddOrdStatus.setTitle("Новый статус");
        jDialogAddOrdStatus.setMinimumSize(new java.awt.Dimension(300, 220));
        jDialogAddOrdStatus.setResizable(false);

        jLabel36.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Дата");

        jTextFieldStatDate.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Статус");

        jComboBoxStatuses.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jComboBoxStatuses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxStatusesItemStateChanged(evt);
            }
        });

        jButtonStatAdd.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonStatAdd.setText("ОК");
        jButtonStatAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStatAddActionPerformed(evt);
            }
        });

        jLabelError7.setForeground(new java.awt.Color(204, 0, 0));
        jLabelError7.setText("Поля должны быть заполнены");
        jLabelError7.setVisible(false);

        jButtonCancel9.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonCancel9.setText("Отмена");
        jButtonCancel9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogAddOrdStatusLayout = new javax.swing.GroupLayout(jDialogAddOrdStatus.getContentPane());
        jDialogAddOrdStatus.getContentPane().setLayout(jDialogAddOrdStatusLayout);
        jDialogAddOrdStatusLayout.setHorizontalGroup(
            jDialogAddOrdStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAddOrdStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAddOrdStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jDialogAddOrdStatusLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldStatDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogAddOrdStatusLayout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxStatuses, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(jDialogAddOrdStatusLayout.createSequentialGroup()
                .addGroup(jDialogAddOrdStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogAddOrdStatusLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabelError7))
                    .addGroup(jDialogAddOrdStatusLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButtonStatAdd)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonCancel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialogAddOrdStatusLayout.setVerticalGroup(
            jDialogAddOrdStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogAddOrdStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAddOrdStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStatDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(jDialogAddOrdStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jComboBoxStatuses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelError7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialogAddOrdStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStatAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );

        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ManagerCut");
        setBackground(new java.awt.Color(212, 219, 230));
        setResizable(false);

        jTsbbedPaneOrders.setBackground(new java.awt.Color(188, 204, 224));

        jScrollPane11.setBackground(new java.awt.Color(255, 255, 255));

        jTableOrders.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "№", "Заказ", "Клиент", "Статус"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableOrdersMousePressed(evt);
            }
        });
        jScrollPane11.setViewportView(jTableOrders);

        jButtonAddOrder.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddOrder.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddOrder.setText("Добавить");
        jButtonAddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddOrderActionPerformed(evt);
            }
        });

        jButtonEditOrder.setBackground(new java.awt.Color(52, 142, 249));
        jButtonEditOrder.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditOrder.setText("Редактировать");
        jButtonEditOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditOrderActionPerformed(evt);
            }
        });

        jButtonDelOrder.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelOrder.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelOrder.setText("Удалить");
        jButtonDelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrderActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(52, 142, 249));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Список заказов");

        jTableOrdMat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Назв.", "Размер", "Цена"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(jTableOrdMat);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 142, 249));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Статусы заказа");

        jButtonAddStatToOrd.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddStatToOrd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddStatToOrd.setText("Добавить");
        jButtonAddStatToOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddStatToOrdActionPerformed(evt);
            }
        });

        jButtonDelStatOrd.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelStatOrd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelStatOrd.setText("Удалить");
        jButtonDelStatOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelStatOrdActionPerformed(evt);
            }
        });

        jTableOrdService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "№", "Услуга", "Сотрудник", "Цена"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(jTableOrdService);

        jButtonAddOrdServ.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddOrdServ.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddOrdServ.setText("Добавить");
        jButtonAddOrdServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddOrdServActionPerformed(evt);
            }
        });

        jButtonDelOrdServ.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelOrdServ.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelOrdServ.setText("Удалить");
        jButtonDelOrdServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrdServActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(52, 142, 249));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Список услуг");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(52, 142, 249));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Список материалов");

        jTableOrdStats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Дата", "Статус"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(jTableOrdStats);

        jButtonAddOrdMat.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddOrdMat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddOrdMat.setText("Добавить");
        jButtonAddOrdMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddOrdMatActionPerformed(evt);
            }
        });

        jButtonDelOrdMat.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDelOrdMat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelOrdMat.setText("Удалить");
        jButtonDelOrdMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrdMatActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(52, 142, 249));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("Фильтры:");

        jRadioButton2.setText("Клиент");
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        jRadioButton3.setText("Статус");
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton3ItemStateChanged(evt);
            }
        });
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Дата");
        jRadioButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton4ItemStateChanged(evt);
            }
        });

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Без фильтра");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jLabelFilter1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFilter1.setForeground(new java.awt.Color(52, 142, 249));
        jLabelFilter1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelFilter1.setText("С:");

        jLabelFilter2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFilter2.setForeground(new java.awt.Color(52, 142, 249));
        jLabelFilter2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFilter2.setText("До:");

        jComboBoxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelFilter3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelFilter3.setForeground(new java.awt.Color(52, 142, 249));
        jLabelFilter3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelFilter3.setText("С:");

        jButtonFilterSearch.setText("Поиск");
        jButtonFilterSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFilterSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jButtonAddOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonEditOrder)
                            .addGap(427, 427, 427))
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonDelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton2)
                                            .addComponent(jRadioButton1))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButton3)
                                            .addComponent(jRadioButton4)
                                            .addComponent(jLabelFilter3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxFilter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jButtonFilterSearch)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextFieldFilter1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addGap(43, 43, 43)
                                                        .addComponent(jLabelFilter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jLabelFilter2)))
                                                .addGap(10, 10, 10)
                                                .addComponent(jTextFieldFilter2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(45, 45, 45))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonAddOrdMat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonDelOrdMat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonAddOrdServ, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDelOrdServ, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonAddStatToOrd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDelStatOrd))
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAddOrder)
                            .addComponent(jButtonEditOrder)
                            .addComponent(jButtonDelOrder))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonDelOrdServ)
                                        .addComponent(jButtonAddStatToOrd)
                                        .addComponent(jButtonDelStatOrd)
                                        .addComponent(jButtonAddOrdServ))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonDelOrdMat)
                                        .addComponent(jButtonAddOrdMat))))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 84, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelFilter1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFilter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelFilter2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFilter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFilter3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonFilterSearch)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTsbbedPaneOrders.addTab("Заказы", jPanel4);

        jPanel1.setBackground(new java.awt.Color(212, 219, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Модули заказа");

        jButton1.setBackground(new java.awt.Color(52, 142, 249));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Добавить");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(52, 142, 249));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Удалить");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTableOrdSheets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Название", "Размеры", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableOrdSheets.setGridColor(new java.awt.Color(33, 33, 33));
        jScrollPane3.setViewportView(jTableOrdSheets);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Детали");

        jButtonAddDetail.setBackground(new java.awt.Color(52, 142, 249));
        jButtonAddDetail.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddDetail.setText("Добавить");
        jButtonAddDetail.setEnabled(false);
        jButtonAddDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDetailActionPerformed(evt);
            }
        });

        jButtonDeleteDetail.setBackground(new java.awt.Color(52, 142, 249));
        jButtonDeleteDetail.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDeleteDetail.setText("Удалить");
        jButtonDeleteDetail.setEnabled(false);
        jButtonDeleteDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteDetailActionPerformed(evt);
            }
        });

        jButtonCreateSheets.setBackground(new java.awt.Color(52, 142, 249));
        jButtonCreateSheets.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCreateSheets.setText("Раскроить!");
        jButtonCreateSheets.setEnabled(false);
        jButtonCreateSheets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateSheetsActionPerformed(evt);
            }
        });

        jTabbedPane2.setBackground(new java.awt.Color(245, 245, 255));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "№ детали", "Длина", "Ширина", "Кол-ство", "Вращение", "Комментарий", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable3.setGridColor(new java.awt.Color(33, 33, 33));
        jTable3.setSelectionBackground(new java.awt.Color(52, 177, 255));
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.getModel().addTableModelListener(
            new TableModelListener(){
                @Override
                public void tableChanged(TableModelEvent evt){
                    {
                        updateDetail((DefaultTableModel)jTable3.getModel(), evt.getFirstRow());
                    }
                }});
                jTable3.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        jTable3FocusGained(evt);
                    }
                });
                jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        jTable3MousePressed(evt);
                    }
                });
                jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        jTable3KeyPressed(evt);
                    }
                });
                jScrollPane2.setViewportView(jTable3);

                jTabbedPane2.addTab("", jScrollPane2);

                jTable4.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "№ детали", "Длина", "Ширина", "Кол-ство", "Вращение", "Комментарий", "id"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, true, true, true, true, true, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jTable4.setGridColor(new java.awt.Color(33, 33, 33));
                jTable4.setSelectionBackground(new java.awt.Color(52, 177, 255));
                jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                //jTable3.setModel(new DetailTable());
                jTable4.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        jTable4KeyPressed(evt);
                    }
                });
                jScrollPane4.setViewportView(jTable4);

                jTabbedPane2.addTab("", jScrollPane4);

                jTable5.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "№ детали", "Длина", "Ширина", "Кол-ство", "Вращение", "Комментарий", "id"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Object.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, true, true, true, true, true, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jTable5.setGridColor(new java.awt.Color(33, 33, 33));
                jTable5.setSelectionBackground(new java.awt.Color(52, 177, 255));
                jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                //jTable3.setModel(new DetailTable());
                jTable5.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        jTable5KeyPressed(evt);
                    }
                });
                jScrollPane5.setViewportView(jTable5);

                jTabbedPane2.addTab("", jScrollPane5);

                jTable6.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "№ детали", "Длина", "Ширина", "Кол-ство", "Вращение", "Комментарий", "id"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, true, true, true, true, true, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jTable6.setGridColor(new java.awt.Color(33, 33, 33));
                jTable6.setSelectionBackground(new java.awt.Color(52, 177, 255));
                jTable6.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                //jTable3.setModel(new DetailTable());
                jScrollPane6.setViewportView(jTable6);

                jTabbedPane2.addTab("", jScrollPane6);

                jTable7.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                    },
                    new String [] {
                        "№ детали", "Длина", "Ширина", "Кол-ство", "Вращение", "Комментарий", "id"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, true, true, true, true, true, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jTable7.setGridColor(new java.awt.Color(33, 33, 33));
                jTable7.setSelectionBackground(new java.awt.Color(52, 177, 255));
                jTable7.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                //jTable3.setModel(new DetailTable());
                jScrollPane7.setViewportView(jTable7);

                jTabbedPane2.addTab("", jScrollPane7);

                jTable8.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                    },
                    new String [] {
                        "№ детали", "Длина", "Ширина", "Кол-ство", "Вращение", "Комментарий", "id"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, true, true, true, true, true, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jTable8.setGridColor(new java.awt.Color(33, 33, 33));
                jTable8.setSelectionBackground(new java.awt.Color(52, 177, 255));
                jTable8.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                //jTable3.setModel(new DetailTable());
                jScrollPane8.setViewportView(jTable8);

                jTabbedPane2.addTab("", jScrollPane8);

                jTable10.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                    },
                    new String [] {
                        "№ детали", "Длина", "Ширина", "Кол-ство", "Вращение", "Комментарий", "id"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, true, true, true, true, true, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                jTable10.setGridColor(new java.awt.Color(33, 33, 33));
                jTable10.setSelectionBackground(new java.awt.Color(52, 177, 255));
                jTable10.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                //jTable3.setModel(new DetailTable());
                jScrollPane10.setViewportView(jTable10);

                jTabbedPane2.addTab("", jScrollPane10);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonAddDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDeleteDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonCreateSheets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAddDetail)
                            .addComponent(jButtonDeleteDetail))
                        .addGap(29, 29, 29)
                        .addComponent(jButtonCreateSheets, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                );

                jTsbbedPaneOrders.addTab("Детали заказа", jPanel1);

                javax.swing.GroupLayout jSheetPaintPanel2Layout = new javax.swing.GroupLayout(jSheetPaintPanel2);
                jSheetPaintPanel2.setLayout(jSheetPaintPanel2Layout);
                jSheetPaintPanel2Layout.setHorizontalGroup(
                    jSheetPaintPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                );
                jSheetPaintPanel2Layout.setVerticalGroup(
                    jSheetPaintPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 526, Short.MAX_VALUE)
                );

                jButton18.setText("->");
                jButton18.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton18ActionPerformed(evt);
                    }
                });

                jButton17.setText("<-");
                jButton17.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton17ActionPerformed(evt);
                    }
                });

                jLabel7.setText("Лист №");

                jButton20.setText("Сохранить");
                jButton20.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton20ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSheetPaintPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(277, 277, 277)
                                .addComponent(jButton17)
                                .addGap(113, 113, 113)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(jButton18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                                .addComponent(jButton20)))
                        .addContainerGap())
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSheetPaintPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton18)
                            .addComponent(jButton17)
                            .addComponent(jLabel7)
                            .addComponent(jButton20))
                        .addGap(14, 14, 14))
                );

                jTsbbedPaneOrders.addTab("Карты раскроя", jPanel2);

                jMenu3.setText("Фирма");

                jMenuItemStaff.setText("Сотрудниики");
                jMenuItemStaff.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItemStaffActionPerformed(evt);
                    }
                });
                jMenu3.add(jMenuItemStaff);

                jMenuItemClient.setText("Клиенты");
                jMenuItemClient.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItemClientActionPerformed(evt);
                    }
                });
                jMenu3.add(jMenuItemClient);

                jMenuBar1.add(jMenu3);

                jMenu1.setText("Заказ");

                jMenuItem1.setText("Открыть");
                jMenu1.add(jMenuItem1);

                jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
                jMenuItem2.setText("Сохранить");
                jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItem2ActionPerformed(evt);
                    }
                });
                jMenu1.add(jMenuItem2);

                jMenuBar1.add(jMenu1);

                jMenu2.setText("Настройки");

                jMenuItem3.setText("База информации");
                jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItem3ActionPerformed(evt);
                    }
                });
                jMenu2.add(jMenuItem3);

                jMenuBar1.add(jMenu2);

                setJMenuBar(jMenuBar1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTsbbedPaneOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(19, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTsbbedPaneOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pack();
                setLocationRelativeTo(null);
            }// </editor-fold>//GEN-END:initComponents
    
    private DefaultTableModel getTableDetModel(){
        DefaultTableModel model;
        
        switch (jTabbedPane2.getSelectedIndex()){
            case 0:
                model = (DefaultTableModel) jTable3.getModel();
                detailLists[0].incCountOfinserted();
                break;
            case 1:
                model = (DefaultTableModel) jTable4.getModel();
                detailLists[1].incCountOfinserted();
                break;
            case 2:
                model = (DefaultTableModel) jTable5.getModel();
                detailLists[2].incCountOfinserted();
                break;
            case 3:
                model = (DefaultTableModel) jTable6.getModel();
                detailLists[3].incCountOfinserted();
                break;
            case 4:
                model = (DefaultTableModel) jTable7.getModel();
                detailLists[4].incCountOfinserted();
                break;
            case 5:
                model = (DefaultTableModel) jTable8.getModel();
                detailLists[5].incCountOfinserted();
                break;
            default:
                model = (DefaultTableModel) jTableSheet.getModel();
                detailLists[6].incCountOfinserted();
                break;
        }
        
        return model;
    }
    
    private void jButtonAddDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDetailActionPerformed
        if (jTableOrders.getSelectedRow() > -1 && jTableOrdSheets.getRowCount() > 0)
        {
            DefaultTableModel modelDets = getTableDetModel();

            if (modelDets.getRowCount() == 0 || modelDets.getValueAt(modelDets.getRowCount()-1, 6) != null)
                modelDets.addRow(new Object[]{modelDets.getRowCount()+1, null, null, null, false, null, null});

            else{
                SQLiteManager man = new SQLiteManager();
                DefaultTableModel modelSheets = (DefaultTableModel)jTableOrdSheets.getModel();

                int id_sheets = Integer.parseInt(modelSheets.getValueAt(jTabbedPane2.getSelectedIndex(), 2).toString());
                int length = Integer.parseInt(modelDets.getValueAt(modelDets.getRowCount()-1, 1).toString());
                int width = Integer.parseInt(modelDets.getValueAt(modelDets.getRowCount()-1, 2).toString());
                int cnt = Integer.parseInt(modelDets.getValueAt(modelDets.getRowCount()-1, 3).toString());
                boolean rot = Boolean.parseBoolean(modelDets.getValueAt(modelDets.getRowCount()-1, 4).toString());

                try {
                    man.insertIntoDetail(-1, id_sheets, width, length, cnt, rot);
                } catch (SQLException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                ButtonsHandler.refreshTableDetails(modelDets, id_order, jTabbedPane2.getSelectedIndex());
                modelDets.addRow(new Object[]{modelDets.getRowCount()+1, null, null, null, false, null, null});
            }
        }
    }//GEN-LAST:event_jButtonAddDetailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTableOrders.getSelectedRow() > -1){
        try {
            ComboBoxHandler.refresMaterialComboBox(jComboBoxMaterial);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jDialogAddSheet.setLocationRelativeTo(null);
        jDialogAddSheet.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonDeleteDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteDetailActionPerformed
        DefaultTableModel model;
        JTable table;
        int i;
        
         switch (jTabbedPane2.getSelectedIndex()){
            case 0:
                model = (DefaultTableModel) jTable3.getModel();
                table = jTable3;
                i = 0;
                break;
            case 1:
                model = (DefaultTableModel) jTable4.getModel();
                table = jTable4;
                i = 1;
                break;
            case 2:
                model = (DefaultTableModel) jTable5.getModel();
                table = jTable5;
                i = 2;
                break;
            case 3:
                model = (DefaultTableModel) jTable6.getModel();
                table = jTable6;
                i = 3;
                break;
            case 4:
                model = (DefaultTableModel) jTable7.getModel();
                table = jTable7;
                i = 4;
                break;
            case 5:
                model = (DefaultTableModel) jTable8.getModel();
                table = jTable8;
                i = 5;
                break;
            default:
                model = (DefaultTableModel) jTable10.getModel();
                table = jTable10;
                i = 6;
                break;
        }
        
        if (table.getSelectedRow() < table.getRowCount()-1){
            
            Object lengthLast = table.getValueAt(table.getRowCount()-1, 1);
            Object widthLast = table.getValueAt(table.getRowCount()-1, 2);
            Object cntLast = table.getValueAt(table.getRowCount()-1, 3);
            Object rotLast = table.getValueAt(table.getRowCount()-1, 4);
            
            SQLiteManager man = new SQLiteManager();
            int id = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 6).toString());

            try {
                man.deleteDetail(id);
                ButtonsHandler.refreshTableDetails(model, id_order, jTabbedPane2.getSelectedIndex());
                model.addRow(new Object[]{model.getRowCount()+1, lengthLast, widthLast, cntLast, rotLast, null});
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            model.removeRow(table.getSelectedRow());
    }//GEN-LAST:event_jButtonDeleteDetailActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();; 
        model.addRow(new Object[]{null, model.getRowCount()+1,null, null, null});
        orderLists.incCountOfinserted();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();;
        
        if (model.getValueAt(jTable1.getSelectedRow(), 0) != "" && model.getValueAt(jTable1.getSelectedRow(), 0) != null)
            orderLists.addDeletedList((int)model.getValueAt(jTable1.getSelectedRow(), 0));
        else
            orderLists.setCountOfinserted(orderLists.getCountOfinserted()-1);
        
        model.removeRow(jTable1.getSelectedRow());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableOrdSheets.getModel();;
        int ind = jTableOrdSheets.getSelectedRow();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deleteSheets(Integer.parseInt(model.getValueAt(ind, 2).toString()));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            ButtonsHandler.refreshTableOrderSheets(jTableOrdMat, id_order);
            ButtonsHandler.refreshTableOrderSheets(jTableOrdSheets, id_order);
            jTabbedPane2.setEnabledAt(ind, false);
            jTabbedPane2.setTitleAt(ind, "");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSheet.getModel();; 
        model.addRow(new Object[]{model.getRowCount()+1,null, null, null});
        materialLists.incCountOfinserted();
        cnt++;
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSheet.getModel();;
        
        if (model.getValueAt(jTableSheet.getSelectedRow(), 4) != "" && model.getValueAt(jTableSheet.getSelectedRow(), 4) != null)
            materialLists.addDeletedList((int)model.getValueAt(jTableSheet.getSelectedRow(), 4));
        else
            materialLists.setCountOfinserted(materialLists.getCountOfinserted()-1);
        
        model.removeRow(jTableSheet.getSelectedRow());
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        SQLiteManager man = new SQLiteManager();
        DBLoader load = new DBLoader();
        
        List<Material> matList = new ArrayList();
        Material mat = new Material();
        DefaultTableModel modelMat = (DefaultTableModel) jTableSheet.getModel();
        
        for (int i = 0; i < modelMat.getRowCount(); i++){
            //order.setId((int)jTable1.getValueAt(i, 0));
            mat.setDescription(modelMat.getValueAt(i, 1).toString());
            mat.setWidth((int)modelMat.getValueAt(i, 2));
            mat.setHeight((int)modelMat.getValueAt(i, 3));
            matList.add(mat);
        }
        
        try{
            load.loadMaterialToDB(matList, materialLists.getUpdatedList(), materialLists.getDeletedList(), materialLists.getCountOfinserted());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        materialLists.clear();
        
        int ind = jTableSheet.getSelectedRow();
        
        for(int i = 0; i < modelMat.getRowCount(); i++)
            modelMat.removeRow(i);
        
        jDialog2.setVisible(true);
        jDialog2.setLocationRelativeTo(null);
        
        DefaultTableModel modelOrd = (DefaultTableModel) jTableSheet.getModel();
        /*List<Material> mats;
        try {
            mats = man.selectMaterial();
            for (int i = 0; i < mats.size(); i++){
                modelOrd.addRow(new Object[]{null, null, null, null, null});
                
                modelOrd.setValueAt(i+1, i, 0);
                modelOrd.setValueAt(mats.get(i).getDescription(), i, 1);
                modelOrd.setValueAt(mats.get(i).getWidth(), i, 2);
                modelOrd.setValueAt(mats.get(i).getHeight(), i, 3);
                modelOrd.setValueAt(mats.get(i).getId(), i, 4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        modelOrd.removeRow(modelOrd.getRowCount()-1);
        
        if(ind >= 0){
            DefaultTableModel model = (DefaultTableModel) jTableOrdSheets.getModel();
            DefaultTableModel localMat = (DefaultTableModel) jTableSheet.getModel();
            String name = localMat.getValueAt(ind, 1).toString();
            String size = localMat.getValueAt(ind, 2) + "x" + localMat.getValueAt(ind, 3);
            String id = localMat.getValueAt(ind, 4).toString();
            model.addRow(new Object[]{name, size, id});
            
            jTabbedPane2.setEnabledAt(jTableOrdSheets.getRowCount()-1, true);
            jTabbedPane2.setTitleAt(jTableOrdSheets.getRowCount()-1, name);
            jButtonAddDetail.setEnabled(true);
            jButtonDeleteDetail.setEnabled(true);
            jButtonCreateSheets.setEnabled(true);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        DBLoader load = new DBLoader();
        
        List<Material> matList = new ArrayList();
        Material mat = new Material();
        DefaultTableModel modelMat = (DefaultTableModel) jTableSheet.getModel();
        
        boolean warning = false;
        for (int i = 0; i < modelMat.getRowCount(); i++){
            boolean flag = false;
            for (int j = 1; j < 4; j++)
                if (modelMat.getValueAt(i, j) == null){
                    materialLists.setCountOfinserted(materialLists.getCountOfinserted()-1);
                    flag = true;
                    warning = true;
                    break;
                }
            
            if (flag == false){
                mat.setDescription(modelMat.getValueAt(i, 1).toString());
                mat.setWidth((int)modelMat.getValueAt(i, 2));
                mat.setHeight((int)modelMat.getValueAt(i, 3));
                matList.add(mat);
            }
        }
        
        if (warning == true && askUser == false){
                jDialogEmptyCells.setLocationRelativeTo(null);
                jDialogEmptyCells.setVisible(true);
            }
        else if (askUser == true || warning == false){
            try{
                load.loadMaterialToDB(matList, materialLists.getUpdatedList(), materialLists.getDeletedList(), materialLists.getCountOfinserted());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            materialLists.clear();
            jDialog2.setVisible(false);
            askUser = false;
       }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        if (jSheetPaintPanel2.getInd() > 0){
            jSheetPaintPanel2.setInd(jSheetPaintPanel2.getInd()-1);
            jLabel7.setText("Лист №" + jSheetPaintPanel2.getInd());
            jSheetPaintPanel2.repaint();
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        if (jSheetPaintPanel2.getInd() < jSheetPaintPanel2.getSheetSize()-1){
            jSheetPaintPanel2.setInd(jSheetPaintPanel2.getInd()+1);
            jLabel7.setText("Лист №" + jSheetPaintPanel2.getInd());
            jSheetPaintPanel2.repaint();
        }
    }//GEN-LAST:event_jButton18ActionPerformed
    
    public SheetHandler getAllDetails(JTable table, int height, int width){
        SheetHandler sh = new SheetHandler(height, width);
        for (int i = 0; i < table.getRowCount(); i++){
            for(int j = 0; j < (int)table.getValueAt(i, 3); j++)
                sh.getDetailList().add(new Detail((int)table.getValueAt(i, 0), (int)table.getValueAt(i, 1), (int)table.getValueAt(i, 2), (boolean)table.getValueAt(i, 4)));
        }
        
        return sh;
    }
    
    private void jButtonCreateSheetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateSheetsActionPerformed
        ArrayList<SheetHandler> sh = new ArrayList();
        jSheetPaintPanel2.setInd(0);
        saveDetails();
        
        String[] size = new String[2];
        if (jTabbedPane2.isEnabledAt(0)){
            size = jTableOrdSheets.getValueAt(0, 1).toString().split("x");
            sh.add(getAllDetails(jTable3, Integer.parseInt(size[1]), Integer.parseInt(size[0])));
        }
        if (jTabbedPane2.isEnabledAt(1)){
            size = jTableOrdSheets.getValueAt(1, 1).toString().split("x");
            sh.add(getAllDetails(jTable4, Integer.parseInt(size[1]), Integer.parseInt(size[0])));
        }
        if (jTabbedPane2.isEnabledAt(2)){
            size = jTableOrdSheets.getValueAt(2, 1).toString().split("x");
            sh.add(getAllDetails(jTable5, Integer.parseInt(size[1]), Integer.parseInt(size[0])));
        }
        if (jTabbedPane2.isEnabledAt(3)){
            size = jTableOrdSheets.getValueAt(3, 1).toString().split("x");
            sh.add(getAllDetails(jTable6, Integer.parseInt(size[1]), Integer.parseInt(size[0])));
        }
        if (jTabbedPane2.isEnabledAt(4)){
            size = jTableOrdSheets.getValueAt(4, 1).toString().split("x");
            sh.add(getAllDetails(jTable7, Integer.parseInt(size[1]), Integer.parseInt(size[0])));
        }
        if (jTabbedPane2.isEnabledAt(5)){
            size = jTableOrdSheets.getValueAt(5, 1).toString().split("x");
            sh.add(getAllDetails(jTable8, Integer.parseInt(size[1]), Integer.parseInt(size[0])));
        }
        if (jTabbedPane2.isEnabledAt(6)){
            size = jTableOrdSheets.getValueAt(6, 1).toString().split("x");
            sh.add(getAllDetails(jTableSheet, Integer.parseInt(size[1]), Integer.parseInt(size[0])));
        }
        
        if (sh.size() > 0)
        {
            int i = 0;
            ArrayList<Sheet> arrSheet = new ArrayList();
            while(jTabbedPane2.isEnabledAt(i)){
                sh.get(i).distribute();
                arrSheet.addAll(sh.get(i).getSheetList());
                i++;
            }
            
            jLabel7.setText("Лист №" + jSheetPaintPanel2.getInd());
            jSheetPaintPanel2.setSheet(arrSheet);
        }
        else{
            jDialogEmptyDetails.setVisible(true);
            jDialogEmptyDetails.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jButtonCreateSheetsActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        jDialogEmptyDetails.setVisible(false);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void clearTable(JTable table){
        
    }
    
    private void loadOrdersFromTableToDB(){
            DBLoader load = new DBLoader();
            DefaultTableModel modelOrd = (DefaultTableModel) jTable1.getModel();
            List<Orders> ordList = new ArrayList();
            Orders order = new Orders();

            for (int i = 0; i < modelOrd.getRowCount(); i++){
                System.out.println(modelOrd.getValueAt(i, 4));
                if (modelOrd.getValueAt(i, 2) == null)
                    order.setClient("null");
                else
                    order.setClient(modelOrd.getValueAt(i, 2).toString());
                
                System.out.println(i + ": " + modelOrd.getValueAt(i, 3));
                
                if (modelOrd.getValueAt(i, 3) == null || modelOrd.getValueAt(i, 3).equals(""))
                    order.setDateStart("1111-11-11");
                else
                    order.setDateStart(modelOrd.getValueAt(i, 3).toString());
                
                if (modelOrd.getValueAt(i, 4) == null || modelOrd.getValueAt(i, 4).equals(""))
                    order.setDateFinish("1111-11-11");
                else
                    order.setDateFinish(modelOrd.getValueAt(i, 3).toString());
                
                ordList.add(order);
            }
            try{
                load.loadOrdersToDB(ordList, orderLists.getUpdatedList(), orderLists.getDeletedList(), orderLists.getCountOfinserted());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            orderLists.clear();
    }
    
    private void loadOrdersFromDBToTable()
    {
        SQLiteManager man = new SQLiteManager();   
        DefaultTableModel modelOrd = (DefaultTableModel) jTable1.getModel();
        
        for(int i = 0; i < modelOrd.getRowCount(); i++)
            modelOrd.removeRow(i);
            
        modelOrd.removeRow(modelOrd.getRowCount()-1);
            
        List<Orders> orders;
        
            try {
                orders = man.selectOrders();
                for (int i = 0; i < orders.size(); i++){
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.addRow(new Object[]{null, null, null, null, null});

                    model.setValueAt(orders.get(i).getId(), i, 0);
                    model.setValueAt(i+1, i, 1);
                    
                    if (orders.get(i).getClient().equals("null"))
                        model.setValueAt("", i, 2);
                    else
                        model.setValueAt(orders.get(i).getClient(), i, 2);
                    
                    if (new SimpleDateFormat("yyyy-MM-dd").format(orders.get(i).getDateFinish()).equals("1111-11-11"))
                        model.setValueAt("", i, 3);
                    else
                        model.setValueAt(new SimpleDateFormat("yyyy-MM-dd").format(orders.get(i).getDateFinish()), i, 3);
                    
                    if (new SimpleDateFormat("yyyy-MM-dd").format(orders.get(i).getDateFinish()).equals("1111-11-11"))
                        model.setValueAt("", i, 4);
                    else
                        model.setValueAt(new SimpleDateFormat("yyyy-MM-dd").format(orders.get(i).getDateFinish()), i, 4);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    private void cleanTableMaterial(){
        jTableOrdSheets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Название", "Размеры", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    private void cleanTableDetail(JTable table){
            table.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "№ детали", "Длина", "Ширина", "Кол-ство", "Вращение", "Комментарий", "id"
                            }
                        ) {
                            Class[] types = new Class [] {
                                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
                            };
                            boolean[] canEdit = new boolean [] {
                                false, true, true, true, true, false, false
                            };

                            public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                            }

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
    }
    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
            /*int selectedInd = jTable1.getSelectedRow();
            
            loadOrdersFromTableToDB();
            loadOrdersFromDBToTable();
            
            if (selectedInd >= 0){
                jButton15.setEnabled(true);
                    
                SQLiteManager man = new SQLiteManager();
                DefaultTableModel modelOrd = (DefaultTableModel) jTable1.getModel();
                DefaultTableModel modelMat = (DefaultTableModel) jTable2.getModel();
                
                List<Integer> idMat = new ArrayList();
                Material mat;
                id_order = (int)modelOrd.getValueAt(selectedInd, 0);
                
                for(int i = 0; i < modelMat.getRowCount(); i++)
                    modelMat.removeRow(i);
                
                List<Details>[] det = null;
                try{
                    idMat = man.getUniqMat(id_order);
                    
                    for (int i = modelMat.getRowCount(); i < idMat.size(); i++){
                        modelMat.addRow(new Object[]{null,null,null});
                    }

                    for (int i = 0; i < 6; i++){
                        jTabbedPane2.setEnabledAt(i, false);
                        jTabbedPane2.setTitleAt(i, "");
                    }
                    
                    for (int i = 0; i < idMat.size(); i++){
                        mat = man.selectMaterial(idMat.get(i));

                        modelMat.setValueAt(mat.getDescription(), i, 0);
                        modelMat.setValueAt(mat.getWidth()+"x"+mat.getHeight(), i, 1);
                        modelMat.setValueAt(mat.getId(), i, 2);
                        
                        jTabbedPane2.setEnabledAt(i, true);
                        jTabbedPane2.setTitleAt(i, mat.getDescription());

                        det = man.getDet(id_order);
                        DefaultTableModel modelDet;

                        if (i == 0){
                            modelDet = (DefaultTableModel) jTable3.getModel();                     
                        }
                        else if(i == 1){
                            modelDet = (DefaultTableModel) jTable4.getModel();
                        }
                        else if(i == 2){
                            modelDet = (DefaultTableModel) jTable5.getModel();
                        }
                        else if(i == 3){
                            modelDet = (DefaultTableModel) jTable6.getModel();
                        }
                        else if(i == 4){
                            modelDet = (DefaultTableModel) jTable7.getModel();
                        }
                        else if(i == 5){
                            modelDet = (DefaultTableModel) jTable8.getModel();
                        }
                        else{
                            modelDet = (DefaultTableModel) jTable10.getModel();
                        }
                        
                        //очистка таблицы
                        for (int j = 0; j < modelDet.getRowCount(); j++){
                            modelDet.removeRow(j);
                        }
                        
                        //заполнение пустыми строками
                        int cnt = 1;
                        for (int j = modelDet.getRowCount(); j < det[i].size(); j++){
                            modelDet.addRow(new Object[]{cnt, null, null, null, false, null, null});
                            System.out.println(j-modelMat.getRowCount()+2);
                            cnt++;
                        }    
                        
                        for (int j = 0; j < det[i].size(); j++){
                            modelDet.setValueAt(j+1, j, 0);
                            modelDet.setValueAt(det[i].get(j).getWidth(), j, 1);
                            modelDet.setValueAt(det[i].get(j).getHeight(), j, 2);
                            modelDet.setValueAt(det[i].get(j).getCount(), j, 3);
                            System.out.println(j + ": " + det[i].get(j).getRotated());
                            modelDet.setValueAt(det[i].get(j).getRotated(), j, 4);
                            modelDet.setValueAt(det[i].get(j).getId(), j, 6);
                        }
                        
                        /*cleanTable(jTable4);
                        cleanTable(jTable5);
                        cleanTable(jTable6);
                        cleanTable(jTable7);
                        cleanTable(jTable8);
                        cleanTable(jTable10);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
                //System.out.println("size: " + det[0].size());
                if (det == null){
                    cleanTableDetail(jTable3);
                    cleanTableDetail(jTable4);
                    cleanTableDetail(jTable5);
                    cleanTableDetail(jTable6);
                    cleanTableDetail(jTable7);
                    cleanTableDetail(jTable8);
                    cleanTableDetail(jTable10);
                    
                    cleanTableMaterial();
                    
                    jTable2.removeColumn(jTable2.getColumnModel().getColumn(2));
                    jTable3.removeColumn(jTable3.getColumnModel().getColumn(6));
                    jTable4.removeColumn(jTable4.getColumnModel().getColumn(6));
                    jTable5.removeColumn(jTable5.getColumnModel().getColumn(6));
                    jTable6.removeColumn(jTable6.getColumnModel().getColumn(6));
                    jTable7.removeColumn(jTable7.getColumnModel().getColumn(6));
                    jTable8.removeColumn(jTable8.getColumnModel().getColumn(6));
                    jTable10.removeColumn(jTable10.getColumnModel().getColumn(6));
                    
                    jTable3.getColumnModel().getColumn(0).setMaxWidth(100);
                    jTable3.getColumnModel().getColumn(4).setMaxWidth(100);
                    jTable3.getColumnModel().getColumn(5).setMinWidth(200);
                    jTable4.getColumnModel().getColumn(0).setMaxWidth(100);
                    jTable4.getColumnModel().getColumn(4).setMaxWidth(100);
                    jTable4.getColumnModel().getColumn(5).setMinWidth(200);
                    jTable5.getColumnModel().getColumn(0).setMaxWidth(100);
                    jTable5.getColumnModel().getColumn(4).setMaxWidth(100);
                    jTable5.getColumnModel().getColumn(5).setMinWidth(200);
                    jTable6.getColumnModel().getColumn(0).setMaxWidth(100);
                    jTable6.getColumnModel().getColumn(4).setMaxWidth(100);
                    jTable6.getColumnModel().getColumn(5).setMinWidth(200);
                    jTable7.getColumnModel().getColumn(0).setMaxWidth(100);
                    jTable7.getColumnModel().getColumn(4).setMaxWidth(100);
                    jTable7.getColumnModel().getColumn(5).setMinWidth(200);
                    jTable8.getColumnModel().getColumn(0).setMaxWidth(100);
                    jTable8.getColumnModel().getColumn(4).setMaxWidth(100);
                    jTable8.getColumnModel().getColumn(5).setMinWidth(200);
                    jTable10.getColumnModel().getColumn(0).setMaxWidth(100);
                    jTable10.getColumnModel().getColumn(4).setMaxWidth(100);
                    jTable10.getColumnModel().getColumn(5).setMinWidth(200);
                    
                    jButton5.setEnabled(false);
                    jButton6.setEnabled(false);
                    jButton9.setEnabled(false);
                }
                
                if (jTable2.getRowCount() > 0){
                    jButton5.setEnabled(true);
                    jButton6.setEnabled(true);
                    jButton9.setEnabled(true);
                    jButton15.setEnabled(true);
                }
            }
        
        orderLists.clear();
        jDialog1.setVisible(false);*/
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (model.getValueAt(jTable1.getSelectedRow(), 0) != "" && model.getValueAt(jTable1.getSelectedRow(), 0) != null)
            orderLists.addUpdatedList((int)model.getValueAt(jTable1.getSelectedRow(), 0));
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        if (jSheetPaintPanel2.getInd() != -1){
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.JPEG", "*.*");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                jSheetPaintPanel2.saveImage(fc.getSelectedFile().toString()/*+(String)jTable2.getValueAt(jTabbedPane2.getSelectedIndex(),0) + (jSheetPaintPanel2.getInd()+1)*/, "jpeg");
            }
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTableSheetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableSheetKeyPressed
        DefaultTableModel model = (DefaultTableModel) jTableSheet.getModel();
        if (model.getValueAt(jTableSheet.getSelectedRow(), 4) != "" && model.getValueAt(jTable1.getSelectedRow(), 4) != null)
            orderLists.addUpdatedList((int)model.getValueAt(jTableSheet.getSelectedRow(), 4));
    }//GEN-LAST:event_jTableSheetKeyPressed
    
    private void saveDetails(){
        SQLiteManager man = new SQLiteManager();
        DBLoader load = new DBLoader();
        
        List<Details> detList;
        Details det = new Details();
        DefaultTableModel modelMat = (DefaultTableModel) jTableOrdSheets.getModel();
        
        det.setId_orders(id_order);
        if (jTabbedPane2.isEnabledAt(0)){
            detList = new ArrayList();
            DefaultTableModel modelDet = (DefaultTableModel) jTable3.getModel();
            det.setId_material(Integer.parseInt(modelMat.getValueAt(0, 2).toString()));
            
            boolean warning = false;
            for (int i = 0; i < modelDet.getRowCount(); i++){
                boolean flag = false;
                for (int j = 1; j < 4; j++)
                    if (modelDet.getValueAt(i, j) == null){
                        materialLists.setCountOfinserted(materialLists.getCountOfinserted()-1);
                        flag = true;
                        warning = true;
                        break;
                    }
                if (flag == false){
                    det.setWidth((Integer)modelDet.getValueAt(i, 1));
                    det.setHeight((Integer)modelDet.getValueAt(i, 2));
                    det.setCount((Integer)modelDet.getValueAt(i, 3));
                    det.setRotated((Boolean)modelDet.getValueAt(i, 4));
                    detList.add(det);
                }
            }
            
            if (warning == true && askUser == false){
                jDialogEmptyCells.setLocationRelativeTo(null);
                jDialogEmptyCells.setVisible(true);
            }
            else if (askUser == true || warning == false){
                try{
                    load.loadDetailToDB(detList, detailLists[0].getUpdatedList(), detailLists[0].getDeletedList(), detailLists[0].getCountOfinserted());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                detailLists[0].clear();
                jDialog2.setVisible(false);
            }
        }
        if (jTabbedPane2.isEnabledAt(1)){
            detList = new ArrayList();
            DefaultTableModel modelDet = (DefaultTableModel) jTable4.getModel();
            det.setId_material(Integer.parseInt(modelMat.getValueAt(1, 2).toString()));
            
            boolean warning = false;
            for (int i = 0; i < modelDet.getRowCount(); i++){
                boolean flag = false;
                for (int j = 1; j < 4; j++)
                    if (modelDet.getValueAt(i, j) == null){
                        materialLists.setCountOfinserted(materialLists.getCountOfinserted()-1);
                        flag = true;
                        warning = true;
                        break;
                    }
                if (flag == false){
                    det.setWidth((int)modelDet.getValueAt(i, 1));
                    det.setHeight((int)modelDet.getValueAt(i, 2));
                    det.setCount((int)modelDet.getValueAt(i, 3));
                    det.setRotated((boolean)modelDet.getValueAt(i, 4));
                    detList.add(det);
                }
            }
            
            if (askUser == true || warning == false){
                try{
                    load.loadDetailToDB(detList, detailLists[1].getUpdatedList(), detailLists[1].getDeletedList(), detailLists[1].getCountOfinserted());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                detailLists[1].clear();
            }
        }
        if (jTabbedPane2.isEnabledAt(2)){
            detList = new ArrayList();
            DefaultTableModel modelDet = (DefaultTableModel) jTable5.getModel();
            det.setId_material(Integer.parseInt(modelMat.getValueAt(2, 2).toString()));
            
            boolean warning = false;
            for (int i = 0; i < modelDet.getRowCount(); i++){
                boolean flag = false;
                for (int j = 1; j < 4; j++)
                    if (modelDet.getValueAt(i, j) == null){
                        materialLists.setCountOfinserted(materialLists.getCountOfinserted()-1);
                        flag = true;
                        warning = true;
                        break;
                    }
                if (flag == false){
                    det.setWidth((int)modelDet.getValueAt(i, 1));
                    det.setHeight((int)modelDet.getValueAt(i, 2));
                    det.setCount((int)modelDet.getValueAt(i, 3));
                    det.setRotated((boolean)modelDet.getValueAt(i, 4));
                    detList.add(det);
                }
            }
            
            if (askUser == true || warning == false){
                try{
                    load.loadDetailToDB(detList, detailLists[2].getUpdatedList(), detailLists[2].getDeletedList(), detailLists[2].getCountOfinserted());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                detailLists[2].clear();
            }
        }
        if (jTabbedPane2.isEnabledAt(3)){
            detList = new ArrayList();
            DefaultTableModel modelDet = (DefaultTableModel) jTable6.getModel();
            det.setId_material(Integer.parseInt(modelMat.getValueAt(3, 2).toString()));
            
            for (int i = 0; i < modelDet.getRowCount(); i++){
                //order.setId((int)jTable1.getValueAt(i, 0));
                det.setWidth((int)modelDet.getValueAt(i, 1));
                det.setHeight((int)modelDet.getValueAt(i, 2));
                det.setCount((int)modelDet.getValueAt(i, 3));
                det.setRotated((boolean)modelDet.getValueAt(i, 4));
                detList.add(det);
            }
            try{
                load.loadDetailToDB(detList, detailLists[3].getUpdatedList(), detailLists[3].getDeletedList(), detailLists[3].getCountOfinserted());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            detailLists[3].clear();
        }
        if (jTabbedPane2.isEnabledAt(4)){
            detList = new ArrayList();
            DefaultTableModel modelDet = (DefaultTableModel) jTable7.getModel();
            det.setId_material(Integer.parseInt(modelMat.getValueAt(4, 2).toString()));
            
            for (int i = 0; i < modelDet.getRowCount(); i++){
                //order.setId((int)jTable1.getValueAt(i, 0));
                det.setWidth((int)modelDet.getValueAt(i, 1));
                det.setHeight((int)modelDet.getValueAt(i, 2));
                det.setCount((int)modelDet.getValueAt(i, 3));
                det.setRotated((boolean)modelDet.getValueAt(i, 4));
                detList.add(det);
            }
            try{
                load.loadDetailToDB(detList, detailLists[4].getUpdatedList(), detailLists[4].getDeletedList(), detailLists[4].getCountOfinserted());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            detailLists[4].clear();
        }
        if (jTabbedPane2.isEnabledAt(5)){
            detList = new ArrayList();
            DefaultTableModel modelDet = (DefaultTableModel) jTable8.getModel();
            det.setId_material(Integer.parseInt(modelMat.getValueAt(5, 2).toString()));
            
            for (int i = 0; i < modelDet.getRowCount(); i++){
                //order.setId((int)jTable1.getValueAt(i, 0));
                det.setWidth((int)modelDet.getValueAt(i, 1));
                det.setHeight((int)modelDet.getValueAt(i, 2));
                det.setCount((int)modelDet.getValueAt(i, 3));
                det.setRotated((boolean)modelDet.getValueAt(i, 4));
                detList.add(det);
            }
            try{
                load.loadDetailToDB(detList, detailLists[5].getUpdatedList(), detailLists[5].getDeletedList(), detailLists[5].getCountOfinserted());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            detailLists[5].clear();
        }
        if (jTabbedPane2.isEnabledAt(6)){
            detList = new ArrayList();
            DefaultTableModel modelDet = (DefaultTableModel) jTable10.getModel();
            det.setId_material(Integer.parseInt(modelMat.getValueAt(6, 2).toString()));
            
            for (int i = 0; i < modelDet.getRowCount(); i++){
                //order.setId((int)jTable1.getValueAt(i, 0));
                det.setWidth((int)modelDet.getValueAt(i, 1));
                det.setHeight((int)modelDet.getValueAt(i, 2));
                det.setCount((int)modelDet.getValueAt(i, 3));
                det.setRotated((boolean)modelDet.getValueAt(i, 4));
                detList.add(det);
            }
            try{
                load.loadDetailToDB(detList, detailLists[6].getUpdatedList(), detailLists[6].getDeletedList(), detailLists[6].getCountOfinserted());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            detailLists[6].clear();
        }
        
        askUser = false;
    }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        saveDetails();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTable3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyPressed
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        if (model.getValueAt(jTable3.getSelectedRow(), 0) != "" && model.getValueAt(jTable3.getSelectedRow(), 0) != null)
            detailLists[0].addUpdatedList((int)model.getValueAt(jTable3.getSelectedRow(), 0));
    }//GEN-LAST:event_jTable3KeyPressed

    private void jTable4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable4KeyPressed
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        if (model.getValueAt(jTable4.getSelectedRow(), 0) != "" && model.getValueAt(jTable4.getSelectedRow(), 0) != null)
            detailLists[1].addUpdatedList((int)model.getValueAt(jTable4.getSelectedRow(), 0));
    }//GEN-LAST:event_jTable4KeyPressed

    private void jTable5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable5KeyPressed
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        if (model.getValueAt(jTable5.getSelectedRow(), 0) != "" && model.getValueAt(jTable5.getSelectedRow(), 0) != null)
            detailLists[2].addUpdatedList((int)model.getValueAt(jTable5.getSelectedRow(), 0));
    }//GEN-LAST:event_jTable5KeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        askUser = true;
        jDialogEmptyCells.setVisible(false);
        jButton16.doClick();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jDialogEmptyCells.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButtonDelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelOrderActionPerformed
        int ind = jTableOrders.getSelectedRow();
        if (ind > -1)
        {
            DefaultTableModel model = (DefaultTableModel)jTableOrders.getModel();

            if (ind > -1){
                SQLiteManager man = new SQLiteManager();
                try {
                    man.deleteOrder((int)model.getValueAt(ind, 0));
                } catch (SQLException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.removeRow(ind);
            }
        }
    }//GEN-LAST:event_jButtonDelOrderActionPerformed

    private void jButtonDelOrdServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelOrdServActionPerformed
        int ind = jTableOrdService.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableOrdService.getModel();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deleteOrderService(Integer.parseInt(model.getValueAt(ind, 0).toString()));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(ind);
        }
    }//GEN-LAST:event_jButtonDelOrdServActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
         jLabel21.setVisible(false);
         jDialogNewClient.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel1ActionPerformed
        jDialogNewStaff.setVisible(false);
    }//GEN-LAST:event_jButtonCancel1ActionPerformed

    private void jButtonDelOrder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelOrder1ActionPerformed
        
    }//GEN-LAST:event_jButtonDelOrder1ActionPerformed

    private void jButtonDelOrder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelOrder2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDelOrder2ActionPerformed

    private void jButtonDelOrder3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelOrder3ActionPerformed
        int ind = jTableAllClients.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableAllClients.getModel();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deleteClient((int)model.getValueAt(ind, 0));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(ind);
        }
    }//GEN-LAST:event_jButtonDelOrder3ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        ButtonsHandler.refreshTableMaterial(jTableMaterial);
        ButtonsHandler.refreshTablePosts(jTablePosts);
        ButtonsHandler.refreshTableServices(jTableService);
        ButtonsHandler.refreshTableStats(jTableStatus);
        
        jDialogInfo.setVisible(true);
        jDialogInfo.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItemStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemStaffActionPerformed
        ButtonsHandler.refreshTableStaff(jTableAllStaff);
        
        jDialogAllStaff.setVisible(true);
        jDialogAllStaff.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItemStaffActionPerformed

    private void jMenuItemClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientActionPerformed
        ButtonsHandler.refreshTableAllClients(jTableAllClients);
        
        jDialogAllClients.setVisible(true);
        jDialogAllClients.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItemClientActionPerformed

    private void jButtonOpenAddClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenAddClientActionPerformed
        jTextFieldName.setText("");
        jTextFieldSurname.setText("");
        jTextFieldAddress.setText("");
        jTextFieldTel.setText("");
        
        jDialogNewClient.setVisible(true);
        jDialogNewClient.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButtonOpenAddClientActionPerformed

    private void jButtonClientAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientAddActionPerformed
        DefaultTableModel model = (DefaultTableModel)jTableAllClients.getModel();
        int ind = jTableAllClients.getSelectedRow();
        if (ind > -1)
            ind = (int)model.getValueAt(ind, 0);
        
        System.out.println(ind);
        
        if (jTextFieldName.getText().equals("")){
            jLabel21.setVisible(true);
        }
        else{
            SQLiteManager man = new SQLiteManager();
            try {
                man.insertIntoClient(ind, jTextFieldSurname.getText(), jTextFieldName.getText(),
                        jTextFieldTel.getText(), jTextFieldAddress.getText());
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel21.setVisible(false);
            ButtonsHandler.refreshTableAllClients(jTableAllClients);
            try {
                ComboBoxHandler.refreshClientsComboBox(jComboBoxClients);
                jComboBoxClients.setSelectedIndex(jComboBoxClients.getItemCount()-2);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            jDialogNewClient.setVisible(false);
        }
    }//GEN-LAST:event_jButtonClientAddActionPerformed

    private void jButtonOpenEdiClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenEdiClientActionPerformed
        int ind = jTableAllClients.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableAllClients.getModel();
        
        jTextFieldName.setText(model.getValueAt(ind, 3).toString());
        jTextFieldSurname.setText(model.getValueAt(ind, 2).toString());
        jTextFieldAddress.setText(model.getValueAt(ind, 5).toString());
        jTextFieldTel.setText(model.getValueAt(ind, 4).toString());
        
        jDialogNewClient.setVisible(true);
        jDialogNewClient.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButtonOpenEdiClientActionPerformed

    private void jButtonApplyClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApplyClientsActionPerformed
        jDialogAllClients.setVisible(false);
    }//GEN-LAST:event_jButtonApplyClientsActionPerformed

    private void jTableOrdersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrdersMousePressed
        SQLiteManager man = new SQLiteManager();
        int ind = jTableOrders.getSelectedRow();
        DefaultTableModel modelOrders = (DefaultTableModel)jTableOrders.getModel();
        JTable table;
        
        if (ind > -1){
            id_order = Integer.parseInt(modelOrders.getValueAt(ind, 0).toString()); 
            
            ButtonsHandler.refreshTableOrderService(jTableOrdService, id_order);
            ButtonsHandler.refreshTableOrderStatus(jTableOrdStats, id_order);
            ButtonsHandler.refreshTableOrderSheets(jTableOrdMat, id_order);
            ButtonsHandler.refreshTableOrderSheets(jTableOrdSheets, id_order);
            
            for (int i = 0; i < 6; i++){
                jTabbedPane2.setEnabledAt(i, false);
                jTabbedPane2.setTitleAt(i, "");
            }
            
            for (int i = 0; i < jTableOrdSheets.getRowCount(); i++){
                jTabbedPane2.setEnabledAt(i, true);
                jTabbedPane2.setTitleAt(i, jTableOrdSheets.getValueAt(i, 0).toString());
                
                switch(i){
                    case 0:
                        table = jTable3; break;
                    case 1:
                        table = jTable4; break;
                    case 2:
                        table = jTable5; break;
                    case 3:
                        table = jTable6; break;
                    case 4:
                        table = jTable7; break;
                    case 5:
                        table = jTable8; break;
                    default:
                        table = jTable10; break;
                }
    
                ButtonsHandler.refreshTableDetails(table, id_order, i);
            }
            if (jTableOrdSheets.getRowCount() > 0){
                jButtonAddDetail.setEnabled(true);
                jButtonDeleteDetail.setEnabled(true);
                jButtonCreateSheets.setEnabled(true);
            }
            else{
                jButtonAddDetail.setEnabled(false);
                jButtonDeleteDetail.setEnabled(false);
                jButtonCreateSheets.setEnabled(false);
            }
            userChange = false;
        }
    }//GEN-LAST:event_jTableOrdersMousePressed

    private void jButtonCancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel3ActionPerformed
        jDialogNewOrderService.setVisible(false);
    }//GEN-LAST:event_jButtonCancel3ActionPerformed

    private void jButtonAddOrdServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddOrdServActionPerformed
        if (jTableOrders.getSelectedRow() > -1){
            try {
                ComboBoxHandler.refreshStaffComboBox(jComboBoxStaff);
                ComboBoxHandler.refreshServiceComboBox(jComboBoxServices);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            jDialogNewOrderService.setLocationRelativeTo(null);
            jDialogNewOrderService.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAddOrdServActionPerformed

    private void jTableAllStaffMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAllStaffMousePressed
        int ind = jTableAllStaff.getSelectedRow();
        DefaultTableModel modelOrders = (DefaultTableModel)jTableAllStaff.getModel();
        
        if (ind > -1){
            ButtonsHandler.refreshTableStaffPost(jTableStaffPost, Integer.parseInt(modelOrders.getValueAt(ind, 0).toString()));
        }
    }//GEN-LAST:event_jTableAllStaffMousePressed

    private void jButtonOpenAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenAddStaffActionPerformed
        try {
            ComboBoxHandler.refreshStaffPostComboBox(jComboBoxStaffPost);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jDialogNewStaff.setLocationRelativeTo(null);
        jDialogNewStaff.setVisible(true);
    }//GEN-LAST:event_jButtonOpenAddStaffActionPerformed

    private void jButtonCancel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel4ActionPerformed
        jLabelError.setVisible(false);
        jDialogAddPost.setVisible(false);
    }//GEN-LAST:event_jButtonCancel4ActionPerformed

    private void jButtonAddPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPostActionPerformed
        if (jTextFieldAddPost.getText().equals(""))
            jLabelError.setVisible(true);
        else
        {
            SQLiteManager man = new SQLiteManager();
            
            try {
                man.insertIntoPost(-1, jTextFieldAddPost.getText());
                ButtonsHandler.refreshTablePosts(jTablePosts);
                ComboBoxHandler.refreshStaffPostComboBox(jComboBoxStaffPost);
                jComboBoxStaffPost.setSelectedIndex(jComboBoxStaffPost.getItemCount()-2);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            jLabelError.setVisible(false);
            jDialogAddPost.setVisible(false);
        }
    }//GEN-LAST:event_jButtonAddPostActionPerformed

    private void jComboBoxStaffPostItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxStaffPostItemStateChanged
        int ind = jComboBoxStaffPost.getSelectedIndex();
        if (ind > -1){
            if (ComboBoxHandler.getID(ind).equals("-1")){
                jDialogAddPost.setLocationRelativeTo(null);
                jDialogAddPost.setVisible(true);
            }
        }
    }//GEN-LAST:event_jComboBoxStaffPostItemStateChanged

    private void jButtonAddMaterial1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMaterial1ActionPerformed
        if (jTextFieldAddMaterial.getText().equals(""))
            jLabelError1.setVisible(true);
        else
        {
            try {
                ComboBoxHandler.refreshStaffPostComboBox(jComboBoxStaffPost);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            jLabelError1.setVisible(false);
            jDialogAddMaterial.setVisible(false);
        }
    }//GEN-LAST:event_jButtonAddMaterial1ActionPerformed

    private void jButtonCancel5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel5ActionPerformed
        jLabelError1.setVisible(false);
        jDialogAddMaterial.setVisible(false);
    }//GEN-LAST:event_jButtonCancel5ActionPerformed

    private void jButtonCancel6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel6ActionPerformed
       jLabelError2.setVisible(false);
       jDialogAddService.setVisible(false);
    }//GEN-LAST:event_jButtonCancel6ActionPerformed

    private void jButtonAddService1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddService1ActionPerformed
        if ((jTextFieldServicePrice.getText().equals(""))||(jTextFieldAddService1.getText().equals("")))
            jLabelError2.setVisible(true);
        else
        {
            int price;
                if (jTextFieldServicePrice.getText().indexOf(".") == -1)
                    price = Integer.parseInt(jTextFieldServicePrice.getText())*100;
                else{
                    String[] fullprice = new String[2];

                    int i = 0;
                    while (jTextFieldServicePrice.getText().charAt(i) != '.'){
                        i++;
                    }
                    fullprice[0] = jTextFieldServicePrice.getText().substring(0, i);
                    fullprice[1] = jTextFieldServicePrice.getText().substring(i+1);
                    
                    price = (Integer.parseInt(fullprice[0])*100)+Integer.parseInt(fullprice[1]);
                }
                    
            SQLiteManager man = new SQLiteManager();
            try {
                man.insertIntoService(-1, jTextFieldAddService1.getText(), price);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            ButtonsHandler.refreshTableServices(jTableService);
            
            jLabelError2.setVisible(false);
            jDialogAddService.setVisible(false);
        }
    }//GEN-LAST:event_jButtonAddService1ActionPerformed

    private void jButtonAddStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddStatusActionPerformed
        jDialogAddStatus.setLocationRelativeTo(null);
        jDialogAddStatus.setVisible(true);
        jLabelError4.setVisible(false);
    }//GEN-LAST:event_jButtonAddStatusActionPerformed

    private void jButtonAddMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMaterialActionPerformed
        jDialogAddMaterial.setLocationRelativeTo(null);
        jDialogAddMaterial.setVisible(true);
        jLabelError1.setVisible(false);
    }//GEN-LAST:event_jButtonAddMaterialActionPerformed

    private void jButtonAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddStaffActionPerformed
        jDialogAddPost.setLocationRelativeTo(null);
        jDialogAddPost.setVisible(true);
        jLabelError.setVisible(false);
    }//GEN-LAST:event_jButtonAddStaffActionPerformed

    private void jButtonAddServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddServiceActionPerformed
        jDialogAddService.setLocationRelativeTo(null);
        jDialogAddService.setVisible(true);
        jLabelError2.setVisible(false);
    }//GEN-LAST:event_jButtonAddServiceActionPerformed

    private void jButtonAddStatus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddStatus1ActionPerformed
        if (jTextFieldAddStatus.getText().equals(""))
            jLabelError4.setVisible(true);
        else
        {
            SQLiteManager man = new SQLiteManager();
            
            try {
                man.insertIntoStatus(-1, jTextFieldAddStatus.getText());
                ButtonsHandler.refreshTableStats(jTableStatus);
                
                //ComboBoxHandler.refreshStatsComboBox(jComboBoxStaffPost);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ComboBoxHandler.setStatsComboBox();
                ComboBoxHandler.refreshStatsComboBox(jComboBoxStats);
                jComboBoxStats.setSelectedIndex(jComboBoxStats.getItemCount()-2);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            jLabelError4.setVisible(false);
            jDialogAddStatus.setVisible(false);
        }
    }//GEN-LAST:event_jButtonAddStatus1ActionPerformed

    private void jButtonCancel8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel8ActionPerformed
        jLabelError4.setVisible(false);
        jDialogAddStatus.setVisible(false);
    }//GEN-LAST:event_jButtonCancel8ActionPerformed

    private void jButtonDelStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelStaffActionPerformed
        int ind = jTablePosts.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTablePosts.getModel();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deletePost((int)model.getValueAt(ind, 0));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(ind);
        }
    }//GEN-LAST:event_jButtonDelStaffActionPerformed

    private void jButtonDelStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelStatusActionPerformed
        int ind = jTableStatus.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableStatus.getModel();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deleteStatus((int)model.getValueAt(ind, 0));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(ind);
        }
    }//GEN-LAST:event_jButtonDelStatusActionPerformed

    private void jButtonDelServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelServiceActionPerformed
        int ind = jTableService.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableService.getModel();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deleteService((int)model.getValueAt(ind, 0));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(ind);
        }
    }//GEN-LAST:event_jButtonDelServiceActionPerformed

    private void jButtonDelMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelMaterialActionPerformed
        int ind = jTableMaterial.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableMaterial.getModel();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deleteMaterial((int)model.getValueAt(ind, 0));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(ind);
        }
    }//GEN-LAST:event_jButtonDelMaterialActionPerformed

    private void jComboBoxClientsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxClientsItemStateChanged
        try {
            ComboBoxHandler.setClientsComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        int ind = jComboBoxClients.getSelectedIndex();
        if (ind > -1){
            if (ComboBoxHandler.getID(ind).equals("-1")){
                jDialogNewClient.setLocationRelativeTo(null);
                jDialogNewClient.setVisible(true);
            }
        }
    }//GEN-LAST:event_jComboBoxClientsItemStateChanged

    private void jButtonOrderAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrderAddActionPerformed
        if (jTextFieldOrdDesc.getText().equals("") || jTextFieldOrdDate.getText().equals(""))
            jLabelError6.setVisible(true);
        else
        {
            SQLiteManager man = new SQLiteManager();
            
            try {
                ComboBoxHandler.setClientsComboBox();
                int selectedInd = jComboBoxClients.getSelectedIndex();
                int id_client = Integer.parseInt(ComboBoxHandler.getID(selectedInd));
                
                man.insertIntoOrder(-1, id_client, jTextFieldOrdDesc.getText());

                List<Integer> orderID = man.selectAllOrdersID();
                int id_lastOrder = orderID.get(orderID.size()-1);
                
                ComboBoxHandler.setStatsComboBox();
                selectedInd = jComboBoxStats.getSelectedIndex();
                int id_status = Integer.parseInt(ComboBoxHandler.getID(selectedInd));
                
                man.insertIntoOrderStatus(-1, id_lastOrder, id_status, new SimpleDateFormat("yyyy-MM-dd").parse(jTextFieldOrdDate.getText()));
                ButtonsHandler.refreshTableOrders(jTableOrders);
                
                //ButtonsHandler.refreshTableStats(jTableStatus);
                //ComboBoxHandler.refreshStatsComboBox(jComboBoxStaffPost);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            jLabelError6.setVisible(false);
            jDialogNewOrder.setVisible(false);
        }
    }//GEN-LAST:event_jButtonOrderAddActionPerformed

    private void jButtonCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel2ActionPerformed
        jLabelError6.setVisible(false);
        jDialogNewOrder.setVisible(false);
    }//GEN-LAST:event_jButtonCancel2ActionPerformed

    private void jComboBoxStatsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxStatsItemStateChanged
        try {
            ComboBoxHandler.setStatsComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        int ind = jComboBoxStats.getSelectedIndex();
        if (ind > -1){
            if (ComboBoxHandler.getID(ind).equals("-1")){
                jDialogAddStatus.setLocationRelativeTo(null);
                jDialogAddStatus.setVisible(true);
            }
        }
    }//GEN-LAST:event_jComboBoxStatsItemStateChanged

    private void jButtonStaffAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStaffAddActionPerformed
        DefaultTableModel model = (DefaultTableModel)jTableAllStaff.getModel();
        int ind = jTableAllStaff.getSelectedRow();
        if (ind > -1)
            ind = Integer.parseInt(model.getValueAt(ind, 0).toString());
        
        if (jTextFieldStaffName.getText().equals("") || jTextFieldStaffSurname.getText().equals("") ||
                jTextFieldStaffAdr.getText().equals("") || jTextFieldStaffTel.getText().equals("") ||
                jTextFieldStaffBirth.getText().equals("") ||jTextFieldStaffPostDate.getText().equals("")){
            jLabelError3.setVisible(true);
        }
        else
        {
            SQLiteManager man = new SQLiteManager();
            
            try {
                man.insertIntoStaff(-1, jTextFieldStaffSurname.getText(), jTextFieldStaffName.getText(),
                        jTextFieldStaffTel.getText(), jTextFieldStaffAdr.getText(), new SimpleDateFormat("yyyy-MM-dd").parse(jTextFieldStaffBirth.getText()));
                
                List <String> list = man.selectStaffID();
                int id = Integer.parseInt(list.get(list.size()-1));
                System.out.println("id: " + list.get(list.size()-1));
                System.out.println("size: " + list.size());
                
                ind = jComboBoxStaffPost.getSelectedIndex();
                int id_post = Integer.parseInt(ComboBoxHandler.getID(ind));
                
                man.insertIntoStaffPost(-1, id, id_post, new SimpleDateFormat("yyyy-MM-dd").parse(jTextFieldStaffPostDate.getText()));
                
                ButtonsHandler.refreshTableStaff(jTableAllStaff);
                ComboBoxHandler.refreshStaffComboBox(jComboBoxStaff);
                
                jLabelError3.setVisible(false);
                ButtonsHandler.refreshTableStaff(jTableAllStaff);
            } catch (ParseException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } 
            jDialogNewStaff.setVisible(false);
        }
    }//GEN-LAST:event_jButtonStaffAddActionPerformed

    private void jButtonAddOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddOrderActionPerformed
        try {
            ComboBoxHandler.refreshClientsComboBox(jComboBoxClients);
            ComboBoxHandler.refreshStatsComboBox(jComboBoxStats);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jDialogNewOrder.setLocationRelativeTo(null);
        jDialogNewOrder.setVisible(true);
    }//GEN-LAST:event_jButtonAddOrderActionPerformed

    private void jButtonClientAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientAdd3ActionPerformed
        SQLiteManager man =new SQLiteManager();
        
        int selectedIndex = jComboBoxServices.getSelectedIndex();
        try {
            ComboBoxHandler.setServiceComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int id_service = Integer.parseInt(ComboBoxHandler.getID(selectedIndex));
        
        selectedIndex = jComboBoxStaff.getSelectedIndex();
        try {
            ComboBoxHandler.setStaffComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int id_staff = Integer.parseInt(ComboBoxHandler.getID(selectedIndex));
        
        try {
            man.insertIntoServiceOrder(-1, id_service, id_order, id_staff);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ButtonsHandler.refreshTableOrderService(jTableOrdService, id_order);
        
        jDialogNewOrderService.setVisible(false);
    }//GEN-LAST:event_jButtonClientAdd3ActionPerformed

    private void jComboBoxMaterialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMaterialItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMaterialItemStateChanged

    private void jButtonSheetAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSheetAddActionPerformed
        DefaultTableModel model = (DefaultTableModel)jTableOrdMat.getModel();
        /*int ind = jTableAllStaff.getSelectedRow();
        if (ind > -1)
            ind = Integer.parseInt(model.getValueAt(ind, 0).toString());
        */
        if (jTextFieldLength.getText().equals("") || jTextFieldWidth.getText().equals("") ||
                jTextFieldPrice.getText().equals("")) {
            jLabelError5.setVisible(true);
        }
        else
        {
            SQLiteManager man = new SQLiteManager();
            
            try {
                
                int ind = jComboBoxMaterial.getSelectedIndex();
                int id_material = Integer.parseInt(ComboBoxHandler.getID(ind));
                
                int price;
                if (jTextFieldPrice.getText().indexOf(".") == -1)
                    price = Integer.parseInt(jTextFieldPrice.getText())*100;
                else{
                    String[] fullprice = new String[2];

                    int i = 0;
                    while (jTextFieldPrice.getText().charAt(i) != '.'){
                        i++;
                    }
                    fullprice[0] = jTextFieldPrice.getText().substring(0, i);
                    fullprice[1] = jTextFieldPrice.getText().substring(i+1);
                    
                    price = (Integer.parseInt(fullprice[0])*100)+Integer.parseInt(fullprice[1]);
                }
                man.insertIntoSheet(-1, id_material, Integer.parseInt(jTextFieldWidth.getText()), Integer.parseInt(jTextFieldLength.getText()), price);
                
                List<String> matID = man.selectSheetsID();
                int id_sheet = Integer.parseInt(matID.get(matID.size()-1));
                        
                man.insertIntoSheets(-1, id_order, id_sheet, 1);
                ButtonsHandler.refreshTableOrderSheets(jTableOrdMat, id_order);
                ButtonsHandler.refreshTableOrderSheets(jTableOrdSheets, id_order);
                
                jTabbedPane2.setEnabledAt(jTableOrdMat.getRowCount()-1, true);
                jTabbedPane2.setTitleAt(ind, (jTableOrdMat.getValueAt(jTableOrdMat.getRowCount()-1, 0)).toString());
                
                jLabelError5.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } 
            jDialogAddSheet.setVisible(false);
        }
    }//GEN-LAST:event_jButtonSheetAddActionPerformed

    private void jButtonCancel7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel7ActionPerformed
        jDialogAddSheet.setVisible(false);
    }//GEN-LAST:event_jButtonCancel7ActionPerformed

    private void jTextFieldWidthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWidthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldWidthActionPerformed

    private void jButtonAddOrdMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddOrdMatActionPerformed
        if (jTableOrders.getSelectedRow() > -1)
        {
            try {
                ComboBoxHandler.refresMaterialComboBox(jComboBoxMaterial);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            jDialogAddSheet.setLocationRelativeTo(null);
            jDialogAddSheet.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAddOrdMatActionPerformed

    private void jTextFieldPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPriceActionPerformed

    private void jButtonAddStatToOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddStatToOrdActionPerformed
        if (jTableOrders.getSelectedRow() > -1){
            try {
                ComboBoxHandler.refreshStatusComboBox(jComboBoxStatuses);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            jDialogAddOrdStatus.setLocationRelativeTo(null);
            jDialogAddOrdStatus.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAddStatToOrdActionPerformed

    private void jComboBoxStatusesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxStatusesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxStatusesItemStateChanged

    private void jButtonStatAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStatAddActionPerformed
         if (jTextFieldStatDate.getText().equals("")){
            jLabelError7.setVisible(true);
        }
        else
        {
            SQLiteManager man = new SQLiteManager();
            
            try {
                int ind = jComboBoxStatuses.getSelectedIndex();
                int id_status = Integer.parseInt(ComboBoxHandler.getID(ind));
                
                man.insertIntoOrderStatus(-1, id_order, id_status, new SimpleDateFormat("yyyy-MM-dd").parse(jTextFieldStatDate.getText()));
                
                ButtonsHandler.refreshTableOrderStatus(jTableOrdStats, id_order);
                
                jLabelError3.setVisible(false);
            } catch (ParseException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } 
            jDialogAddOrdStatus.setVisible(false);
        }
    }//GEN-LAST:event_jButtonStatAddActionPerformed

    private void jButtonCancel9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel9ActionPerformed
        jDialogAddOrdStatus.setVisible(false);
    }//GEN-LAST:event_jButtonCancel9ActionPerformed

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        if (jRadioButton1.isSelected()){
            jLabelFilter1.setVisible(false);
            jLabelFilter2.setVisible(false);
            jTextFieldFilter1.setVisible(false);
            jTextFieldFilter2.setVisible(false);
            jLabelFilter3.setVisible(false);
            jComboBoxFilter.setVisible(false);
            jButtonFilterSearch.setVisible(false);
            ButtonsHandler.refreshTableOrders(jTableOrders);
        }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        if (jRadioButton2.isSelected()){
            jLabelFilter3.setVisible(true);
            jLabelFilter3.setText("Клиент:");
            
            try {
                ComboBoxHandler.refreshClientsComboBox(jComboBoxFilter);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            jComboBoxFilter.setVisible(true);
            jButtonFilterSearch.setVisible(true);
        }
        else{
            jLabelFilter3.setVisible(false);
            jComboBoxFilter.setVisible(false);
            jButtonFilterSearch.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton4ItemStateChanged
        if (jRadioButton4.isSelected()){
            jLabelFilter1.setVisible(true);
            jLabelFilter2.setVisible(true);
            jTextFieldFilter1.setVisible(true);
            jTextFieldFilter2.setVisible(true);
            jButtonFilterSearch.setVisible(true);
        }
        else{
            jLabelFilter1.setVisible(false);
            jLabelFilter2.setVisible(false);
            jTextFieldFilter1.setVisible(false);
            jTextFieldFilter2.setVisible(false);
            jButtonFilterSearch.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton4ItemStateChanged

    private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton3ItemStateChanged
        if (jRadioButton3.isSelected()){
            jLabelFilter3.setVisible(true);
            jLabelFilter3.setText("Статус:");
            
            try {
                ComboBoxHandler.refreshStatsComboBox(jComboBoxFilter);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            jComboBoxFilter.setVisible(true);
            jButtonFilterSearch.setVisible(true);
        }
        else{
            jLabelFilter3.setVisible(false);
            jComboBoxFilter.setVisible(false);
            jButtonFilterSearch.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton3ItemStateChanged

    private void jButtonFilterSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFilterSearchActionPerformed
        SQLiteManager man = new SQLiteManager();
        if (jRadioButton2.isSelected()){
            int selectedInd = jComboBoxFilter.getSelectedIndex();
            int id_client = Integer.parseInt(ComboBoxHandler.getID(selectedInd));
            
            ButtonsHandler.refreshTableOrdersWithFilters(jTableOrders, 1, id_client);
        }
        else if(jRadioButton3.isSelected()){
            int selectedInd = jComboBoxFilter.getSelectedIndex();
            int id_status = Integer.parseInt(ComboBoxHandler.getID(selectedInd));
            
            ButtonsHandler.refreshTableOrdersWithFilters(jTableOrders, 2, id_status);
        }
        else{
            String dateFrom = jTextFieldFilter1.getText();
            String dateTo = jTextFieldFilter2.getText();
                
            ButtonsHandler.refreshTableOrdersWithDate(jTableOrders, dateFrom, dateTo);
        }
    }//GEN-LAST:event_jButtonFilterSearchActionPerformed

    private void jButtonDelStatOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelStatOrdActionPerformed
        int ind = jTableOrdStats.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableOrdStats.getModel();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deleteOrderStatus((int)model.getValueAt(ind, 0));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.removeRow(ind);
        }
    }//GEN-LAST:event_jButtonDelStatOrdActionPerformed

    private void jButtonDelOrdMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelOrdMatActionPerformed
        int ind = jTableOrdMat.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableOrdMat.getModel();
        
        if (ind > -1){
            SQLiteManager man = new SQLiteManager();
            try {
                man.deleteSheets(Integer.parseInt(model.getValueAt(ind, 0).toString()));
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            ButtonsHandler.refreshTableOrderSheets(jTableOrdMat, id_order);
            ButtonsHandler.refreshTableOrderSheets(jTableOrdSheets, id_order);
            jTabbedPane2.setEnabledAt(ind, false);
            jTabbedPane2.setTitleAt(ind, "");
        }
    }//GEN-LAST:event_jButtonDelOrdMatActionPerformed

    private void jCheckBoxSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxSearchItemStateChanged
        if (jCheckBoxSearch.isSelected()){
            askUser = false;
            jComboBoxStaffPostSearch.setVisible(true);
            try {
                ComboBoxHandler.refreshStaffPostComboBox(jComboBoxStaffPostSearch);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            SQLiteManager man = new SQLiteManager();

            int selectedInd = jComboBoxStaffPostSearch.getSelectedIndex();
            int id_post = Integer.parseInt(ComboBoxHandler.getID(selectedInd));

            ButtonsHandler.refreshTableStaffWithPost(jTableAllStaff, id_post);
        }
        else{
            jComboBoxStaffPostSearch.setVisible(false);
            ButtonsHandler.refreshTableStaff(jTableAllStaff);
        }
            
    }//GEN-LAST:event_jCheckBoxSearchItemStateChanged

    private void jComboBoxStaffPostSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxStaffPostSearchItemStateChanged
        if (askUser == true){
            SQLiteManager man = new SQLiteManager();

            int selectedInd = jComboBoxStaffPostSearch.getSelectedIndex();
            int id_post = Integer.parseInt(ComboBoxHandler.getID(selectedInd));

            ButtonsHandler.refreshTableStaffWithPost(jTableAllStaff, id_post);
        }
    }//GEN-LAST:event_jComboBoxStaffPostSearchItemStateChanged

    private void jComboBoxStaffPostSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxStaffPostSearchMousePressed
        askUser = true;
    }//GEN-LAST:event_jComboBoxStaffPostSearchMousePressed

    private void jComboBoxStaffPostSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxStaffPostSearchFocusGained
        askUser = true;
    }//GEN-LAST:event_jComboBoxStaffPostSearchFocusGained
    
    private void updateDetail(DefaultTableModel modelDets, int ind){
        /*SQLiteManager man = new SQLiteManager();
        DefaultTableModel modelSheets = (DefaultTableModel)jTableOrdSheets.getModel();
        if (userChange == true && modelDets.getValueAt(modelDets.getRowCount()-1, 6) != null)
        {
            int id = Integer.parseInt(modelDets.getValueAt(ind, 6).toString());
            int id_sheets = Integer.parseInt(modelSheets.getValueAt(jTabbedPane2.getSelectedIndex(), 2).toString());
            int length = Integer.parseInt(modelDets.getValueAt(ind, 1).toString());
            int width = Integer.parseInt(modelDets.getValueAt(ind, 2).toString());
            int cnt = Integer.parseInt(modelDets.getValueAt(ind, 3).toString());
            boolean rot = Boolean.parseBoolean(modelDets.getValueAt(ind, 4).toString());

            try {
                man.insertIntoDetail(id, id_sheets, width, length, cnt, rot);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            ButtonsHandler.refreshTableDetails(modelDets, id_order, jTabbedPane2.getSelectedIndex());
            System.out.println("!!!");
        }*/
    }
    
    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        
    }//GEN-LAST:event_jTable3MousePressed

    private void jTable3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable3FocusGained
        userChange = true;
    }//GEN-LAST:event_jTable3FocusGained

    private void jButtonEditOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditOrderActionPerformed
        if (jTableOrders.getSelectedRow() > -1){
            int ind = jTableOrders.getSelectedRow();
            try {
                ComboBoxHandler.refreshClientsComboBox(jComboBoxClients);
                int sel = ComboBoxHandler.getInd(jTableOrders.getValueAt(ind, 3).toString());
                jComboBoxClients.setSelectedIndex(sel);

                jComboBoxStats.setVisible(false);
                jTextFieldOrdDate.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }


            jTextFieldOrdDesc.setText(jTableOrders.getValueAt(ind, 1).toString());

            jDialogNewOrder.setLocationRelativeTo(null);
            jDialogNewOrder.setVisible(true);
        }
    }//GEN-LAST:event_jButtonEditOrderActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonAddDetail;
    private javax.swing.JButton jButtonAddMaterial;
    private javax.swing.JButton jButtonAddMaterial1;
    private javax.swing.JButton jButtonAddOrdMat;
    private javax.swing.JButton jButtonAddOrdServ;
    private javax.swing.JButton jButtonAddOrder;
    private javax.swing.JButton jButtonAddPost;
    private javax.swing.JButton jButtonAddService;
    private javax.swing.JButton jButtonAddService1;
    private javax.swing.JButton jButtonAddStaff;
    private javax.swing.JButton jButtonAddStatToOrd;
    private javax.swing.JButton jButtonAddStatus;
    private javax.swing.JButton jButtonAddStatus1;
    private javax.swing.JButton jButtonApplyChanges;
    private javax.swing.JButton jButtonApplyClients;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCancel1;
    private javax.swing.JButton jButtonCancel2;
    private javax.swing.JButton jButtonCancel3;
    private javax.swing.JButton jButtonCancel4;
    private javax.swing.JButton jButtonCancel5;
    private javax.swing.JButton jButtonCancel6;
    private javax.swing.JButton jButtonCancel7;
    private javax.swing.JButton jButtonCancel8;
    private javax.swing.JButton jButtonCancel9;
    private javax.swing.JButton jButtonCanelChanges;
    private javax.swing.JButton jButtonClientAdd;
    private javax.swing.JButton jButtonClientAdd3;
    private javax.swing.JButton jButtonCreateSheets;
    private javax.swing.JButton jButtonDelMaterial;
    private javax.swing.JButton jButtonDelOrdMat;
    private javax.swing.JButton jButtonDelOrdServ;
    private javax.swing.JButton jButtonDelOrder;
    private javax.swing.JButton jButtonDelOrder1;
    private javax.swing.JButton jButtonDelOrder2;
    private javax.swing.JButton jButtonDelOrder3;
    private javax.swing.JButton jButtonDelService;
    private javax.swing.JButton jButtonDelStaff;
    private javax.swing.JButton jButtonDelStatOrd;
    private javax.swing.JButton jButtonDelStatus;
    private javax.swing.JButton jButtonDeleteDetail;
    private javax.swing.JButton jButtonEditOrder;
    private javax.swing.JButton jButtonFilterSearch;
    private javax.swing.JButton jButtonOpenAddClient;
    private javax.swing.JButton jButtonOpenAddStaff;
    private javax.swing.JButton jButtonOpenAddStaff1;
    private javax.swing.JButton jButtonOpenAddStaff2;
    private javax.swing.JButton jButtonOpenEdiClient;
    private javax.swing.JButton jButtonOpenEditStaff;
    private javax.swing.JButton jButtonOrderAdd;
    private javax.swing.JButton jButtonSheetAdd;
    private javax.swing.JButton jButtonStaffAdd;
    private javax.swing.JButton jButtonStatAdd;
    private javax.swing.JCheckBox jCheckBoxSearch;
    private javax.swing.JComboBox<String> jComboBoxClients;
    private javax.swing.JComboBox<String> jComboBoxFilter;
    private javax.swing.JComboBox<String> jComboBoxMaterial;
    private javax.swing.JComboBox<String> jComboBoxServices;
    private javax.swing.JComboBox<String> jComboBoxStaff;
    private javax.swing.JComboBox<String> jComboBoxStaffPost;
    private javax.swing.JComboBox<String> jComboBoxStaffPostSearch;
    private javax.swing.JComboBox<String> jComboBoxStats;
    private javax.swing.JComboBox<String> jComboBoxStatuses;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialogAddMaterial;
    private javax.swing.JDialog jDialogAddOrdStatus;
    private javax.swing.JDialog jDialogAddPost;
    private javax.swing.JDialog jDialogAddService;
    private javax.swing.JDialog jDialogAddSheet;
    private javax.swing.JDialog jDialogAddStatus;
    private javax.swing.JDialog jDialogAllClients;
    private javax.swing.JDialog jDialogAllStaff;
    private javax.swing.JDialog jDialogEmptyCells;
    private javax.swing.JDialog jDialogEmptyDetails;
    private javax.swing.JDialog jDialogInfo;
    private javax.swing.JDialog jDialogNewClient;
    private javax.swing.JDialog jDialogNewOrder;
    private javax.swing.JDialog jDialogNewOrderService;
    private javax.swing.JDialog jDialogNewStaff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelError1;
    private javax.swing.JLabel jLabelError2;
    private javax.swing.JLabel jLabelError3;
    private javax.swing.JLabel jLabelError4;
    private javax.swing.JLabel jLabelError5;
    private javax.swing.JLabel jLabelError6;
    private javax.swing.JLabel jLabelError7;
    private javax.swing.JLabel jLabelFilter1;
    private javax.swing.JLabel jLabelFilter2;
    private javax.swing.JLabel jLabelFilter3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemClient;
    private javax.swing.JMenuItem jMenuItemStaff;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelMaterial;
    private javax.swing.JPanel jPanelPost;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private notbestcut.JSheetPaintPanel jSheetPaintPanel2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPaneInfo;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTableAllClients;
    private javax.swing.JTable jTableAllStaff;
    private javax.swing.JTable jTableMaterial;
    private javax.swing.JTable jTableOrdMat;
    private javax.swing.JTable jTableOrdService;
    private javax.swing.JTable jTableOrdSheets;
    private javax.swing.JTable jTableOrdStats;
    private javax.swing.JTable jTableOrders;
    private javax.swing.JTable jTablePosts;
    private javax.swing.JTable jTableService;
    private javax.swing.JTable jTableSheet;
    private javax.swing.JTable jTableStaffPost;
    private javax.swing.JTable jTableStatus;
    private javax.swing.JTextField jTextFieldAddMaterial;
    private javax.swing.JTextField jTextFieldAddPost;
    private javax.swing.JTextField jTextFieldAddService1;
    private javax.swing.JTextField jTextFieldAddStatus;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldFilter1;
    private javax.swing.JTextField jTextFieldFilter2;
    private javax.swing.JTextField jTextFieldLength;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldOrdDate;
    private javax.swing.JTextField jTextFieldOrdDesc;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldServicePrice;
    private javax.swing.JTextField jTextFieldStaffAdr;
    private javax.swing.JTextField jTextFieldStaffBirth;
    private javax.swing.JTextField jTextFieldStaffName;
    private javax.swing.JTextField jTextFieldStaffPostDate;
    private javax.swing.JTextField jTextFieldStaffSurname;
    private javax.swing.JTextField jTextFieldStaffTel;
    private javax.swing.JTextField jTextFieldStatDate;
    private javax.swing.JTextField jTextFieldSurname;
    private javax.swing.JTextField jTextFieldTel;
    private javax.swing.JTextField jTextFieldWidth;
    private javax.swing.JTabbedPane jTsbbedPaneOrders;
    // End of variables declaration//GEN-END:variables
}
