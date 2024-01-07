package Dimens;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class Dimens extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Dimens() {
    }

    protected int height() {
        return (int)this.screenSize.getHeight();
    }

    protected int width() {
        return (int)this.screenSize.getWidth();
    }

    protected int centerX(Frame frame) {
        return (int)(this.screenSize.getWidth() - (double)frame.getWidth()) / 2;
    }

    protected int centerY(Frame frame) {
        return (int)(this.screenSize.getHeight() - (double)frame.getHeight()) / 2;
    }
}

