package kolokwium;

import javax.swing.*;

/*

Zasada działania: gdy któryś z prostokątów znajdzie się w punkcie
wskazanym przez nas za pomocą slider'ów (x oraz y), to po naciśnięciu
przycisku "poluj" - zniknie.

*/

public class RectangleGame extends JFrame{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RectangleGameFrame::new);
    }
}



