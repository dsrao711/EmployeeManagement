import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;


class MainFrame extends JFrame
{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete;

MainFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());
btnAdd=new JButton("Add");
btnView=new JButton("View");
btnUpdate=new JButton("Update");
btnDelete=new JButton("Delete");

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
AddFrame a=new AddFrame();
dispose();
}});

btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
ViewFrame a=new ViewFrame();
dispose();
}});

btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
UpdateFrame a=new UpdateFrame();
dispose();
}});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
DeleteFrame a=new DeleteFrame();
dispose();
}});

setTitle("Employee Details");
setSize(300,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

public static void main(String args[])
{
MainFrame m=new MainFrame();
}
}//end of mainframe


class hibernate
{
Console m = System.console();
public void addEmployee(int id,String name,double salary)
{
try
{
Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact=cfg.buildSessionFactory();

Session session=sfact.openSession();

Transaction t=null;

t=session.beginTransaction();
Employee e=new Employee();
 id=Integer.parseInt(m.readLine("enter rno "));
name=m.readLine("enter name ");
 salary=Double.parseDouble(m.readLine("Enter salary"));
e.setEid(id);
e.setEname(name);
e.setEsalary(salary);
session.save(e);
t.commit();
JOptionPane.showMessageDialog(new JDialog(),e + "record inserted...");
}
catch(Exception e){
System.out.println(e);
}
/*finally
{
session.close();
} */
}
}


