package ui;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        Panel panel = new Panel(frame);
        panel.begin();
        panel.fireBegin();
        frame.add(panel);
        frame.setVisible(true);
    }
}
