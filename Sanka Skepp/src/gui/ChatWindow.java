package gui;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ChatWindow extends JFrame {
	private TextField tf = new TextField();
	private TextArea ta = new TextArea();
	private String msgToSend; 
	
	public ChatWindow(){
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout() );
		content.add("North",tf);
		content.add("Center",ta);
		
		tf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				msgToWrite(e.getActionCommand());
			}
		} );
		
		setContentPane(content);
		setTitle("Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
		pack();
	}
	
	private synchronized void msgToWrite(String msg)
	{
		while(msgToSend != null)
		{
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		System.out.println("Sparar msg: " + msg);
		msgToSend = msg;
		notifyAll();
	}
	
	public synchronized String GetMessageToSendToServer()
	{
		while(msgToSend == null)
		{
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		System.out.println("LŠmnar ut msg: " + msgToSend);
		String msg = msgToSend; 
		msgToSend = null;
		return msg; 
	}
	public void resetWindow(){
		tf.setText("");
	}
	
	public void PutMessageInWindow(String message)
	{
		if(message!=null){
			ta.append(message+"\n");
			}
	}
}

