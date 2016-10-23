import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.print.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.io.FileReader.*;
import java.io.FileWriter.*;
import java.util.*;
import java.util.Scanner;


class MyNote extends JFrame implements ActionListener
{
JTextArea ta;
JMenuBar mb;
Scanner s=null;
JMenu file,edit,view;
JMenuItem  new1,open,save,close,cut,copy,paste,print,about;
JFileChooser fc;
Font f;
JScrollPane jsp;

MyNote()     //throws FileNotFoundException
{
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(null);
//f=new Font("Arial",Font.BOLD,18);
f=new Font("Segoe UI",Font.BOLD,15);
//f=new Font("Monotype Corsiva",Font.BOLD,13);
fc=new JFileChooser();
jsp=new JScrollPane(ta);
ta.add(jsp);
ta=new JTextArea();
ta.setFont(f);
mb=new JMenuBar();
mb.setBounds(0,0,800,30);
ta.setBounds(0,30,800,770);
file=new JMenu("File");
view=new JMenu("View");

edit=new JMenu("Edit");
new1=new JMenuItem("New");
open=new JMenuItem("Open");
save=new JMenuItem("Save");
print=new JMenuItem("Print");
close=new JMenuItem("Close");
cut=new JMenuItem("Cut");
copy=new JMenuItem("Copy");
paste=new JMenuItem("Paste");
about=new JMenuItem("About");
file.add(new1);
file.addSeparator();
file.add(open);
file.addSeparator();
file.add(save);
file.addSeparator();
file.add(print);
file.addSeparator();
file.add(close);
file.addSeparator();
edit.add(cut);
edit.addSeparator();
edit.add(copy);
edit.addSeparator();
edit.add(paste);
edit.addSeparator();
view.add(about);
mb.add(file);
mb.add(edit);
add(mb);
add(ta);
mb.add(view);
view.addSeparator();


// Setting Shortcut Keys;
file.setMnemonic(KeyEvent.VK_F);
edit.setMnemonic(KeyEvent.VK_E);
view.setMnemonic(KeyEvent.VK_B);


KeyStroke opn=KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK);
open.setAccelerator(opn);

KeyStroke sav=KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
save.setAccelerator(sav);

KeyStroke ne=KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK);
new1.setAccelerator(ne);

KeyStroke clo=KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK);
close.setAccelerator(clo);

KeyStroke prnt=KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK);
print.setAccelerator(prnt);

KeyStroke cu=KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK);
cut.setAccelerator(cu);

KeyStroke cpy=KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK);
copy.setAccelerator(cpy);

KeyStroke pst=KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK);
paste.setAccelerator(pst);

KeyStroke ab=KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_DOWN_MASK);
about.setAccelerator(ab);


//Adding Action Listeners for all JMenuItem
cut.addActionListener(this);
copy.addActionListener(this);
paste.addActionListener(this);

new1.addActionListener(this);
open.addActionListener(this);

save.addActionListener(this);
print.addActionListener(this);
close.addActionListener(this);
//Ask  for Saving the changes made
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent we)
{
int n;
n=JOptionPane.showOptionDialog(null,"Do you want to save your changes?","Notapd",
JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);

if(n==JOptionPane.NO_OPTION)
System.exit(0);
else if(n==JOptionPane.CLOSED_OPTION)
setVisible(true);
else if(n==JOptionPane.YES_OPTION)
{
JFrame jf=new JFrame();
 int p = fc.showSaveDialog(jf);
if(p == JFileChooser.APPROVE_OPTION)
{
try(FileWriter f1 = new FileWriter(fc.getSelectedFile()))
{
BufferedWriter br = new BufferedWriter(f1);
s = new Scanner(ta.getText());
while(s.hasNext())
{
	br.write(s.nextLine());
	br.newLine();
}
						
br.close();
}
catch(IOException ie)
{
System.out.println(ie);
}
}
}
System.exit(0);

}

});
}

//Defining Action methods
public void actionPerformed(ActionEvent ae) 
{
if(ae.getSource()==new1)
{
ta.setText("");
}
if(ae.getSource()==close)
{
System.exit(0);
}

if(ae.getSource()==open)
{
ta.setText("");
int n=fc.showOpenDialog(this);
if(n==JFileChooser.APPROVE_OPTION)
{
try
{
Scanner sc=new Scanner(fc.getSelectedFile());
while(sc.hasNext())
{
String str=sc.nextLine();
ta.append(str+"\n");

}
}
catch(FileNotFoundException aie)
{
System.out.println(aie);
}
}
}
if(ae.getSource()==save)
{
int p = fc.showSaveDialog(this);
if(p == JFileChooser.APPROVE_OPTION)
{
try(FileWriter f1 = new FileWriter(fc.getSelectedFile()))
{
BufferedWriter br = new BufferedWriter(f1);
s = new Scanner(ta.getText());
while(s.hasNext())
{
	br.write(s.nextLine());
	br.newLine();
}
						
br.close();
}
catch(IOException ie)
{
System.out.println(ie);
}
}
}
if(ae.getSource()==cut)
{
ta.cut();
}
if(ae.getSource()==copy)
{
ta.copy();
}
if(ae.getSource()==paste)
{
ta.paste();
}
if(ae.getSource()==print)
{
try
{
ta.print();
}
catch(PrinterException ex)
{
System.out.println(ex);
}

}
};

public static void main(String args[])    throws IOException
{
MyNote mn=new MyNote();
mn.setSize(800,800);
mn.setVisible(true);
mn.setTitle("Notepad");

}
}
