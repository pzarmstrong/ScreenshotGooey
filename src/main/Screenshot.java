package main;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JFrame;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import java.awt.AWTException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 *  @author parmstro
 * 
 *  @version 0.1a
 *  
 */
public class Screenshot implements MouseListener
{
    SimpleDateFormat sdf = new SimpleDateFormat("YYYMMDD-HHmmss");
    private JFrame frame;
    JTextPane textOutput;
    
    boolean isRunning = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Screenshot window = new Screenshot();
                    window.frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Screenshot()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame("Screeny");
        
        frame.setBounds(100, 100, 195, 556);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        final JButton btnStart = new JButton("Start");
        btnStart.setBounds(10, 11, 159, 23);
        frame.getContentPane().add(btnStart);
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(10, 45, 159, 382);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblCaptureWindow = new JLabel("CAPTURE WINDOW");
        lblCaptureWindow.setHorizontalAlignment(SwingConstants.CENTER);
        lblCaptureWindow.setBounds(0, 143, 159, 96);
        panel.add(lblCaptureWindow);
        
        textOutput = new JTextPane();
        textOutput.setBounds(10, 438, 159, 69);
        frame.getContentPane().add(textOutput);
        panel.addMouseListener(this);
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!isRunning)
                {
                    isRunning = true;
                    btnStart.setText("Running");
                }
                else
                {
                    isRunning = false;
                    btnStart.setText("Stopped");
                }
            }
        });
    }
    
    @Override
    public void mouseEntered(MouseEvent arg0)
    {
        if (isRunning)
        {
            try
            {
                File dir = new File("images");
                if (!dir.exists())
                {
                    dir.mkdir();
                }
                File out = new File(dir + "/screenshot_" + sdf.format(Calendar
                        .getInstance().getTime()) + ".png");
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(
                        Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(image, "png", out);
                textOutput.setText("screenshot saved: \n" + sdf.format(Calendar
                        .getInstance().getTime()));
    
            } catch (AWTException f)
            {
                System.err.println("Robot error");
                f.printStackTrace();
            } catch (IOException f)
            {
                System.err.println("File Operations Error");
                f.printStackTrace();
            } catch (SecurityException se)
            {
                System.err.println("Directory creation error");
                se.printStackTrace();
            }
        }
        else
        {
            //
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }
}
