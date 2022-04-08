package sk.stuba.fei.uim.oop.utility;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'r'){
            System.out.println("safs");
        }
    }
}
