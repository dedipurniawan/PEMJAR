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
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem Save;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem Open;
    private javax.swing.JMenuItem Close;
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
        file = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem(); 
        Close = new javax.swing.JMenuItem();
        
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

        file.setText("File");       

        Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	OpenActionPerformed(evt);
            }
        });
        file.add(Open);

        Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                SaveActionPerformed(e);
            }
        });
        file.add(Save);
        
        Close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                CloseactionPerformed(e);
            }
        });
        file.add(Close);
        

        jMenuBar1.add(file);
       
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

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {                                      
        Proses();
    }   
    
    public void CloseactionPerformed(java.awt.event.ActionEvent e) {
		// if the source of the event was our "close" option
		if (e.getSource() == this.Close)
			this.dispose(); // dispose all resources and close the application

		// if the source was the "open" option
		else if (e.getSource() == this.Open) {
			JFileChooser open = new JFileChooser(); // open up a file chooser (a
													// dialog for the user to
													// browse files to open)
			int option = open.showOpenDialog(this); // get the option that the
													// user selected (approve or
													// cancel)
			// NOTE: because we are OPENing a file, we call showOpenDialog~
			// if the user clicked OK, we have "APPROVE_OPTION"
			// so we want to open the file
			if (option == JFileChooser.APPROVE_OPTION) {
				this.jTextArea2.setText(""); // clear the TextArea before applying
											// the file contents
				try {
					// create a scanner to read the file
					// (getSelectedFile().getPath() will get the path to the
					// file)
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while (scan.hasNext()) // while there's still something to
											// read
						this.jTextArea2.append(scan.nextLine() + "\n"); 
				} catch (Exception ex) { // catch any exceptions, and...
					// ...write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}

		// and lastly, if the source of the event was the "save" option
		else if (e.getSource() == this.Save) {
			JFileChooser save = new JFileChooser(); // again, open a file
													// chooser
			int option = save.showSaveDialog(this); // similar to the open file,
													// only this time we call
			// showSaveDialog instead of showOpenDialog
			// if the user clicked OK (and not cancel)
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					// create a buffered writer to write to a file
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.jTextArea2.getText()); // write the contents of
														// the TextArea to the
														// file
					out.close(); // close the file stream
				} catch (Exception ex) { // again, catch any exceptions and...
					// ...write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}
	}

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {                                           
        //open
        JFileChooser dialog = new JFileChooser();
        //file filee = Open.getSelectedFile();
        //String dir = file.getAbsolutePath();
        jTextArea2.setText("");
     
        int returnVal = dialog.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = dialog.getSelectedFile();
            try{
                FileInputStream fstream = new FileInputStream(file);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));               
                String strLine;
                while ((strLine = br.readLine()) != null){
                    StringTokenizer st = new StringTokenizer(strLine, "");
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
        chooser.setCurrentDirectory(new File("D:\\"));
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

    public void Proses() {
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
