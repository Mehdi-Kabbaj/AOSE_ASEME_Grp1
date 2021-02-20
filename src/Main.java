import java.awt.Color;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import consumers.ConsumersAgent;
import interfaces.ConsumersAgentManager;
import interfaces.MarketPlaceAgentManager;
import interfaces.ProducersAgentManager;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import marketPlace.MarketPlaceAgent;
import producers.ProducersAgent;
import structures.Offer;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Runtime runtime = Runtime.instance();
		Profile config = new ProfileImpl("localhost", 8885, null);
		config.setParameter("gui", "true");
		AgentContainer mc = runtime.createMainContainer(config);
		
		Thread t = new Thread(){
		public void run(){
			MarketPlaceAgentManager o2a_mp = null;
		    ProducersAgentManager o2a_pa = null;
		    ProducersAgentManager o2a_pa2 = null;
		    ConsumersAgentManager o2a_ca = null;
		    ConsumersAgentManager o2a_ca2 = null;
		    int day = 0;
		    
		    JFrame frame = new JFrame("Market");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1500, 1000);
	        frame.setVisible(true);
	        JPanel panel = new JPanel();
	        JLabel jlabel = new JLabel("Market");
	        JPanel panelProducer = new JPanel();
	        JLabel producer = new JLabel("Producer");
	        panelProducer.add(producer);
	        panel.add(jlabel);
	        JPanel panelConsumers = new JPanel();
	        JLabel consumers = new JLabel("Consumers");
	        panelConsumers.add(consumers);
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
	        mainPanel.add(panel);
	        mainPanel.add(panelProducer);
	        mainPanel.add(panelConsumers);
	        frame.getContentPane().add(mainPanel);
		    Border blackline;
		    blackline = BorderFactory.createLineBorder(Color.black);
		    
			try{
				Offer offer1 = new Offer(50,"Producer", true);
				Offer offer2 = new Offer(40,"Producer2", false);
				int quota1 = 1;
				int quota2 = 3;
				Object args1[] = new Object[2];
			    args1[0] = offer1;
			    args1[1] = quota1;
			    
			    Object args2[] = new Object[2];
			    args2[0] = offer2;
			    args2[1] = quota2;
				AgentController mp = mc.createNewAgent("Market", MarketPlaceAgent.class.getName(), null);
				AgentController pa = mc.createNewAgent("Producer", ProducersAgent.class.getName(), args1);
				AgentController pa2 = mc.createNewAgent("Producer2", ProducersAgent.class.getName(), args2);
				AgentController ac1 = mc.createNewAgent("consumer1", ConsumersAgent.class.getName(), null);
				AgentController ac2 = mc.createNewAgent("consumer2", ConsumersAgent.class.getName(), null);

				//TimeUnit.MILLISECONDS.sleep(20000);
				mp.start();
				//TimeUnit.MILLISECONDS.sleep(10000);
				pa.start();
				pa2.start();
				TimeUnit.MILLISECONDS.sleep(1000);
				ac1.start();
				ac2.start();
				o2a_mp = mp.getO2AInterface(MarketPlaceAgentManager.class);
		    	o2a_pa = pa.getO2AInterface(ProducersAgentManager.class);
		    	o2a_pa2 = pa2.getO2AInterface(ProducersAgentManager.class);
		    	o2a_ca = ac1.getO2AInterface(ConsumersAgentManager.class);
		    	o2a_ca2 = ac2.getO2AInterface(ConsumersAgentManager.class);
		    	
				}catch(StaleProxyException e){e.printStackTrace();System.exit(1);} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(true){
					if(o2a_mp.getDay()>day){
						day++;
						JLabel jlabel2 = new JLabel(o2a_mp.toString());
						JLabel jlabel2d = new JLabel("Day : "+day);
						panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
						panel.add(jlabel2d);
						panel.add(jlabel2);
						panel.updateUI();
						JLabel jlabel3 = new JLabel(o2a_pa.toString());
						JLabel jlabel3_2 = new JLabel(o2a_pa2.toString());
						JLabel jlabel3d = new JLabel("Day : "+day);
						panelProducer.setLayout(new BoxLayout(panelProducer, BoxLayout.Y_AXIS));
						panelProducer.add(jlabel3d);
						panelProducer.add(jlabel3);
						panelProducer.add(jlabel3_2);
						panelProducer.setBorder(blackline);
						panelProducer.updateUI();
						JLabel jlabel4d = new JLabel("Day : "+day);
						JLabel jlabel4 = new JLabel(o2a_ca.toString());
						JLabel jlabel5 = new JLabel(o2a_ca2.toString());
						panelConsumers.setLayout(new BoxLayout(panelConsumers, BoxLayout.Y_AXIS));
						panelConsumers.add(jlabel4d);
						panelConsumers.add(jlabel4);
						panelConsumers.add(jlabel5);
						panelConsumers.updateUI();
				       
					}
				}
			}		
		};
		t.start();
		//System.exit(0);
		runtime.shutDown();
		//t.stop();
    }
}
