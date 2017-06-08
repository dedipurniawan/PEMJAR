package TB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class NewJFrame_C extends javax.swing.JFrame {
	// Variables declaration
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    
        String hostName;
        String portNum = "2222";
        String registryURL = "rmi://localhost:" + portNum + "/TB";
        Interface h;
        Impl im = new Impl();
        String temp="";
		private JTextArea jTextArea2;
		private JMenu file;
		private JMenuItem Open;
		private JMenuItem Save;
		private JMenuItem Close;
		Impl exportedObj = new Impl();
    
    public NewJFrame_C() throws RemoteException, MalformedURLException, NotBoundException, Exception {
        this.h = (Interface)Naming.lookup(registryURL);
        //temp = h.getText1();
        initComponents();
        Timer timer = new Timer(5000, new ActionListener() {

        @Override
        //memanggil method
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea2.setText(h.getText1());
                    h.setText1(jTextArea2.getText());
                } catch (RemoteException ex) {
                    Logger.getLogger(NewJFrame_C.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        timer.setRepeats(true); // Only execute once
        timer.start();         
    }
 
    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
   private void initComponents() {
    	jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();   
        Close = new javax.swing.JMenuItem();
        
        this.setSize(500, 300); // set the initial size of the window
		this.setTitle("Simple Java Notepad - Client"); // set the title of the window
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
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        file.add(Save);
        
        Close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
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
    //GEN-END:initComponents

    protected void SaveActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO Auto-generated method stub
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

	protected void OpenActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser dialog = new JFileChooser();
        //File file = open.getSelectedFile();
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
                    StringTokenizer st = new StringTokenizer(strLine, " ");
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
	
	public void CloseActionPerformed(java.awt.event.ActionEvent evt) {
		// if the source of the event was our "close" option
		if (evt.getSource() == this.Close)
			this.dispose(); // dispose all resources and close the application

		// if the source was the "open" option
		else if (evt.getSource() == this.Open) {
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
		else if (evt.getSource() == this.Save) {
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

	protected void jTextArea2KeyTyped(java.awt.event.KeyEvent evt) {
		// TODO Auto-generated method stub		
	}	

	ArrayList<String> textRequested = new ArrayList<String>();
    String textRequested1;
    Impl arrayListToSend = new Impl();
    
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
    	try {
            exportedObj.setText1(jTextArea2.getText());
        } catch (RemoteException ex) {
            Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        } 
    	Proses2();
    }//GEN-LAST:event_jTextArea1KeyPressed
    
    public void Proses2(){
        try {
            h.setText1(jTextArea2.getText());                   
            } catch (RemoteException ex) {
                Logger.getLogger(NewJFrame_S.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){}
    
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
            java.util.logging.Logger.getLogger(NewJFrame_S.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_S.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_S.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame_S.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NewJFrame_S().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(NewJFrame_S.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(NewJFrame_S.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(NewJFrame_S.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(NewJFrame_S.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
