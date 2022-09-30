package icai.dtc.isw.ui;

import icai.dtc.isw.domain.Decoracion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Texto extends JTextField {

    private Decoracion decoracion;
    private Color textColor;
    private Color backgroundColor;

    public Texto(int columns){
        super(columns);
        setDecoracion(Decoracion.SIN_DECORAR);
    }

    public Texto(int columns, Decoracion decoracion, Color textColor, Color backgroundColor){
        this(columns);
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        setDecoracion(decoracion);
    }

    public void setDecoracion(Decoracion decoracion){
        this.decoracion = decoracion;
        if (decoracion == Decoracion.DECORADO){
            setBackground(backgroundColor);
            setBorder(new EmptyBorder(7, 3, 7, 3));
            setForeground(textColor);
            setCaretColor(textColor);
        }
    }

    @Override
    public void paint(Graphics g){
        if(decoracion == Decoracion.DECORADO) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

            g2.setColor(textColor);
            g2.fillRect(0, getHeight() - 7, getWidth(), 1);
            g2.dispose();
        }else{
            super.paintComponent(g);
        }
    }
}