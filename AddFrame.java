import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class AddFrame extends JFrame
{
  Container c;
  JLabel lblId,lblName,lblSalary;
  JTextField txtId,txtName,txtSalary;
  JButton btnBack,btnSave;

  AddFrame()
  {
    c=getContentPane();
    c.setLayout(new FlowLayout());

    lblId=new JLabel("Enter Employee Id-:");
    txtId=new JTextField(20);
    lblName=new JLabel("Enter Employee Name-:");
    txtName=new JTextField(20);
    lblSalary=new JLabel("Enter Employee Salary-:");
    txtSalary=new JTextField(20);
    btnSave=new JButton("Save");
    btnBack=new JButton("Back");

    c.add(lblId);
    c.add(txtId);
    c.add(lblName);
    c.add(txtName);
    c.add(lblSalary);
    c.add(txtSalary);
    c.add(btnSave);
    c.add(btnBack);

    btnSave.addActionListener(new addEmployee());

    btnBack.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent ae){
    MainFrame a=new MainFrame();
    dispose();
  }});

setTitle("Add Employee Details");
setSize(400,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}


class addEmployee implements ActionListener
{
 public void actionPerformed(ActionEvent ae)
 {
   Configuration cfg=new Configuration();
   cfg.configure("hibernate.cfg.xml");

   SessionFactory sfact=cfg.buildSessionFactory();

   Session session=sfact.openSession();

   Transaction t=null;

   try
   {
        t=session.beginTransaction();
 	Employee e=new Employee();

        // try 
        // {
 
       		 Integer rno=Integer.parseInt(txtId.getText());
  		try{
      		  if(rno>=1)                              //&& (rno == ("[+-]?[0-9][0-9]*")))  && (rno.matches("\\d{4}-\\d{4}")))
     		  {
    			e.setEid(rno);
        	  }
		else
		{
         		 t.rollback();
          		 JOptionPane.showMessageDialog(c,"please enter valid Id!!!");
         		 txtId.setText("");
        		 txtId.requestFocus();
		}
		}
      		  catch (Exception g)
    		  {
         		 t.rollback();
          		 JOptionPane.showMessageDialog(c,"please enter valid Id!!!");
         		 txtId.setText("");
        		 txtId.requestFocus();
      		  } 
   	  // }
        /*  catch (NumberFormatException ee)
        {
          t.rollback();
          JOptionPane.showMessageDialog(c,"please enter employee id");
          txtId.setText("");
          txtId.requestFocus();
         }  */

       //try
       // {
             String name=(txtName.getText());
	try{
             if((name!=null) && (!name.equals(" ")) && (name.matches("^[a-zA-Z]*$")) && (name.length()>=2))
             {
              e.setEname(name);
             }
              else if (name.equals("")  && ( txtSalary.getText().isEmpty())  )
                {
   			t.rollback();
   			JOptionPane.showMessageDialog(c,"Please enter employee name and salary!!");
   			txtId.setText("");
   			txtId.requestFocus();
		}  
		 else
  		{
   			t.rollback();
   			JOptionPane.showMessageDialog(c,"please enter valid name!!!");
   			txtName.setText("");
   			txtName.requestFocus(); 
	 	} 
	}

		 catch(Exception f)
		{
    			t.rollback();
     			JOptionPane.showMessageDialog(c, "please enter employee name");
     			txtId.setText("");
    			 txtId.requestFocus();
		} 

		// try
 		//{
   			double salary=Double.parseDouble(txtSalary.getText());
   			if(salary>=8000)
   			{
     				e.setEsalary(salary);
   			}
 			  else
 			  {
			     t.rollback();
			     JOptionPane.showMessageDialog(c,"salary should be greater than 8000!!!");
			     txtSalary.setText("");
			     txtSalary.requestFocus();
			   } 
		// }
		/*  catch(NumberFormatException ee)
 		{
 		  t.rollback();
 		  JOptionPane.showMessageDialog(c,"please enter employee salary");
 		  txtSalary.setText("");
 		  txtSalary.requestFocus();
		 } */

	session.save(e);
	if(!t.wasCommitted()){
	t.commit();}
	JOptionPane.showMessageDialog(c,"record inserted...");
}

catch(Exception ee)
{

if((txtId.getText().isEmpty()) && (txtName.getText().isEmpty()) && (txtSalary.getText().isEmpty()))
{
t.rollback();
JOptionPane.showMessageDialog(c,"Please enter all 3 fields");
txtId.setText("");
txtId.requestFocus();
}
else if((txtId.getText().isEmpty()) && !(txtName.getText().isEmpty()) && !(txtSalary.getText().isEmpty()))
{
t.rollback();
JOptionPane.showMessageDialog(c,"Please enter employee id ");
txtId.setText("");
txtId.requestFocus();
}
else if(!(txtId.getText().isEmpty()) && (txtName.getText().isEmpty()) && !(txtSalary.getText().isEmpty()))
{
t.rollback();
JOptionPane.showMessageDialog(c,"Please enter employee name");
txtId.setText("");
txtId.requestFocus();
}
else if(!(txtId.getText().isEmpty()) && !(txtName.getText().isEmpty()) && (txtSalary.getText().isEmpty()))
{
t.rollback();
JOptionPane.showMessageDialog(c,"Please enter employee salary");
txtId.setText("");
txtId.requestFocus();
}
else if((txtId.getText().isEmpty()) && (txtName.getText().isEmpty()) && !(txtSalary.getText().isEmpty()))
{
t.rollback();
JOptionPane.showMessageDialog(c,"Please enter employee id and name");
txtId.setText("");
txtId.requestFocus();
}
else if(!(txtId.getText().isEmpty()) && (txtName.getText().isEmpty()) && (txtSalary.getText().isEmpty()))
{
t.rollback();
JOptionPane.showMessageDialog(c,"Please enter employee name and salary");
txtId.setText("");
txtId.requestFocus();
}
else if((txtId.getText().isEmpty()) && !(txtName.getText().isEmpty()) && (txtSalary.getText().isEmpty()))
{
t.rollback();
JOptionPane.showMessageDialog(c,"Please enter employee id and salary");
txtId.setText("");
txtId.requestFocus();
}
else
{
t.rollback();
JOptionPane.showMessageDialog(c,"Please check field...!!");
txtId.setText("");
txtId.requestFocus();
} 

}
 

  finally
  {
    session.close();
  }
}
}

}




