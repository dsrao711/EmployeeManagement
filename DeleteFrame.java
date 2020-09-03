import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteFrame extends JFrame
{
Container c;
JLabel lblId;
JTextField txtId;
JButton btnDelete,btnBack;

DeleteFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());

lblId=new JLabel("Enter Employee Id-:");
txtId=new JTextField(20);
btnDelete=new JButton("Delete");
btnBack=new JButton("Back");

c.add(lblId);
c.add(txtId);
c.add(btnDelete);
c.add(btnBack);

btnDelete.addActionListener(new deleteEmployee());

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}});

setTitle("Delete Employee Details");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

class deleteEmployee implements ActionListener
{
public void actionPerformed(ActionEvent ae)
{
Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact=cfg.buildSessionFactory();

Session session=sfact.openSession();

Transaction t=null;

try{
t=session.beginTransaction();
int rno=Integer.parseInt(txtId.getText());
Employee e=(Employee)session.get(Employee.class,rno);

if(e != null){
session.delete(e);
t.commit();
JOptionPane.showMessageDialog(c,"record deleted...");
}
else{
JOptionPane.showMessageDialog(c,"record does not exists...");
}
}
catch(Exception e){
t.rollback();
JOptionPane.showMessageDialog(c,"Please enter valid Id...");
}
finally
{
session.close();
}
}
}
}






