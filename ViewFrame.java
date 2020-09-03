import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.*;
import org.hibernate.cfg.*;

class ViewFrame extends JFrame
{
Container c;
TextArea ta;
JButton btnView;
JButton btnBack;

ViewFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());
ta=new TextArea(5,30);
btnView=new JButton("View");
btnBack=new JButton("Back");

c.add(ta);
c.add(btnView);
c.add(btnBack);

btnView.addActionListener(new viewEmployee());

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}});


setTitle("View Employee Details");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}



class viewEmployee implements ActionListener
{
public void actionPerformed(ActionEvent ae)
{
Configuration cfg=new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact=cfg.buildSessionFactory();

Session session=sfact.openSession();
StringBuffer sb= new StringBuffer();
Transaction t=null;

try{
t=session.beginTransaction();
List<Employee> emp=new ArrayList<>();
emp=session.createQuery("from Employee").list();
for(Employee e: emp)
	sb.append(e.getEid() + " " + e.getEname() + " " + e.getEsalary()+"\n");
//System.out.println(e.getEid() + " " + e.getEname() + " " + e.getEsalary());
} 
catch(Exception e){
JOptionPane.showMessageDialog(c,"Something went wrong...");
ta.requestFocus();
}
finally
{
ta.setText(sb.toString()+"\n");
session.close();
}
}
}
}

