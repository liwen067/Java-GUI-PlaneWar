package ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() throws HeadlessException {
        setTitle("飞机大战");
        setSize(512,768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
