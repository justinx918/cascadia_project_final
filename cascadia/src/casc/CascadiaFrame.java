package casc;

import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import static java.lang.System.*;

public class CascadiaFrame {
	JFrame frame;

	public CascadiaFrame() {
		frame = new JFrame("s");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1460, 840);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(new Gamestate());
		frame.setVisible(true);
	}
}
