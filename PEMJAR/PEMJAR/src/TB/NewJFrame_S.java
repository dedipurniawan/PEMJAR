package TB;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.*;
import java.awt.*;
//import client
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class NewJFrame_S extends javax.swing.JFrame {
	//Variables declaration                   
    private javax.swing.JMenu Open;
    private javax.swing.JMenuItem Save;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea2;
    
    Impl exportedObj = new Impl();
    public NewJFrame_S() throws RemoteException, MalformedURLException, NotBoundException {

    	initComponents();        
        String portNum = "2222", registryURL;
        try {
            startRegistry(Integer.parseInt(portNum));
            // register the object under the name "TB"
            registryURL = "rmi://localhost:" + portNum + "/TB";
            Naming.rebind(registryURL, exportedObj);
            System.out.println("Server siap");
            exportedObj.setText1("");
            Timer timer = new Timer(1000, new ActionListener() {
            	
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //exportedObj.setText1(jTextArea2.getText());
                        jTextArea2.setText(exportedObj.getText1());
                        exportedObj.setText1(jTextArea2.getText());
                    } catch (RemoteException ex) {
                        Logger.getLogger(NewJFrame_S.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            timer.setRepeats(true); // Only execute once
            timer.start();
        } catch (Exception re) {
            System.out.println("Exception in Server.main: " + re);
        }
    }

    private static void startRegistry(int rmiPortNum) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(rmiPortNum);
            registry.list();
            // The above call will throw an exception
            // if the registry does not already exist
        } catch (RemoteException ex) {
            // No valid registry at that port.
            System.out.println("RMI registry is not located at port " + rmiPortNum);
            Registry registry = LocateRegistry.createRegistry(rmiPortNum);
            System.out.println("RMI registry created at port " + rmiPortNum);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
      private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        Open = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();        
        
        this.setSize(500, 300); // set the initial size of the window
		this.setTitle("Simple Java Notepad - Server"); // set the title of the window
		setDefaultCloseOperation(EXIT_ON_CLOSE); // set the default close operation (exit when it gets closed)
		
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea2KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea2);

        Open.setText("File");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Open.add(jMenuItem1);

        Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        Open.add(Save);

        jMenuBar1.add(Open);
       
        setJMenuBar(jMenuBar1); 
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }                     

    ArrayList<String> textRequested = new ArrayList<String>();
    String textRequested1;
    Impl arrayListToSend = new Impl();
    private void jTextArea2KeyTyped(java.awt.event.KeyEvent evt) {                                    

    }                                   

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {                                      
        yangdilakukan();
    }                                     

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        //open
        JFileChooser dialog = new JFileChooser();
        //File file = open.getSelectedFile();
        //String dir = file.getAbsolutePath();
        jTextArea2.setText(null);
     
        int returnVal = dialog.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = dialog.getSelectedFile();
            try{
                FileInputStream fstream = new FileInputStream(file);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));               
                String strLine;
                while ((strLine = br.readLine()) != null){
                    StringTokenizer st = new StringTokenizer(strLine, ",");
                    jTextArea2.setText(jTextArea2.getText() + st.nextToken() +"\n");
                }
                in.close();
            }catch (Exception e){
                System.err.println("Error: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Batal Buka File ..");
        }
    }                                          

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // save
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("E:\\"));
        int retrival = chooser.showSaveDialog(this);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try (FileWriter keluar = new FileWriter(chooser.getSelectedFile().getPath() + ".txt")) {
                keluar.write(exportedObj.getText1().toString());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }                                    

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {}                                      

    public void yangdilakukan() {
        try {
            exportedObj.setText1(jTextArea2.getText());
        } catch (RemoteException ex) {
            Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        }
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Notepad".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_C.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_C.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_C.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_C.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NewJFrame_S().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(NewJFrame_C.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(NewJFrame_C.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(NewJFrame_C.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }                
}
