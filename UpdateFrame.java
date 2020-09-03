import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class UpdateFrame extends JFrame
{
Container c;
JLabel lblId,lblName,lblSalary;
JTextField txtId,txtName,txtSalary;
JButton btnBack,btnUpdate;

UpdateFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());


lblId=new JLabel("Enter Employee Id-:");
txtId=new JTextField(20);
lblName=new JLabel("Enter Employee Name-:");
txtName=new JTextField(20);
lblSalary=new JLabel("Enter Employee Salary-:");
txtSalary=new JTextField(20);
btnUpdate=new JButton("Update");
btnBack=new JButton("Back");

c.add(lblId);
c.add(txtId);
c.add(lblName);
c.add(txtName);
c.add(lblSalary);
c.add(txtSalary);
c.add(btnUpdate);
c.add(btnBack);

btnUpdate.addActionListener(new updateEmployee());


btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}});

setTitle("Update Employee Details");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}



class updateEmployee implements ActionListener
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
int id=Integer.parseInt(txtId.getText());
String name=(txtName.getText());
double salary=Double.parseDouble(txtSalary.getText());

Employee e=(Employee)session.get(Employee.class,id);



if(id>=1)                                  //&& (rno.matches("\\d{4}-\\d{4}")))
{
e.setEid(id);
}
else
{
t.rollback();
JOptionPane.showMessageDialog(c,"please enter valid Id!!!");
txtId.setText(" ");
txtId.requestFocus();
}


if((name!=null) && (!name.equals(" ")) && (name.matches("^[a-zA-Z]*$")) && (name.length()>=2))
{
e.setEname(name);
}
else
{
t.rollback();
JOptionPane.showMessageDialog(c,"please enter valid name!!!");
txtName.setText(" ");
txtName.requestFocus();
}


if(salary>=8000)
{
e.setEsalary(salary);
}
else
{
t.rollback();
JOptionPane.showMessageDialog(c,"salary should be greater than 8000!!!");
txtSalary.setText(" ");
txtSalary.requestFocus();
}

if(e != null)
{
e.setEid(id);
e.setEname(name);
e.setEsalary(salary);

session.save(e);
t.commit();
JOptionPane.showMessageDialog(c,"record updated..");
}
else{
JOptionPane.showMessageDialog(c,"record does not exists..");
}

}
catch(Exception e){
if (t != null) 
{
t.rollback();
JOptionPane.showMessageDialog(c,"All Fields are compulsary!!");
txtId.setText("");
txtId.requestFocus();
}

/*else if(t != null)
{
t.rollback();
JOptionPane.showMessageDialog(c,"All Fields are compulsary!!");
txtName.setText("");
txtName.requestFocus();
}

else
{
t.rollback();
JOptionPane.showMessageDialog(c,"All Fields are compulsary!!");
txtSalary.setText("");
txtSalary.requestFocus();
}*/
//t.rollback();
//JOptionPane.showMessageDialog(c,"Something went wrong...");
}
finally
{
session.close();
}
}
}
}


